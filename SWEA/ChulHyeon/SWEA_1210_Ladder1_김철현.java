import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;;

/*
 * 2가 도착점인데 거기를 시작점으로 해서 탐색하자
 * 1. 아래로 내려갈 필요가 없으므로 위, 왼, 오 로 탐색 할 것.
 * 2. 왼쪽 혹은 오른쪽에 사다리가 있을 경우 위로 가는 것보다 사다리를 타는게 우선순위가 높으므로 dir 배열에서 위를 맨 나중에 : 왼->오->위
 * 3. nowR 현재 탐색 행이 0일 때 반복문 종료 해서 nowCol을 리턴
 * */

public class SWEA_1210_Ladder1_김철현 {
	
	static int[][] map = new int[100][100]; // 사다리 맵 저장
	static boolean[][] visit =  new boolean[100][100]; // 방문 기록 저장
	static int dirR[] = {0,0,-1}; // 왼 오 위
	static int dirC[] = {-1,1,0};
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= 10;testCase++)
		{
			// 처음에 쓰레기값 하나 입력
			br.readLine();
			// 초기화
			for(int i=0;i<100;i++)
			{
				for(int j=0;j<100;j++)
				{
					visit[i][j] = false;
				}
			}
			// 목표 지점의 row col
			int nowR = 0;
			int nowC = 0;
			// 입력 받을 거야
			for(int row = 0;row<100;row++)
			{
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0;col<100;col++)
				{
					map[row][col] = Integer.parseInt(st.nextToken());
					if(map[row][col] == 2)
					{
						nowR = row;
						nowC = col;
					}
				}
			}
			nowR--; // 시작은 한칸 위에서 시작
			// 왼쪽 오른쪽에 사다리 없으면 계속 올라가고 
			while(nowR > 0)
			{
				for(int dir = 0;dir<3;dir++)
				{
					int nextR = nowR + dirR[dir];
					int nextC = nowC + dirC[dir];
					// 범위 체크
					if(nextR < 0 || nextC < 0 || nextR >= 100 || nextC >= 100)
					{
						continue;
					}
					// 이동  가능 체크
					if(map[nextR][nextC] == 0)
						continue;
					// 방문 체크
					if(visit[nextR][nextC])
						continue;
					visit[nextR][nextC] = true;
					nowR = nextR;
					nowC = nextC;
				}
			}
			sb.append("#").append(testCase).append(" ").append(nowC).append('\n');
		}
		System.out.println(sb.toString());
		
	}
}
