package BOJ2193;

/*
 *
 * 0으로 시작 안함
 * 1이 두번 연속 안나옴
 *
 * */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dp = new long[n+1][2];

        dp[1][0] = 0;
        dp[1][1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }
        System.out.println(dp[n][0] + dp[n][1]);
    }
}