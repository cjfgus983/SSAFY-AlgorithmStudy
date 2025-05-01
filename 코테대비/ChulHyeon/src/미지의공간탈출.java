import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미지의공간탈출 {
    static class Pos3d{
        int row;
        int col;
        int dimension; // 0 동 1 서 2 남 3 북 4 위
        int cnt;
        public Pos3d(int row, int col, int dimension, int cnt) {
            this.row = row;
            this.col = col;
            this.dimension = dimension;
            this.cnt = cnt;
        }
    }

    static class BlackHolePos{
        int row;
        int col;
        int dir;
        int time;
        public BlackHolePos(int row, int col, int dir, int time) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.time = time;
        }
    }

    static int mapSize;
    static int timeWallSize;
    static int blackholeCnt;

    static int map[][];
    static int timeMap[][][];

    static Pos3d start3dPos;

    static int blackHoleMap[][][];

    static long result = -1;

    // 동서남북
    static int dr[] = {0,0,1,-1};
    static int dc[] = {1,-1,0,0};

    static void bfs3d(){
        // 1. 333 시작점인 베이스 행, 열 찾기
        int baseR = 0;
        int baseC = 0;
        boolean flag = false;
        for(int i = 0; i < mapSize; i++){
            if(flag) break;
            for(int j = 0; j < mapSize; j++){
                if(map[i][j] == 3)
                {
                    baseR = i;
                    baseC = j;
                    flag = true;
                    break;
                }
            }
        }
        // 2. 시작점은 찾아둠
        // 3. 종료할 점 찾기
        Pos3d endPos = new Pos3d(0,0,0,0);
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(map[i][j]==3){
                    for(int dir =0; dir<4;dir++){
                        int nextR = i+dr[dir];
                        int nextC = j+dc[dir];
                        // 범위 체크
                        if(nextR < 0 || nextR >= mapSize || nextC < 0 || nextC >= mapSize) continue;
                        // 주위에 0 있으면 해당 점이 종료 지점
                        if(map[nextR][nextC] == 0){
                            if(dir == 0) // 동쪽
                            {
                                int endR = baseR;
                                int endC = (timeWallSize - 1) - (i - baseR);
                                endPos.row = endR;
                                endPos.col = endC;
                                endPos.dimension = 0;
                            }
                            else if(dir == 1){ // 서쪽
                                int endR = baseR;
                                int endC = i - baseR;
                                endPos.row = endR;
                                endPos.col = endC;
                                endPos.dimension = 1;
                            }
                            else if(dir == 2){ // 남쪽
                                int endR = baseR;
                                int endC = j - baseR;
                                endPos.row = endR;
                                endPos.col = endC;
                                endPos.dimension = 2;
                            }
                            else if(dir == 3){ // 북쪽
                                int endR = baseR;
                                int endC = (timeWallSize - 1) - (j - baseR);
                                endPos.row = endR;
                                endPos.col = endC;
                                endPos.dimension = 3;
                            }
                        }
                    }
                }
            }
        }
        // 4. 종료점까지 bfs
        boolean visit[][][] = new boolean[5][timeWallSize][timeWallSize];
        Queue<Pos3d> q = new LinkedList<>();
        // 시작점 넣기
        visit[4][start3dPos.row][start3dPos.col] = true;
        q.add(start3dPos);
        while(!q.isEmpty()){
            Pos3d nowPos = q.poll();
            int nowR = nowPos.row;
            int nowC = nowPos.col;
            int nowDir = nowPos.dimension;
            int nowCnt = nowPos.cnt;

            // 기저 조건
            if(nowR == endPos.row && nowC == endPos.col && nowDir == endPos.dimension){
                System.out.println(start3dPos.row + "" + start3dPos.col + "" + start3dPos.dimension);
                System.out.println(endPos.row + "" + endPos.col + "" + endPos.dimension);
                System.out.println("3d끝에 도착했습니다");
                System.out.println(nowPos.cnt);
            }
            for(int dir =0;dir<4;dir++){
                int nextR = nowR+dr[dir];
                int nextC = nowC+dc[dir];
                int nextDir = 0;
                int nextCnt = nowCnt + 1;
                // 범위 벗어났을 때
                if (nextR < 0 || nextR >= timeWallSize || nextC < 0 || nextC >= timeWallSize) {
                    int M = timeWallSize;

                    if (nextR < 0) { // 위쪽 이탈
                        if (nowDir == 0) {
                            nextDir = 4;
                            nextR = (M - 1) - nowC;
                            nextC = M - 1;
                        } else if (nowDir == 1) {
                            nextDir = 4;
                            nextR = nowC;
                            nextC = 0;
                        } else if (nowDir == 2) {
                            nextDir = 4;
                            nextR = M - 1;
                            nextC = nowC;
                        } else if (nowDir == 3) {
                            nextDir = 4;
                            nextR = 0;
                            nextC = (M - 1) - nowC;
                        } else if (nowDir == 4) {
                            nextDir = 3;
                            nextR = 0;
                            nextC = (M - 1) - nowC;
                        } else continue;
                    }
                    else if (nextR >= M) { // 아래쪽 이탈
                        if (nowDir == 4) {
                            nextDir = 2;
                            nextR = 0;
                            nextC = nowC;
                        } else continue;
                    }
                    else if (nextC < 0) { // 왼쪽 이탈
                        if (nowDir == 4) {
                            nextDir = 1;
                            nextR = 0;
                            nextC = nowR;
                        } else {
                            nextDir = (nowDir + 1) % 4; // left_nxt
                            nextR = nowR;
                            nextC = M - 1;
                        }
                    }
                    else if (nextC >= M) { // 오른쪽 이탈
                        if (nowDir == 4) {
                            nextDir = 0;
                            nextR = 0;
                            nextC = (M - 1) - nowR;
                        } else {
                            nextDir = (nowDir + 3) % 4; // right_nxt
                            nextR = nowR;
                            nextC = 0;
                        }
                    }
                    else {
                        // 정상 범위 내면, 그대로 진행
                        nextDir = nowDir;
                    }
                }


                // 방문 체크
                if(visit[nextDir][nextR][nextC]) continue;

                // 지나갈 수 있나 체크
                if(timeMap[nextDir][nextR][nextC] == 1) continue;

                System.out.println(dir +"로이동: 현재면 " + nowDir + " (" + nowR + "," + nowC + ") → 다음면 " + nextDir + " (" + nextR + "," + nextC + ")");


                visit[nextDir][nextR][nextC] = true;
                q.add(new Pos3d(nextR, nextC , nextDir, nextCnt));

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        timeWallSize = Integer.parseInt(st.nextToken());
        blackholeCnt = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[mapSize][mapSize];
        timeMap = new int[5][timeWallSize][timeWallSize];
        // 블랙홀 위치 초기화
        blackHoleMap = new int[mapSize][mapSize][100];


        // 기본 맵 입력
        for(int row = 0; row < mapSize; row++)
        {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0;col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        // 시간의 벽 입력
        for(int dir = 0; dir <= 4; dir++) {
            for(int row = 0; row < timeWallSize; row++)
            {
                st = new StringTokenizer(br.readLine().trim());
                for(int col = 0;col < timeWallSize; col++) {
                    timeMap[dir][row][col] = Integer.parseInt(st.nextToken());
                    if(timeMap[dir][row][col] == 2) // 타임머신은 시간의 벽 윗면 어딘가에 위치한다.
                    {
                        start3dPos = new Pos3d(row, col, 4, 0);
                    }
                }
            }
        }
        // 이상 현상 입력
        for(int i = 0; i < blackholeCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // 맵에도 블랙홀 표시 해야지
        }

        bfs3d();

    }
}