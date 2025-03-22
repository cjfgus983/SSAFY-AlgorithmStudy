import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        long[][] dp = new long[100001][3];

        dp[1][0] = 1L;
        dp[1][1] = 0L;
        dp[1][2] = 0L;

        dp[2][0] = 0L;
        dp[2][1] = 1L;
        dp[2][2] = 0L;

        dp[3][0] = 1L;
        dp[3][1] = 1L;
        dp[3][2] = 1L;

        for (int i = 4; i <= 100000; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 2][0] + dp[i - 2][2]) % MOD;
            dp[i][2] = (dp[i - 3][0] + dp[i - 3][1]) % MOD;
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println((dp[n][0] % MOD + dp[n][1] % MOD + dp[n][2] % MOD) % MOD);
        }
    }
}