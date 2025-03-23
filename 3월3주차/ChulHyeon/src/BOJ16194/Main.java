package BOJ16194;

import java.util.Arrays;
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
        Arrays.fill(dp, 99999);
        dp[1] = arr[1];
        dp[0] = 0;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + arr[i-j]);
            }
        }
        System.out.println(dp[n]);
    }
}
