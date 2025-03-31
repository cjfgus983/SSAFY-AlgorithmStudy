import java.util.Scanner;

public class BOJ_1699_제곱수의합 {
  static Scanner sc;
  static int[] dp;
  static int N;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    N = sc.nextInt(); // 주어진 자연수
    dp = new int[N + 1]; // 해당 수를 표현할 수 있는 최소 제곱수 항

    // 가능한 제곱수항의 최대값인 자기 자신으로 초기화
    for (int i = 1; i <= N; i++) {
      dp[i] = i;
    }

    for (int idx = 0; idx <= N; idx++) {
      for (int n = 1; idx + (n * n) <= N; n++) { // (현재 수 + 제곱수)가 N이하인 경우만 확인
        if (idx + n * n <= N && dp[idx + n * n] > dp[idx] + 1) {
          dp[idx + n * n] = dp[idx] + 1;
        }
      }
    }

    System.out.println(dp[N]);
  }

}
