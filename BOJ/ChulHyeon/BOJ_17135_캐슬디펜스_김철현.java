package ChulHyeon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 1. 입력을 받아 맵을 초기화한다.
 * 2. 궁수 3명을 배치할 수 있는 모든 조합을 생성한다.
 * 3. 각 조합에 대해 시뮬레이션을 진행한다.
 *    - 적을 궁수의 공격 범위 내에서 가장 가까운 적을 찾아 제거 for 문으로 왼 -> 오른쪽
 *    - 적들이 아래로 이동한다.
 *    - 최대 제거한 적의 수를 갱신한다.
 * 4. 가장 많은 적을 제거할 수 있는 경우를 출력한다.
 */
public class BOJ_17135_캐슬디펜스_김철현 {
    static int rowSize, colSize, range; // 맵의 행 크기, 열 크기, 궁수의 공격 범위
    static int[][] map; // 원본 맵
    static int[][] copyMap; // 맵을 원래 상태로 되돌릴 때 사용할 복사본
    static int ans; // 최대 제거할 수 있는 적의 수

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        // 맵 초기화
        map = new int[rowSize + 1][colSize + 1];
        copyMap = new int[rowSize + 1][colSize + 1];

        // 맵 입력
        for (int i = 1; i <= rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= colSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j]; // 맵 복사
            }
        }

        ArrayList<Integer> archer = new ArrayList<>(); // 궁수 위치 조합 저장
        ans = 0;
        combination(1, colSize, 3, archer); // 궁수 3명의 위치를 조합하여 배치

        bw.write(ans + "\n"); // 최종 결과 출력
        bw.flush();
        bw.close();
        br.close();
    }

    // 맵을 원래 상태로 초기화
    public static void init() {
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    // 맨해튼 거리 계산 함수
    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 궁수 위치 조합 생성 함수
    public static void combination(int start, int n, int col, ArrayList<Integer> archer) {
        if (col == 0) { // 궁수 3명을 모두 배치한 경우
            init(); // 맵 초기화
            attack(archer); // 공격 실행
            return;
        }

        for (int idx = start; idx <= n; idx++) {
            archer.add(idx);
            combination(idx + 1, n, col - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    // 적 공격
    public static void attack(ArrayList<Integer> archer) {
        int res = 0; // 제거한 적의 수

        for (int n = 1; n <= rowSize; n++) { // 최대 N턴 진행 가능
            boolean[][] visited = new boolean[rowSize + 1][colSize + 1]; // 적 제거 여부 체크

            // 각 궁수가 적을 찾아 공격
            for (int anchorIdx = 0; anchorIdx < archer.size(); anchorIdx++) {
                int anchorCol = archer.get(anchorIdx); // 궁수의 위치 (열)
                int minD = Integer.MAX_VALUE; // 최소 거리
                int minR = Integer.MAX_VALUE; // 가장 가까운 적의 행
                int minC = Integer.MAX_VALUE; // 가장 가까운 적의 열

                // 맵 전체 탐색하여 최단 거리의 적 찾기
                for (int row = 1; row <= rowSize; row++) {
                    for (int col = 1; col <= colSize; col++) {
                        if (map[row][col] == 1) { // 적 발견 시
                            int d = distance(row, rowSize + 1, col, anchorCol);
                            if (d < minD || (d == minD && col < minC)) { // 거리 짧거나, 같으면 왼쪽 우선
                                minD = d;
                                minR = row;
                                minC = col;
                            }
                        }
                    }
                }

                // 공격 범위 내의 적을 표시
                if (minD <= range) {
                    visited[minR][minC] = true;
                }
            }

            // 적 제거
            for (int row = 1; row <= rowSize; row++) {
                for (int col = 1; col <= colSize; col++) {
                    if (visited[row][col]) {
                        map[row][col] = 0;
                        res++;
                    }
                }
            }

            // 성 바로 위 줄을 0으로 초기화
            for (int col = 1; col <= colSize; col++) {
                map[rowSize][col] = 0;
            }

            // 맵 이동 (적이 한 칸 아래로 이동)
            for (int row = rowSize; row >= 1; row--) {
                for (int col = 1; col <= colSize; col++) {
                    map[row][col] = map[row - 1][col];
                }
            }
        }

        ans = Math.max(ans, res); // 최대 제거한 적 수 갱신
    }
}
