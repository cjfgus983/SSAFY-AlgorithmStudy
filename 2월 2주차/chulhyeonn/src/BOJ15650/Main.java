package BOJ15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] result;

    static void dfs(int n, int m, int cnt, int now){
        if(cnt == m)
        {
            for(int i : result)
            {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        for(int i = now + 1; i <= n; i++)
        {
            result[cnt] = i;
            dfs(n, m, cnt + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        result = new int[m];
        for(int i = 1; i <= n; i++){
            result[0] = i;
            dfs(n,m,1, i);
        }
    }
}
