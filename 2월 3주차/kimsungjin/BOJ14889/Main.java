import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] S;
    static int[] start;
    static int[] link;
    static int min = Integer.MAX_VALUE;

    static void calc() {
        int startStat = 0;
        int linkStat = 0;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                startStat += S[start[i]][start[j]];
                linkStat += S[link[i]][link[j]];
            }
        }

        min = Math.min(min, Math.abs(startStat - linkStat));
    }

    static void recur(int startCnt, int linkCnt, int player) {
        if (startCnt == n / 2 && linkCnt == n / 2) {
            calc();
            return;
        }
        try {
            start[startCnt] = player;
            recur(startCnt + 1, linkCnt, player + 1);
            start[startCnt] = 0;

            link[linkCnt] = player;
            recur(startCnt, linkCnt + 1, player + 1);
            link[linkCnt] = 0;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        S = new int[n][n];
        start = new int[n / 2];
        link = new int[n / 2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        System.out.println(min);
    }
}