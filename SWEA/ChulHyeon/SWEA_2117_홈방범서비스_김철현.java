import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 1. search 함수 이용해서 모든 행 열을 탐색한다.
 * 2. 탐색 해당 칸이 마름모의 중간 지점이다 라고 생각하고 해당 지점에서 bfs를 호출해서 마름모 범위 생성
 * 3. bfs에는 마름모 안에 들어있는 집의 개수가 리턴된다 
 * 4. 계산식을 이용해서 회사가 손해를 보지 않는다면 결과값(마름모 안의 집의 개수) 를 갱신한다.
 * 
 * */

public class SWEA_2117_홈방범서비스_김철현 {
	static int ans;
	static int size;
	static int value;
	static int[][] map;
	
	static void search()
	{
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				for(int threshold = 1; threshold <= size + 1; threshold++)
				{
					int sum = bfs(new Pos(row, col,1), threshold);
					int tmpAns = sum * value - ((threshold * threshold) + (threshold - 1) * (threshold - 1));
					if(tmpAns >= 0)
					{
						ans = Math.max(ans, sum);
					}
				}
			
			}
		}
	}
	
	static class Pos{
		int row;
		int col;
		int cnt;
		public Pos(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	
	static int[] dirR = {1,-1,0,0};
	static int[] dirC = {0,0,1,-1};
	
	static int bfs(Pos pos ,int threshold) {
		int sum = 0; // 현재 범위에서 집의 개수를 저장
		boolean visit[][] = new boolean[size][size]; // 방문 처리 위한 배열
		
		Queue<Pos> q = new LinkedList<>();
		
		visit[pos.row][pos.col] = true;
		
		q.add(pos);
		while(!q.isEmpty())
		{
			Pos nowPos = q.poll();
			int nowR = nowPos.row;
			int nowC = nowPos.col;
			int nowCnt = nowPos.cnt;

			
			if(map[nowR][nowC] == 1)
			{
				sum++;
			}
			if(nowCnt == threshold) // k 범위에 다다르면 다음 위치 넣지 말고 스킵
			{
				continue;
			}
			for(int dir = 0; dir < 4; dir++) {
				int nextR = nowR + dirR[dir];
				int nextC = nowC + dirC[dir];
				int nextCnt = nowCnt + 1;
				// 범위 처리
				if(nextR < 0 || nextC <0 || nextR >= size || nextC >=size) continue;
				// 방문 처리
				if(visit[nextR][nextC]) continue;
				
				visit[nextR][nextC] = true;
				q.add(new Pos(nextR, nextC, nextCnt));
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
        	// 초기화
        	ans = 0;
        	st = new StringTokenizer(br.readLine().trim());
        	size = Integer.parseInt(st.nextToken());
        	value = Integer.parseInt(st.nextToken());
        	
        	// 맵 입력
        	map = new int[size][size];
        	for(int row = 0; row < size; row++) {
        		st = new StringTokenizer(br.readLine().trim());
        		for(int col = 0; col < size; col++) {
        			map[row][col] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	search();
        	
        	sb.append("#").append(testCase).append(" ").append(ans).append('\n');
        	
        }
        System.out.print(sb);
    }
}
