import java.util.Scanner;

public class BOJ_10844_쉬운계단수 {
  static Scanner sc;
  static int[][] dp;
  static int N;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    N = sc.nextInt(); // 정수의 길이
    dp = new int[101][10];
    for (int n = 1; n <= 9; n++) {
      dp[1][n] = 1; //정수의 길이가 1이고 n으로 끝나는 경우의 수
    }

    for (int i = 2; i <= N; i++) {
      dp[i][0] = dp[i - 1][1];
      for (int j = 1; j <= 8; j++) {
        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
      }
      dp[i][9] = dp[i - 1][8];
    }

    long result = 0;
    for (int last = 0; last <= 9; last++) {
      result += dp[N][last];
    }
    System.out.println(result % 1_000_000_000);
  }

}
