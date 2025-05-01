package ChulHyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 1원, 5원, 10원, 50원, 100원, 500원
* 주어진 금액을 만드는 모든 경우의 수
* */

public class BOJ_9084_동전_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int coinCnt = Integer.parseInt(br.readLine());
            int[] coinValue = new int[coinCnt + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= coinCnt; i++) {
                coinValue[i] = Integer.parseInt(st.nextToken());
            }
            int target = Integer.parseInt(br.readLine()); // 목표

            int dp[][] = new int[coinCnt + 1][10001];

            int ans = 0;

            // 초기화
            for (int i = 0; i <= coinCnt; i++) {
                dp[i][0] = 1;
            }

            for(int coin = 1; coin <= coinCnt; coin++) {
                for(int value = 1; value <= target; value++) { // 무게가 1씩 증가한다 생각해

                    int nowCoinValue = coinValue[coin];
                    if(value - nowCoinValue >= 0)
                    {
                        // 현재 동전을 안 쓰는 경우 + 현재 동전을 쓰는 경우
                        dp[coin][value] = dp[coin - 1][value] + dp[coin][value - nowCoinValue];
                    }
                    else
                    {
                        dp[coin][value] = dp[coin - 1][value];
                    }
                }
            }
            sb.append(dp[coinCnt][target]).append('\n');
        }
        System.out.println(sb.toString());
    }
}