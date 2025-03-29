import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18290_NM과K_1 {
  static int N;
  static int M;
  static int K;
  static int[][] grid;
  static boolean[][] visited;
  static int maxPickSum = Integer.MIN_VALUE;
  static int currentPickSum = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    grid = new int[N][M];
    visited = new boolean[N][M];
    StringTokenizer nums;

    // 격자판 입력받음.
    for (int row = 0; row < N; row++) {
      nums = new StringTokenizer(br.readLine());
      for (int col = 0; col < M; col++) {
        grid[row][col] = Integer.parseInt(nums.nextToken());
      }
    }

    backtrack(0, 0, 0);

    System.out.println(maxPickSum);

  }

  public static void backtrack(int pickCount, int prevRow, int prevCol) {
    // K개 다 선택한 경우 -> 기존의 K개 더한값의 최댓값과 비교해 더 큰 값을 저장한다
    if (pickCount == K) {
      maxPickSum = Math.max(maxPickSum, currentPickSum);
      return;
    }

    // 직전에 선택한 칸 다음칸부터 시작한다.
    for (int r = prevCol >= M ? prevRow + 1 : prevRow; r < N; r++) {
      for (int c = prevRow == r ? prevCol : 0; c < M; c++) {
        // 선택되지 않은 칸이고, 사방에 선택된 칸이 없을 때
        if (!visited[r][c] && (r - 1 < 0 || !visited[r - 1][c]) && (r + 1 >= N || !visited[r + 1][c]) && (c - 1 < 0
            || !visited[r][c - 1]) && (c + 1 >= M || !visited[r][c + 1])) {

          visited[r][c] = true;
          currentPickSum += grid[r][c];
          backtrack(pickCount + 1, r, c + 1);
          currentPickSum -= grid[r][c];
          visited[r][c] = false;
        }

      }
    }

  }
}
