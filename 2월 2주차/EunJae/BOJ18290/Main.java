package BOJ18290;


/*문제 18290
크기가 N×M인 격자판의 각 칸에 정수가 하나씩 들어있다. 이 격자판에서 칸 K개를 선택할 것이고, 선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 구하려고 한다. 단, 선택한 두 칸이 인접하면 안된다. r행 c열에 있는 칸을 (r, c)라고 했을 때, (r-1, c), (r+1, c), (r, c-1), (r, c+1)에 있는 칸이 인접한 칸이다.

입력
첫째 줄에 N, M, K가 주어진다. 둘째 줄부터 N개의 줄에 격자판에 들어있는 수가 주어진다.

출력
선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 출력한다.

제한
1 ≤ N, M ≤ 10
1 ≤ K ≤ min(4, N×M)
격자판에 들어있는 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이다.
항상 K개의 칸을 선택할 수 있는 경우만 입력으로 주어진다.
예제 입력 1 
1 1 1
1
예제 출력 1 
1
 * 
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static BufferedWriter bw;
    static int max = Integer.MIN_VALUE;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        bw.write(max + "\n");
        bw.flush();
    }

    static void dfs(int depth, int total) throws Exception {
        if (depth == K) {
            max = Math.max(total, max);
            return;
        }

        // (r, c)에서 선택할 칸을 찾기
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (!visited[row][col]) {
                    // 방문 처리
                    visited[row][col] = true;
                    // 인접한 칸들 방문 처리
                    boolean[] tempVisited = new boolean[4]; // 4방향 방문 처리
                    for (int d = 0; d < 4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                            tempVisited[d] = visited[nr][nc];
                            visited[nr][nc] = true;
                        }
                    }

                    // 재귀 호출
                    dfs(depth + 1, total + arr[row][col]);

                    // 인접한 칸들 복원 처리
                    for (int d = 0; d < 4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                            visited[nr][nc] = tempVisited[d];
                        }
                    }

                    // 현재 칸 복원
                    visited[row][col] = false;
                }
            }
        }
    }
}
