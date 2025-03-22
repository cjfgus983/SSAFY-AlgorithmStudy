import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n + 1];
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 10000);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = p[0];
        dp[1] = p[1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + p[i - j]);
            }
        }

        System.out.println(dp[n]);
    }
}