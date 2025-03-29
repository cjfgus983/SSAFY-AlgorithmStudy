import java.util.Scanner;

public class BOJ_11727_2xn타일링2 {
  static Scanner sc;
  static int[] dp;
  static int N;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    N = sc.nextInt(); // 주어진 정수
    dp = new int[1001];
    dp[1] = 1;
    dp[2] = 3;

    for (int idx = 3; idx <= N; idx++) {
      int curCount = (dp[idx - 1] + dp[idx - 2] * 2) % 10007;
      dp[idx] = curCount;
    }

    System.out.println(dp[N]);
  }

}
