package BOJ10844;

import java.util.Scanner;

/*
*
* 1 => 9
* 0 으로 시작만 안하면 들어갈 수 있음
*
* */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][]dp = new int[n + 1][10];
        // 1자리 수 일 경우
        for(int i=1;i<=9;i++)
        {
            dp[1][i] = 1;
        }
        for(int i=2;i<=n;i++)
        {
            for(int j=0;j<=9;j++){
                if(j == 0)
                {
                    dp[i][j] = dp[i-1][j+1];
                    continue;
                }
                if(j == 9)
                {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1_000_000_000;
            }
        }

        // 출력
        long result = 0;
        for(int i=0;i<=9;i++)
        {
            result = (result + dp[n][i]) % 1_000_000_000;
        }
        System.out.println(result);
    }
}
