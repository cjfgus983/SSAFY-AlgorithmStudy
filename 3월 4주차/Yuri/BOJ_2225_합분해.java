import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225_합분해 {
    static final int MOD = 1_000_000_000;
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken()); // 만들 수
        K = Integer.parseInt(st.nextToken()); // 정수 개수

        dp = new int[K + 1][N + 1];

        // 초기 조건 설정
        for (int i = 0; i <= K; i++) {
            dp[i][0] = 1; // 0을 만드는 방법은 항상 1개 (모든 수가 0일 때)
        }

        for (int i = 1; i <= K; i++) { // i개 수로
            for (int j = 1; j <= N; j++) { // j를 만드는 방법
                for (int l = 0; l <= j; l++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - l]) % MOD;
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}

