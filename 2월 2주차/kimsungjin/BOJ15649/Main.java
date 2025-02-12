import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<String> ans = new ArrayList<>();

    static void solve(boolean[] visited, int cnt, StringBuilder num) {
        if (cnt == m) {
            ans.add(num.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            StringBuilder nextNum = new StringBuilder(num);
            nextNum.append(i).append(" ");
            solve(visited, cnt + 1, nextNum);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[n + 1];

        solve(visited, 0, new StringBuilder());
        Collections.sort(ans);

        for (String str : ans) {
            System.out.println(str);
        }
    }
}