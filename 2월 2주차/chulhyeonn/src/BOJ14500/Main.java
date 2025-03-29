package BOJ14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;
    static int[] dr = {1,-1 ,0, 0};
    static int[] dc = {0,0,1,-1};
    static int n;
    static int m;

    static int[][] map;
    static boolean[][] visit;

    static void dfs(int cnt,int sum, int nowR, int nowC){
        if(cnt == 4)
        {
            result = Math.max(result, sum);
            return;
        }
        for(int i = 0;i<4;i++)
        {
            int nextR = nowR + dr[i];
            int nextC = nowC + dc[i];
            // 범위 처리
            if(nextR < 0 || nextC < 0 || nextR >= n || nextC >= m)
                continue;
            // 방문 처리
            if(visit[nextR][nextC])
                continue;
            visit[nextR][nextC] = true;
            dfs(cnt+1,sum + map[nextR][nextC],nextR,nextC);
            visit[nextR][nextC] = false;
        }
    }

    static void specialCase(int nowR, int nowC){
        // ㅗ
        if(nowR-1>=0 && nowC-1>=0 && nowC+1<m)
        {
            int sum = map[nowR][nowC] + map[nowR][nowC-1] + map[nowR][nowC+1] + map[nowR - 1][nowC];
            result = Math.max(result, sum);
        }
        // ㅜ
        if(nowR+1<n && nowC-1>=0 && nowC+1<m)
        {
            int sum = map[nowR][nowC] + map[nowR][nowC-1] + map[nowR][nowC+1] + map[nowR + 1][nowC];
            result = Math.max(result, sum);
        }
        // ㅓ
        if(nowR-1>=0 && nowC-1>=0 && nowR + 1<n)
        {
            int sum = map[nowR][nowC] + map[nowR][nowC-1] + map[nowR + 1][nowC] + map[nowR - 1][nowC];
            result = Math.max(result, sum);
        }
        // ㅏ
        if(nowR-1>=0 && nowC+1< m && nowR + 1<n)
        {
            int sum = map[nowR][nowC] + map[nowR][nowC+1] + map[nowR + 1][nowC] + map[nowR - 1][nowC];
            result = Math.max(result, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 입력 구간
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                visit[i][j] = true;
                dfs(1, map[i][j], i,j);
                visit[i][j] = false;
                // ㅗ ㅜ ㅓ ㅏ 는 dfs로 안됨
                specialCase(i, j);
            }
        }
        System.out.println(result);
    }
}
