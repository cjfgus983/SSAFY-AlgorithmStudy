package BOJ11052;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        dp[1] = arr[1];
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + arr[i-j]);
            }
        }
        System.out.println(dp[n]);
    }
}
