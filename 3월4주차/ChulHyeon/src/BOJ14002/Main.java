package BOJ14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        int[] dp = new int[n];

        List<Integer> resultArr[] = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            resultArr[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine().trim());
        for(int i=0;i < n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            resultArr[i].add(arr[i]);
        }
        // 인덱스 2번부터 이전 인덱스를 뒤지며 현재 자기 위치보다 작은걸 찾아서 dp 갱신

        int result = 0;
        int resultIdx = 0;

        for(int i=0;i < n ; i++)
        {
            for(int j=i-1;j>=0;j--)
            {
                if(arr[j] < arr[i]){
                    if(dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;
                        // 이 리스트도 변경해야함
                        resultArr[i] = new ArrayList<>(resultArr[j]); // 새로 만들어서 배열 넣는 느낌
                        resultArr[i].add(arr[i]);
                    }
                }
            }
            // 최대값 갱신
            if(dp[i] > result)
            {
                result = dp[i];
                resultIdx = i;
            }
        }
        System.out.println(result);
        for(int num : resultArr[resultIdx]){
            sb.append(num).append(' ');
        }
        System.out.println(sb);
    }
}

