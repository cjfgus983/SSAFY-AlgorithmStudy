import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int m;
    static int n;
    static int x;
    static int y;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;

            boolean flag = false;
            for (int i = x; i < (n * m); i += m) {
                if (i % n == y) {
                    ans.append(i + 1).append("\n");
                    flag = true;
                    break;
                }
            }

            if (!flag) ans.append(-1).append("\n");
        }

        System.out.println(ans);
    }
}