package BOJ11726;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[1001];


        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3;i<=n;i++) {
            // 이전 꺼에서 ㅣ 자 하나 더하는 경우

            // 2개 전 꺼에서 ㅡ 자 두개 합치는 경우
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
