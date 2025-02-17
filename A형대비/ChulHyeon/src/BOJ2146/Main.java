package BOJ2146;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
    int row;
    int col;
    int dist;

    public Pair(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

public class Main {
    // 지도 크기
    static int[][] inputMap = new int[101][101];
    static int n; // 초기에 입력받는 맵의 크기
    static int[][] island = new int[101][101]; // 섬의 번호를 넣을 배열

    static int dr[] = {1,-1,0,0};
    static int dc[] = {0,0,1,-1};
    static boolean visit[][] = new boolean[101][101];

    // 섬에 번호를 부여하는 함수 - dfs
    static void checkIsland(int landNum, int nowR, int nowC)
    {
        visit[nowR][nowC] = true;
        island[nowR][nowC] = landNum;
        for(int i = 0; i < 4; i++)
        {
            int nextR = nowR + dr[i];
            int nextC = nowC + dc[i];
            // 범위 체크
            if(nextR < 0 || nextC < 0 || nextR >=n || nextC >=n)
                continue;
            // 방문 체크
            if(visit[nextR][nextC] == true)
                continue;
            if(inputMap[nextR][nextC] == 0)
                continue;
            checkIsland(landNum, nextR, nextC);
        }
    }

    static void initializeVisit()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                visit[i][j] = false;
            }
        }
    }

    static int bfs(int nowR, int nowC, int islandNum)
    {
        visit[nowR][nowC] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(nowR, nowC, 0)); // 초기화 한 번 해주고
        while(!q.isEmpty())
        {
            Pair nowPair = q.poll();
            int nowRow = nowPair.row;
            int nowCol = nowPair.col;
            int nowDist = nowPair.dist;
            for(int i = 0;i<4;i++)
            {
                int nextR = nowRow + dr[i];
                int nextC = nowCol + dc[i];
                // 범위 체크
                if(nextR < 0 || nextC < 0 || nextR >=n || nextC >=n)
                    continue;
                // 방문 체크
                if(visit[nextR][nextC] == true)
                    continue;
                // 다음 방향이 같은 섬인지 체크
                if(island[nextR][nextC] == islandNum)
                    continue;
                // 다음이 육지라면 리턴
                if(island[nextR][nextC] != 0)
                {
                    return nowDist;
                }
                if(island[nextR][nextC] == 0)// 다음이 바다라면 큐에 넣기
                {
                    visit[nextR][nextC] = true;
                    q.add(new Pair(nextR, nextC, nowDist+ 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 입력
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++)
            {
                inputMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 같은 섬에 번호 매기기
        int islandNum = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(inputMap[i][j] != 0 && visit[i][j] == false) // 바다가 아니고 방문 안했을 때
                {
                    checkIsland(islandNum, i, j);
                    islandNum++;
                }
            }
        }
        // visit 초기화
        initializeVisit();
        int result = Integer.MAX_VALUE;

        // 이제 bfs로 다른 섬 찾을때 까지 돌아야지
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(island[i][j] != 0) // 바다가 아니고 방문 안했을 때
                {
                    int tmp = bfs(i, j, island[i][j]);
                    result = Math.min(result, tmp);
                    initializeVisit();
                }
            }
        }
        System.out.println(result);
    }
}
