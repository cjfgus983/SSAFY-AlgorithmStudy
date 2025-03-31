import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [idea]
 * 백트래킹을 하며 4칸을 선택하다 보면
 * 5 종류의 테트로미노를 회전, 대칭시킨 모양이 된다.
 * (ㅜ 모양의 테트로미노는 따로 처리해줘야 한다.)
 */
public class BOJ_14500_테트로미노 {
  static int n;
  static int m;
  static int[][] grid;
  static boolean[][] visited;
  static int maxPutSum = Integer.MIN_VALUE;
  // 사방
  static int[] dx = { -1, 1, 0, 0 };
  static int[] dy = { 0, 0, -1, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    grid = new int[n][m];
    visited = new boolean[n][m];

    // 격자판 입력받음.
    for (int row = 0; row < n; row++) {
      st = new StringTokenizer(br.readLine());
      for (int col = 0; col < m; col++) {
        grid[row][col] = Integer.parseInt(st.nextToken());
      }
    }

    // 모든 칸 순회
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < m; col++) {
        visited[row][col] = true;
        backTrack(row, col, grid[row][col], 1);
        visited[row][col] = false;
      }
    }

    System.out.println(maxPutSum);

  }

  static void backTrack(int row, int col, int sum, int count) {
    // 테트로미노 완성 시 수들의 합 계산
    if (count == 4) {
      maxPutSum = Math.max(maxPutSum, sum);
      return;
    }

    // 사방 탐색
    for (int i = 0; i < 4; i++) {
      int curRow = row + dx[i];
      int curCol = col + dy[i];

      // grid 벗어나는 경우
      if (curRow < 0 || curRow >= n || curCol < 0 || curCol >= m) {
        continue;
      }

      // 방문하지 않은 칸인 경우
      if (visited[curRow][curCol] == false) {

        // ㅜ 모양 테트로미노를 위해 2번째 칸에서 백트래킹 한 번 더 수행
        if (count == 2) {
          visited[curRow][curCol] = true;
          backTrack(row, col, sum + grid[curRow][curCol], count + 1);
          visited[curRow][curCol] = false;
        }

        visited[curRow][curCol] = true;
        backTrack(curRow, curCol, sum + grid[curRow][curCol], count + 1);
        visited[curRow][curCol] = false;
      }
    }
  }
}
