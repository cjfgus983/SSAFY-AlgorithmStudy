package BOJ17425;
import java.io.*;

public class Main {
    static int t;
    static long[] f;
    static long[] g;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        f = new long[1000001];
        g = new long[1000001];

        for (int i = 1; i <= 1000000; i++) {
            for (int j = 1; i * j <= 1000000; j++) {
                f[i * j] += i;
            }
        }

        for (int i = 1; i <= 1000000; i++) {
            g[i] = g[i - 1] + f[i];
        }

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            ans.append(g[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(ans);
    }
}