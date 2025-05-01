package ChulHyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* 0. 재귀 함수 호출은 각 행에서 0번 열에서 시작한다. 총 rowSize만큼
* 1. 이동 배열을 둬서 오른쪽 대각선 위, 오른쪽, 오른쪽 대각선 아래 이동 가능도록 함
* 2. 범위, 방문, 못가는 빌딩 체크
* 3. 재귀 돌리는데 해당 결과가 true 로 나오면 => 끝까지 가서 파이프 설치를 했다면
*       3중 for문에서 다른 경로 도착지를 볼 필요가 없음
*
*
* visit = true로 하고 false 로 돌리면 안된다. -> 이미 방문한 곳은 설치가 되는 길이니까 굳이 갈 필요 없음
*
*
*
* 방문한 곳은 다신 안가니까 - visit때문에
* O(rowSize * colSize) ?
* */

public class BOJ_3109_빵집_김철현 {
    static int rowSize, colSize, pipeCount = 0;
    static char[][] map;
    static boolean[][] visit;

    // 대각선 위, 오른쪽, 대각선 아래
    static int[] dirR = {-1, 0, 1};
    static int[] dirC = {1, 1, 1};

    // DFS 탐색 (true 반환 시 경로 탐색 성공)
    static boolean dfs(int nowRow, int nowCol) {
        // 최대 열에 도착하면 파이프 설치 성공
        if (nowCol == colSize - 1) {
            return true;
        }

        // 3방향 탐색
        for (int dir = 0; dir < 3; dir++) {
            int nextRow = nowRow + dirR[dir];
            int nextCol = nowCol + dirC[dir];

            // 범위 체크
            if (nextRow < 0 || nextRow >= rowSize || nextCol >= colSize) {
                continue;
            }
            // 방문 체크
            if(visit[nextRow][nextCol]){
                continue;
            }
            // 못가는 곳 체크
            if(map[nextRow][nextCol] == 'x')
            {
                continue;
            }
            // 방문 처리 - 방문 처리는 되돌리지 않는다. 이미 true라면 파이프가 설치되었다고 체크 했을 테니
            visit[nextRow][nextCol] = true;

            // 재귀 돌리기
            if (dfs(nextRow, nextCol)) {
                return true; // 성공하면 더 탐색할 필요 없음
            }
        }

        return false; // 모든 방향에서 이동 불가능하면 실패
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new char[rowSize][colSize];
        visit = new boolean[rowSize][colSize];

        // 지도 입력
        for (int row = 0; row < rowSize; row++) {
            String inputStr = br.readLine();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = inputStr.charAt(col);
            }
        }

        // 첫 번째 열에서 모든 행에 대해 DFS 탐색 시작
        for (int row = 0; row < rowSize; row++) {
            if (dfs(row, 0)) {
                pipeCount++; // 성공 시 카운트 증가
            }
        }

        System.out.println(pipeCount);
    }
}
