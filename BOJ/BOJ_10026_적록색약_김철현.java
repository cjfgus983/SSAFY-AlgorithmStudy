import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * bfs 호출 시 마다 결과 값을 1씩 증가시킨다.
 * 
	 색맹 전용의 bfs 와 
	 일반인 전용의 bfs를 둔다 .
	 
	 1. 일반인 전용의 bfs는 알파벳이 같아야만 하나의 덩어리로 본다.
	 2. 색맹 전용의 bfs는 알파벳이 R과 G가 같아도 하나의 덩어리로 보아야 하기 때문에
	 이 부분만 주의해서 큐에 넣기
 * */

public class BOJ_10026_적록색약_김철현 {

    static int size;
    static char[][] map;
    static boolean[][] visit;

    static int[] dirR = {1,-1,0,0};
    static int[] dirC = {0,0,1,-1};

    static class Pair{

        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 일반인 전용의 bfs
    static void bfs_ord(int startR, int startC) {
        char thisColor = map[startR][startC];

        Queue<Pair> q = new LinkedList<>();
        visit[startR][startC] = true;
        q.add(new Pair(startR, startC));
        while(!q.isEmpty())
        {
            Pair now = q.poll();
            int nowR = now.row;
            int nowC = now.col;
            for(int dir=0;dir<4;dir++) {
                int nextR = nowR + dirR[dir];
                int nextC = nowC + dirC[dir];
                // 범위 체크
                if(nextR < 0 || nextC < 0 || nextR >=size || nextC >= size) {
                    continue;
                }
                // 방문 체크
                if(visit[nextR][nextC])
                    continue;
                // 알파벳 체크
                if(thisColor == map[nextR][nextC])
                {
                    visit[nextR][nextC] = true;
                    q.add(new Pair(nextR, nextC));
                }
            }
        }
    }

    // 색맹 전용의 Bfs
    static void bfs_inord(int startR, int startC) {
        char thisColor = map[startR][startC];

        Queue<Pair> q = new LinkedList<>();
        visit[startR][startC] = true;
        q.add(new Pair(startR, startC));
        while(!q.isEmpty())
        {
            Pair now = q.poll();
            int nowR = now.row;
            int nowC = now.col;
            for(int dir=0;dir<4;dir++) {
                int nextR = nowR + dirR[dir];
                int nextC = nowC + dirC[dir];
                // 범위 체크
                if(nextR < 0 || nextC < 0 || nextR >=size || nextC >= size) {
                    continue;
                }
                // 방문 체크
                if(visit[nextR][nextC])
                    continue;
                // 알파벳 체크
                if((thisColor == 'R' || thisColor == 'G')&&(map[nextR][nextC] == 'R'||map[nextR][nextC] == 'G'))
                {
                    visit[nextR][nextC] = true;
                    q.add(new Pair(nextR, nextC));
                }
                else if(thisColor == 'B'&&map[nextR][nextC] == 'B')
                {
                    visit[nextR][nextC] = true;
                    q.add(new Pair(nextR, nextC));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 알고리즘 시작
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        map = new char[size][size];
        visit = new boolean[size][size];
        // 맵 입력
        for(int row = 0 ; row < size; row++) {
            String str = br.readLine().trim();
            for(int col = 0; col < size; col++) {
                map[row][col] = str.charAt(col);
            }
        }

        // 일반 처리
        int result_ord = 0;
        for(int row = 0 ; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(visit[row][col] == false)
                {
                    bfs_ord(row, col);
                    result_ord++;
                }
            }
        }
        // 방문 복구
        for(int row = 0 ; row < size; row++) {
            for(int col = 0; col < size; col++) {
                visit[row][col] = false;
            }
        }
        // 색약처리
        int result_inord = 0;
        for(int row = 0 ; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(visit[row][col] == false)
                {
                    bfs_inord(row, col);
                    result_inord++;
                }
            }
        }

        sb.append(result_ord).append(" ").append(result_inord);
        System.out.println(sb.toString());
    }
}