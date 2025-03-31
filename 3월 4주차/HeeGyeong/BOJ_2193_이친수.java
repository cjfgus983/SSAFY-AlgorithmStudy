import java.util.Scanner;

// dp 자료형을 int[][]로 설정하면 N=47 이상부터 오버플로 발생함.
public class BOJ_2193_이친수 {
  static Scanner sc;
  static long[][] dp;
  static int N;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    N = sc.nextInt(); // N자리
    dp = new long[91][2]; // 마지막 자리 숫자 0 or 1에 따른 경우의 수
    dp[1][1] = 1;
    dp[2][0] = 1;
    dp[3][0] = 1;
    dp[3][1] = 1;

    for (int idx = 4; idx <= N; idx++) {
      dp[idx][0] = dp[idx - 1][0] + dp[idx - 1][1];
      dp[idx][1] = dp[idx - 1][0];

    }

    System.out.println(dp[N][0] + dp[N][1]);
  }

}
