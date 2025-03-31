package BOJ15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] arr;
    static int[] result;
    static boolean[] visit;
    static void dfs(int n, int m, int cnt){
        if(cnt == m)
        {
            for(int i : result){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0 ; i < n ; i++){
            if(visit[i])
                continue;
            visit[i] = true;
            result[cnt] = arr[i];
            dfs(n,m,cnt+1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(n,m,0);
        System.out.println(sb.toString());
    }
}
