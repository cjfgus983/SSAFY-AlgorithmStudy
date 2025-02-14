// [15654 N과 M (5) - 25.02.14]

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] num;
    static List<String> ans = new ArrayList<>();

    static void solve(boolean[] visited, int cnt, StringBuilder sb) {
        if (cnt == m) {
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            StringBuilder nextNum = new StringBuilder(sb);
            nextNum.append(num[i]).append(" ");
            solve(visited, cnt + 1, nextNum);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        boolean[] visited = new boolean[n];
        solve(visited, 0, new StringBuilder());

        for (String str : ans) {
            System.out.println(str);
        }
    }
}
