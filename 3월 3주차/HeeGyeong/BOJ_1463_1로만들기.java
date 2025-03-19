import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1463_1로만들기 {
  static Scanner sc;
  static int[] dp;
  static int N;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    N = sc.nextInt(); // 주어진 정수
    dp = new int[N + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[N] = 0;

    for (int idx = N; idx >= 2; idx--) {
      int curCount = dp[idx];
      if (idx % 3 == 0 && curCount + 1 < dp[idx / 3]) {
        dp[idx / 3] = curCount + 1;
      }
      if (idx % 2 == 0 && curCount + 1 < dp[idx / 2]) {
        dp[idx / 2] = curCount + 1;
      }
      if (curCount + 1 < dp[idx - 1]) {
        dp[idx - 1] = curCount + 1;
      }
    }

    System.out.println(dp[1]);
  }

}
