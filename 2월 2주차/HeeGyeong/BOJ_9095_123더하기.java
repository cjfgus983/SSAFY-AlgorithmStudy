import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095_123더하기 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < T; tc++) {
      int n = Integer.parseInt(br.readLine());
      int[] dp = new int[12];

      dp[1] = 1;
      dp[2] = 2;
      dp[3] = 4;

      for (int i = 4; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
      }

      System.out.println(dp[n]);
    }
  }

}
