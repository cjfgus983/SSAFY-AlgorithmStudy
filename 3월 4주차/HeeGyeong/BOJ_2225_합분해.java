import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225_합분해 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] dp = new int[N + 1][K + 1]; // 정수 k개를 더해서 그 합이 N이 되는 경우의 수

    // 자기 자신 1번 더한 경우 
    for (int idx = 1; idx <= N; idx++) {
      dp[idx][1] = 1;
    }
    for (int k = 1; k <= K; k++) { // 합이 0일때 모두 1로 초기화
      dp[0][k] = 1;
    }

    for (int k = 2; k <= K; k++) { // k개
      for (int n = 1; n <= N; n++) { // 합 n
        dp[n][k] = (dp[n][k - 1] + dp[n - 1][k]) % 1_000_000_000;
      }
    }

    System.out.println(dp[N][K]);
  }

}
