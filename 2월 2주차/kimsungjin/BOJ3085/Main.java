import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int maxLine = 0;
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, 1, 0, -1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    char[][] candy = new char[n][n];

    for (int i = 0; i < n; i++) {
      candy[i] = br.readLine().toCharArray();
    }

    for (int i = 0; i < n; i++) {
      checkMaxLine(candy, n, i, i, 0, n - 1);
    }
    for (int j = 0; j < n; j++) {
      checkMaxLine(candy, n, 0, n - 1, j, j);
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < 4; k++) {
          int x = i + dx[k];
          int y = j + dy[k];
          if (
            x >= 0 && x < n && y >= 0 && y < n && candy[i][j] != candy[x][y]
          ) {
            swap(candy, i, j, x, y);
            checkMaxLine(candy, n, i, x, j, y);
            swap(candy, i, j, x, y);
          }
        }
      }
    }

    System.out.println(maxLine);
  }

  private static void checkMaxLine(
    char[][] candy,
    int n,
    int startI,
    int endI,
    int startJ,
    int endJ
  ) {
    for (int i = startI; i <= endI; i++) {
      int localMax = 1;
      for (int j = 1; j < n; j++) {
        if (candy[i][j] == candy[i][j - 1]) {
          localMax++;
          maxLine = Math.max(maxLine, localMax);
        } else {
          localMax = 1;
        }
      }
    }

    for (int j = startJ; j <= endJ; j++) {
      int localMax = 1;
      for (int i = 1; i < n; i++) {
        if (candy[i][j] == candy[i - 1][j]) {
          localMax++;
          maxLine = Math.max(maxLine, localMax);
        } else {
          localMax = 1;
        }
      }
    }
  }

  private static void swap(char[][] candy, int i, int j, int x, int y) {
    char temp = candy[i][j];
    candy[i][j] = candy[x][y];
    candy[x][y] = temp;
  }
}
