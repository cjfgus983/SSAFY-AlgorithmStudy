package BOJ1463;

/*
*
* 3으로 나눌수 있으면 3으로 나누기
* 2로 나누어지면 2로 나누기
* 1을 뺴기
*
* 3개 중 하나
*
* N이 주어졌을 때 3개 중 하나 써서 1로 만들기
*
* */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // dp 설정 해서 최소 카운트 갱신 되면 해당 걸로 진행
        int dp[] = new int[n + 1];
        // 초기화
        Arrays.fill(dp, Integer.MAX_VALUE - 10);
        dp[n] = 0;

        for(int i=n;i>=1;i--)
        {
            // 1을 뺀 경우
            int tmp = i + 1;
            if(tmp > n)
                continue;
            dp[i] = Math.min(dp[i], dp[tmp] + 1);
            // 2로 나눈 경우
            tmp = i * 2;
            if(tmp > n)
                continue;
            dp[i] = Math.min(dp[i], dp[tmp] + 1);
            // 3으로 나눈 경우
            tmp = i * 3;
            if(tmp > n)
                continue;
            dp[i] = Math.min(dp[i], dp[tmp] + 1);
        }


        System.out.println(dp[1]);
    }
}
