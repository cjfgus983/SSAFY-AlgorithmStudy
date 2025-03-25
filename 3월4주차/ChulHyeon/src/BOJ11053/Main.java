package BOJ11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        int[] dp = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0;i < n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        // 인덱스 2번부터 이전 인덱스를 뒤지며 현재 자기 위치보다 작은걸 찾아서 dp 갱신

        int result = 0;

        for(int i=0;i < n ; i++)
        {
            for(int j=i-1;j>=0;j--)
            {
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 최대값 갱신
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
