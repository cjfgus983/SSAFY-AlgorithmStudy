package BOJ18290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static int result = Integer.MIN_VALUE;
    static void dfs(int cnt, int sum){
        if(k == cnt)
        {
            result = Math.max(result, sum);
            return;
        }
        for(int nowR = 0; nowR <n; nowR++)
        {
            for(int nowC = 0; nowC <m; nowC++)
            {
                // 현재 칸 방문했는지 확인
                if(visit[nowR][nowC]) continue;
                boolean canGo = true;
                for(int k=0;k<4;k++) // 인접한 칸 방문 확인
                {
                    int nextR = nowR + dr[k];
                    int nextC = nowC + dc[k];
                    if(nextR >= 0 && nextC >= 0 && nextR < n && nextC < m && visit[nextR][nextC]) // next가 범위 안에 있고
                    {
                        canGo = false;
                        break;
                    }
                }
                // canGo 가 true라면 dfs진행
                if(canGo == false)
                {
                    continue;
                }
                visit[nowR][nowC] = true;
                dfs(cnt+1, sum+map[nowR][nowC]);
                visit[nowR][nowC] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        // 맵 입력
        for(int i = 0; i < n; i++){
            String row = br.readLine();
            st = new StringTokenizer(row);
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dfs
        dfs(0, 0);
        System.out.println(result);
    }
}
