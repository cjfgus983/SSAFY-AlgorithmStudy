import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 3085번
public class BOJ_3085_사탕게임 {
  static int N;
  static char[][] board;
  static int maxLength;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    board = new char[N][N];
    maxLength = 0;

    // 보드 입력받음
    for (int inputLine = 0; inputLine < N; inputLine++) {
      board[inputLine] = br.readLine().toCharArray();
    }


    // 모든 칸에서 스왑을 시도하며 최댓값 계산
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 오른쪽과 교환
        if (j + 1 < N) {
          swap(i, j, i, j + 1);
          getMaxLength();
          swap(i, j, i, j + 1);
        }
        // 아래쪽과 교환
        if (i + 1 < N) {
          swap(i, j, i + 1, j);
          getMaxLength();
          swap(i, j, i + 1, j);
        }
      }
    }

    System.out.println(maxLength);

  }
  

  // 두 사탕 위치 바꿈.
  private static void swap(int row1, int col1, int row2, int col2) {
    char temp = board[row1][col1];
    board[row1][col1] = board[row2][col2];
    board[row2][col2] = temp;
  }

  // 현재 보드 상태에서 행과 열로 가장 긴 연속 부분의 길이를 구한다.
  private static void getMaxLength() {
    for (int i = 0; i < N; i++) {
      int rowCount = 1;
      int colCount = 1;
      for (int j = 1; j < N; j++) {
        //행에서 최대 길이
        if (board[i][j] == board[i][j - 1]) {
          rowCount++;
        } else {
          maxLength = Math.max(maxLength, rowCount);
          rowCount = 1;
        }
        //열에서 최대 길이
        if (board[j][i] == board[j - 1][i]) {
          colCount++;
        } else {
          maxLength = Math.max(maxLength, colCount);
          colCount = 1;
        }
      }
      maxLength = Math.max(maxLength, rowCount);
      maxLength = Math.max(maxLength, colCount);
    }
  }

}