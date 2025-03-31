package BOJ15990;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[][] dp = new long[100001][4];

        dp[1][1] = 1;

        dp[2][2] = 1;

        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i-1][3] + dp[i-1][2]) % 1000000009;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % 1000000009;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % 1000000009;
        }

        for(int i = 0; i < n; i++) {
            int k = sc.nextInt();
            System.out.println((dp[k][1] + dp[k][2] + dp[k][3])%1000000009);
        }
    }
}
