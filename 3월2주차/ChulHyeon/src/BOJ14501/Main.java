package BOJ14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] t;
    static int[] p;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        // 입력
        t = new int[n + 1];
        p = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            // 상담을 하는 경우, 퇴사 전에 끝나는지 확인 후 갱신
            if (i + t[i] <= n + 1) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            } // i == 1이고 t[1] == 3 이니까 4일차(초반)의 최대값 갱신

            // i 날에 상담 안했을 때 다음 날로 넘기기
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }


        System.out.println(dp[n + 1]);
    }
}
