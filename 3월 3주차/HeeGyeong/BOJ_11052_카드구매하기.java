import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052_카드구매하기 {
  static BufferedReader br;
  static StringTokenizer st;
  static int[] cards, dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine()); // 구매하려 하는 카드 개수
    cards = new int[N + 1];
    dp = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int idx = 1; idx <= N; idx++) {
      int inputNum = Integer.parseInt(st.nextToken());
      cards[idx] = inputNum;
      dp[idx] = inputNum;
    }

    dp[1] = cards[1];

    for (int idx = 2; idx <= N; idx++) {
      for (int i = 1; i <= idx / 2; i++) {
        dp[idx] = Math.max(dp[idx - i] + dp[i], dp[idx]);

      }
    }

    System.out.println(dp[N]);
  }

}
