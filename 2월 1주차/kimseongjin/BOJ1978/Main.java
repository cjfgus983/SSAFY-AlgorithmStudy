package BOJ1978;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] prime = new boolean[1001];
        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i <= 1000; i++) {
            if (!prime[i]) continue;
            for (int j = 2; i * j <= 1000; j++) {
                prime[i * j] = false;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(prime[Integer.parseInt(st.nextToken())]) cnt++;
        }

        System.out.println(cnt);
    }
}