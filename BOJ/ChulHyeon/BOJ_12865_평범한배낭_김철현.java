package ChulHyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int itemCnt = Integer.parseInt(st.nextToken());
        int weightLimit = Integer.parseInt(st.nextToken());

        int values[] = new int[itemCnt + 1];
        int weights[] = new int[itemCnt +1];
        for(int i =1; i<=itemCnt; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int dp[][] = new int[itemCnt + 1][weightLimit + 1];
        for(int item = 1; item <= itemCnt; item++) {
            for(int k = 1; k <= weightLimit; k++) { // 허용 가능한 배낭의 무게가 1부터 시작한다고 생각

                // 현재 아이템의 무게
                int nowWeight = weights[item];
                // 현재 아이템의 가치
                int nowValue = values[item];
                if(k - nowWeight >= 0) { // 넣을 수 있을 때만
                    dp[item][k] = Math.max(dp[item - 1][k], dp[item - 1][k - nowWeight] + nowValue);
                }
                else
                {
                    dp[item][k] = dp[item - 1][k];
                }
            }
        }

        System.out.println(dp[itemCnt][weightLimit]);
    }
}
