/**
 * 1. 입력
 *    1-1. 테케 수 입력
 *    1-2. 초기 맵의 크기(rowSize, colSize)와 총 실행 시간(totalTime)을 입력.
 *    1-3. 맵의 크기 늘리기 기존 사이즈에 최대 커질 수 있는 값 2 * totalTime을 더해서 맵을 만든다.
 *    1-4. 초기에 입력 받은 부분 visit 갱신
 *
 * 2. 시뮬레이션(1 ~ totalTime):
 *    2-1. cellPQ에 저장된 후보 세포들을 꺼내어, 아직 해당 위치에 세포가 없으면 cellList에 추가한다.
 *    즉 pq에는 해당 칸에서 세포가 될 수 있는 후보들이 들어가고 list에는 현재 세포에 대한 정보가 들어간다.
 *    2-2. cellList를 순회하며 각 세포의 상태 변화를 처리한다.
 *         2-2-1. 비활성 상태(0)인 세포의 timer가 현재 시간(nowTime)과 같으면:
 *                - 세포를 활성 상태(1)로 전환하고,
 *                - timer를 nowTime + initTime (즉, 활성 상태 지속 시간)으로 갱신한다.
 *                - 4방향 탐색해서 가능한 부분 pq에 넣기 이 때 시간 주의해서 넣기
 *         2-2-2. 활성 상태(1)인 세포의 timer가 현재 시간(nowTime)과 같으면 세포를 죽은 상태(2)로 전환한다.
 *
 * 3. 결과 출력:
 *    3-1. 시뮬레이션 종료 후, cellList에서 상태가 비활성(0) 또는 활성(1)인 세포의 개수를 결과로 출력한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class SWEA_5653_줄기세포배양_김철현 {
	// 세포 클래스 (각 세포는 위치, 남은 시간(timer), 생명력(initTime), 상태(state)를 가진다)
	static class Cell implements Comparable<Cell> {
		int row, col;
		int timer;     // 세포 상태 전환 시점 (비활성 시: 활성화, 활성 시: 사망)
		int initTime;  // 초기 생명력 값 (비활성 기간 및 활성 기간)
		int state;     // 0 -> 비활성, 1 -> 활성, 2 -> 죽음

		public Cell(int row, int col, int timer, int initTime) {
			this.row = row;
			this.col = col;
			this.timer = timer;
			this.initTime = initTime;
			this.state = 0; // 초기 상태는 항상 비활성
		}
		@Override
		public int compareTo(Cell o) {
			// 초기 생명력(initTime)이 작은 순서부터 처리해야, 나중에 생명력 높은 세포가 덮어쓸 수 있다.
			return this.initTime - o.initTime;
		}
	}

	// 상하좌우 이동 배열
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	// 입력 크기 및 총 실행 시간
	static int rowSize, colSize, totalTime;
	// visit: 해당 격자 위치에 이미 세포가 배치되었는지 확인 (중복 번식 방지)
	static boolean[][] visit;
	// cellList: 현재 존재하는(번식 및 상태 변화가 진행되는) 세포들을 관리하는 리스트
	static List<Cell> cellList;
	// cellPQ: 번식 후보들을 저장하는 우선순위 큐 (생명력(initTime) 내림차순 처리 X : 비교 기준은 입력 순서를 위한 것)
	static PriorityQueue<Cell> cellPQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			totalTime = Integer.parseInt(st.nextToken());

			// 격자 크기: 입력 크기에 총 실행 시간(totalTime)을 양쪽에 추가하여 확장
			int mapRowSize = rowSize + 2 * totalTime;
			int mapColSize = colSize + 2 * totalTime;
			visit = new boolean[mapRowSize][mapColSize];
			cellList = new ArrayList<>();
			// 초기 생명력(initTime)이 작은 것부터 시작해야, 나중에 생명력 높은 세포가 덮어쓸 수 있음.
			cellPQ = new PriorityQueue<>();

			// 초기 배치: 오프셋(totalTime)만큼 이동하여 입력 격자를 중앙에 배치
			for (int row = 0; row < rowSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < colSize; col++) {
					int life = Integer.parseInt(st.nextToken());
					if (life > 0) {
						int nextRow = row + totalTime;
						int nextCol = col + totalTime;
						// 초기 세포: 비활성 상태, timer = initTime (즉, initTime 시간이 지나면 활성화)
						cellList.add(new Cell(nextRow, nextCol, life, life));
						visit[nextRow][nextCol] = true;
					}
				}
			}
			// 시뮬레이션 진행 (1부터 totalTime까지)
			simulate();
			// totalTime 후 살아있는 세포(비활성 또는 활성 상태)의 수를 결과로 출력
			int result = 0;
			for (Cell cell : cellList) {
				if (cell.state == 0 || cell.state == 1)
					result++;
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	// 시뮬레이션 메서드
	// 매 시간(nowTime)마다 세포들의 상태 변화를 처리하고, 번식 후보(cellPQ)를 cellList에 반영한다.
	static void simulate() {
		for (int nowTime = 1; nowTime <= totalTime; nowTime++) {
			// 1. 번식 후보 처리: cellPQ에 저장된 세포 후보들을 꺼내어 해당 위치에 세포가 아직 없으면 cellList에 추가
			while (!cellPQ.isEmpty()) {
				Cell candidate = cellPQ.poll();
				if (!visit[candidate.row][candidate.col]) {
					visit[candidate.row][candidate.col] = true;
					cellList.add(candidate);
				}
			}

			// 2. cellList 순회하여 각 세포의 상태 변화 및 번식 처리
			//    - 비활성 상태(cell.state == 0)에서 timer와 nowTime이 같으면 활성화(1)로 전환
			//      그리고 활성 상태 유지 시간(즉, 죽는 시점)은 nowTime + initTime으로 갱신
			//      이와 동시에 4방향 번식 후보를 cellPQ에 등록 (번식 시각: nowTime + initTime + 1)
			//    - 활성 상태(cell.state == 1)에서 timer와 nowTime이 같으면 세포를 죽음(2)으로 전환
			for (int i = 0; i < cellList.size(); i++) {
				Cell cell = cellList.get(i);
				if (cell.state == 2)
					continue;

				// 비활성 상태에서 활성 시점에 도달한 경우
				if (cell.state == 0 && cell.timer == nowTime) {
					cell.state = 1;
					cell.timer = nowTime + cell.initTime; // 활성 상태 유지 시간 갱신 (죽는 시점)

					// 번식 -> 4방향에 대해 새 세포 후보를 생성하여 우선순위 큐에 등록
					// 새 세포는 '현재 시간 + initTime + 1'에 번식한다.
					for (int d = 0; d < 4; d++) {
						int nextR = cell.row + dr[d];
						int nextC = cell.col + dc[d];
						cellPQ.add(new Cell(nextR, nextC, nowTime + cell.initTime + 1, cell.initTime));
					}
				}
				// 활성 상태에서 죽을 시점에 도달한 경우
				else if (cell.state == 1 && cell.timer == nowTime) {
					cell.state = 2;
				}
			}
		}
	}
}
