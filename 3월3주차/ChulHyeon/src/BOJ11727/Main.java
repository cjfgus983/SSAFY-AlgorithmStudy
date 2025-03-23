package BOJ11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[1001];


        dp[1] = 1;
        dp[2] = 3; // 세로세로 가로가로 2x2 하나
        dp[3] = 5;
        for(int i = 4;i<=n;i++) {
            // 2칸 전에서 2배
            dp[i] = (dp[i-2] * 2 + dp[i-1]) % 10007;
            // 1칸 전 그대로

        }
        System.out.println(dp[n]);
    }
}
