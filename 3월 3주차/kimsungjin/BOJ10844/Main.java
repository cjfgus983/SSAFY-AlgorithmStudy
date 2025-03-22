import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1000000000;
        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[101][10];

        dp[1][0] = 0L;
        dp[1][1] = 1L;
        dp[1][2] = 1L;
        dp[1][3] = 1L;
        dp[1][4] = 1L;
        dp[1][5] = 1L;
        dp[1][6] = 1L;
        dp[1][7] = 1L;
        dp[1][8] = 1L;
        dp[1][9] = 1L;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % MOD + dp[i - 1][j + 1] % MOD) % MOD;
            }
            dp[i][9] = dp[i - 1][8];
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (dp[n][i] % MOD);
        }
        System.out.println(sum % MOD);
    }
}