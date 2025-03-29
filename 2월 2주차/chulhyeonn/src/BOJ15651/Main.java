package BOJ15651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 한번에 출력 해보기

public class Main {
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    static void dfs(int n, int m, int cnt){
        if(cnt == m)
        {
            for(int i : result)
            {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 1; i <= n; i++)
        {
            result[cnt] = i;
            dfs(n, m, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        result = new int[m];
        dfs(n, m, 0);

        System.out.println(sb.toString());
    }
}
