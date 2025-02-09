package BOJ1929;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] prime = new boolean[1000001];
        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i <= 1000000; i++) {
            if (!prime[i]) continue;
            for (int j = 2; i * j <= 1000000; j++) {
                prime[i * j] = false;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        StringBuilder ans = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if(prime[i]) ans.append(i).append("\n");
        }

        System.out.println(ans);
    }
}