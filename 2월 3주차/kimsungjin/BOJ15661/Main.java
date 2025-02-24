import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] S;
    static int[] start;
    static int[] link;
    static int min = Integer.MAX_VALUE;

    static void calc(int startCnt, int linkCnt) {
        int startStat = 0;
        for (int i = 0; i < startCnt; i++) {
            for (int j = 0; j < startCnt; j++) {
                startStat += S[start[i]][start[j]];
            }
        }

        int linkStat = 0;

        for (int i = 0; i < linkCnt; i++) {
            for (int j = 0; j < linkCnt; j++) {
                linkStat += S[link[i]][link[j]];
            }
        }

        min = Math.min(min, Math.abs(startStat - linkStat));
    }

    static void recur(int startCnt, int linkCnt, int player) {
        if (player == n) {
            calc(startCnt, linkCnt);
            return;
        }
        try {
            start[startCnt] = player;
            recur(startCnt + 1, linkCnt, player + 1);
            start[startCnt] = 0;

            link[linkCnt] = player;
            recur(startCnt, linkCnt + 1, player + 1);
            link[linkCnt] = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        S = new int[n][n];
        start = new int[n];
        link = new int[n];

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