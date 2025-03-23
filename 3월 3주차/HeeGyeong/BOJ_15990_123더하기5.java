import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15990_123더하기5 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());
    int maxNum = 0;
    int[] input = new int[T];
    long[][] dp = new long[100_001][4];

    dp[1][1] = 1;
    dp[2][2] = 1;
    dp[3][1] = 1; // 합이 3으로 만드는 경우 중에 마지막으로 더해지는 수가 1인 경우의 수
    dp[3][2] = 1;
    dp[3][3] = 1;

    for (int tc = 0; tc < T; tc++) {
      input[tc] = Integer.parseInt(br.readLine());
      maxNum = Math.max(maxNum, input[tc]);
    }

    // 마지막으로 더하는 수를 1 or 2 or 3으로 나누어 생각.
    for (int i = 4; i <= maxNum; i++) {
      dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1_000_000_009;
      dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1_000_000_009;
      dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1_000_000_009;
    }

    for (int inputNum : input) {
      sb.append((dp[inputNum][1] + dp[inputNum][2] + dp[inputNum][3]) % 1_000_000_009).append('\n');
    }
    System.out.println(sb);
  }

}
