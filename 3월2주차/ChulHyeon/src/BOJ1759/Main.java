package BOJ1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int arrlen;
    static int alphaCnt;
    static char[] arr;
    static char[] result;
    static boolean[] visit;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int cnt, int start) {
        if(cnt == arrlen) {
            boolean success = false;
            int v_count = 0;
            for(int idx=0; idx < arrlen; idx++) {
                if (result[idx] == 'a' || result[idx] == 'e' || result[idx] == 'i' || result[idx] == 'o' || result[idx] == 'u')
                    v_count++;
            }
            if (v_count >= 1 && arrlen - v_count >= 2)
                success = true;
            if (success)
            {
                for (int i = 0; i < arrlen; i++)
                {
                    sb.append(result[i]);
                }
                sb.append('\n');
            }
        }

        for(int idx = start; idx < alphaCnt; idx++) {
            visit[idx] = true;
            result[cnt] = arr[idx];
            dfs(cnt + 1, idx + 1);
            visit[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine().trim());
        arrlen = Integer.parseInt(st.nextToken());
        alphaCnt = Integer.parseInt(st.nextToken());

        arr = new char[alphaCnt];
        result = new char[alphaCnt];
        visit = new boolean[alphaCnt];

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=  0; idx < alphaCnt; idx++) {
            arr[idx] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr); // 정렬

        dfs(0,0);


        System.out.println(sb.toString());
    }
}
