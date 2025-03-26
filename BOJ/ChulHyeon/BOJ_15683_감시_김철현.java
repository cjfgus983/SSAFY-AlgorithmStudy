import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
1번 : 한 쪽 방향 감시
2 : 서로 반대 방향 감시
3 : 직각 방향 감시
4 : 3방향 감시
5 : 4방향 감시
범위는 1칸이 아니라 범위 끝가지 무한 칸임
대각선은 고려 안하고 90도씩 시계방향 회전

6 : 벽을 의미

즉 최대한 많이 감시할 수 있는 부분을 찾아라
맵의 크기가 작기 때문에
백 트래킹을 먼저 진행한 뒤, 맵 전체의 0 개수를 찾아도 될 것 같음

1. class에 cctv의 위치정보, 타입, 방향을 넣음
2. 백 트래킹으로 cctv 의 방향을 매 기저마다 다르게 설정
3. 기저조건에 다다랐다면 checkRange() 함수를 이용해서 cctv방향에 따라 0을 -1 로 바꾸고 남은 0의 개수를 계산

type 1, 3, 4 => 4가지 방향
type 2 => 2가지 방향
type 5 => 1가지 방향

 */




public class BOJ_15683_감시_김철현 {
	
	static class Cctv{
		int row;
		int col;
		int type; 
		int dir; // 방향은 최대 0 1 2 3
		
		public Cctv(int row, int col, int type, int dir)
		{
			this.row = row;
			this.col = col;
			this.type = type;
			this.dir = dir;
		}
	}
	
	static int rowSize;
	static int colSize;
	
	static int[][] map;
	
	static int result = 99999;
	
	// 상 우 하 좌
	static int[] dr = {-1,0,1,0}; 
	static int[] dc = {0,1,0,-1};
	
	static int cnt = 0; // cctv의 개수를 저장 추후 기저조건으로 사용
	
	static List<Cctv> cctvs;
	
	// cctv의 범위를 구하는 함수
	static int checkRange() {	
		for(Cctv nowCctv :  cctvs)
		{
			// 현재 cctv의 타입에 따라 
			if(nowCctv.type == 1)
			{
				// 범위 안에 있고 벽을 만나기 전까지
				int nowR = nowCctv.row;
				int nowC = nowCctv.col;
				while(nowR >= 0 && nowC >= 0 && nowR < rowSize && nowC < colSize && map[nowR][nowC] != 6)
				{
					if(map[nowR][nowC] == 0)
					{
						map[nowR][nowC] = -1;
					}
					nowR = nowR + dr[nowCctv.dir];
					nowC = nowC + dc[nowCctv.dir];
				}
			}
			// 2번 타입 CCTV
			if(nowCctv.type == 2)
			{
			    int nowR = nowCctv.row;
			    int nowC = nowCctv.col;
			    // 1. 주 방향
			    int nextR = nowR;
			    int nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[nowCctv.dir];
			        nextC += dc[nowCctv.dir];
			    }
			    // 2. 반대 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 2) % 4];
			        nextC += dc[(nowCctv.dir + 2) % 4];
			    }
			}

			// 3번 타입 CCTV
			if(nowCctv.type == 3)
			{
			    int nowR = nowCctv.row;
			    int nowC = nowCctv.col;
			    // 1. 주 방향
			    int nextR = nowR;
			    int nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[nowCctv.dir];
			        nextC += dc[nowCctv.dir];
			    }
			    // 2. 90도 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 1) % 4];
			        nextC += dc[(nowCctv.dir + 1) % 4];
			    }
			}

			// 4번 타입 CCTV
			if(nowCctv.type == 4)
			{
			    int nowR = nowCctv.row;
			    int nowC = nowCctv.col;
			    // 1. 주 방향
			    int nextR = nowR;
			    int nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[nowCctv.dir];
			        nextC += dc[nowCctv.dir];
			    }
			    // 2. 90도 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 1) % 4];
			        nextC += dc[(nowCctv.dir + 1) % 4];
			    }
			    // 3. -90도 방향 (또는 (dir + 3) % 4)
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 3) % 4];
			        nextC += dc[(nowCctv.dir + 3) % 4];
			    }
			}

			// 5번 타입 CCTV
			if(nowCctv.type == 5)
			{
			    int nowR = nowCctv.row;
			    int nowC = nowCctv.col;
			    // 1. 주 방향
			    int nextR = nowR;
			    int nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[nowCctv.dir];
			        nextC += dc[nowCctv.dir];
			    }
			    // 2. 90도 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 1) % 4];
			        nextC += dc[(nowCctv.dir + 1) % 4];
			    }
			    // 3. -90도 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 3) % 4];
			        nextC += dc[(nowCctv.dir + 3) % 4];
			    }
			    // 4. 반대 방향
			    nextR = nowR;
			    nextC = nowC;
			    while(nextR >= 0 && nextC >= 0 && nextR < rowSize && nextC < colSize && map[nextR][nextC] != 6)
			    {
			        if(map[nextR][nextC] == 0)
			        {
			            map[nextR][nextC] = -1;
			        }
			        nextR += dr[(nowCctv.dir + 2) % 4];
			        nextC += dc[(nowCctv.dir + 2) % 4];
			    }
			}

		}
		
		int zeroCnt = 0;

		for(int row=0;row<rowSize;row++) {
			for(int col=0;col<colSize;col++) {
				if(map[row][col] == 0)
				{
					zeroCnt++;
				}
			}
		}
		
		return zeroCnt;
	}
	
	static void backTracking(int nowCnt) {
		if(nowCnt == cnt)
		{
			// 0의 개수 파악하고 리턴
			int sum = checkRange();
			// 0 의 개수 가장 적은 것을 result 로 갱신
			result = Math.min(result, sum);
			
			// 맵 체크해서 -1가 된 부분 다시 0으로 초기화
			for(int row = 0; row < rowSize;row++)
			{
				for(int col = 0; col < colSize; col++) {
					if(map[row][col] == -1)
						map[row][col] = 0;
				}
			}
			
			// 디버깅
//			for(Cctv cctv : cctvs)
//			{
//				System.out.print("행열" + cctv.row + " " + cctv.col + "인덱스" + nowCnt + "방향" + cctv.dir + "//");
//			}
//			System.out.println();
			return;
		}
		Cctv nowCctv = cctvs.get(nowCnt);
		// type 1 3 4 -> 방향 4가지
		
		if(nowCctv.type == 1 || nowCctv.type == 3 ||nowCctv.type == 4)
		{
			for(int d = 0; d < 4; d++){
			    nowCctv.dir = d;
			    backTracking(nowCnt + 1);
			}
		}
		
		// type 2 -> 방향 2가지
		if(nowCctv.type == 2)
		{
			for(int d = 0; d < 2; d++){
			    nowCctv.dir = d;
			    backTracking(nowCnt + 1);
			}
		}
		// type 5 -> 방향 1가지
		if(nowCctv.type == 5)
		{
			for(int d = 0; d < 1; d++){
			    nowCctv.dir = d;
			    backTracking(nowCnt + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 초기화 & 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		cctvs = new ArrayList<>();
		
		map = new int[rowSize][colSize];
		
		for(int row = 0; row < rowSize;row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col =0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] > 0 && map[row][col] < 6)
				{
					cnt++;
					cctvs.add(new Cctv(row, col, map[row][col], -1));
				}
			}
		}
		
		
		// 백 트래킹 시작
		backTracking(0);
		
		// 출력
		System.out.println(result);
	}
}
