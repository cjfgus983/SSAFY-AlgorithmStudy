import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. (0,0) 에서 시작해서 쭉 돌거야
 * 2. dirR dirC 배열을 사용, 순서는 오른쪽, 아래, 왼쪽, 위
 * 3. map[nextR][nextC] == 0 일 동안 while 로 반복
 * 4. 위 조건에 안맞으면 방향 꺽어줘야 하는데  (nowDir + 1) % 4 하면 자연스럽게 방향전환 될 듯
 * 5. 끝나는 조건은 한 칸 채울 때마다 cnt++ 해서 입력받은 n*n 과 같아지면 출력하고 프로그램 종료 하도록 하자
 * 
 * 
 * ******** 방향을 바꿔줘야 하는 경우 
 * 1. 범위를 벗어난 경우 
 * 2. 다음칸이 이미 채워진 경우
 * 이 두 경우 방향을 바꿔줌과 동시에 현재 위치 (nowR, nowC) 도 갱신 해줘야 한다.
 * */

public class SWEA_1954_달팽이숫자_김철현 {
	
	static int dirR[] = {0,1,0,-1};
	static int dirC[] = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <=T;testCase++)
		{
			sb.append("#").append(testCase).append('\n');
			int mapSize = Integer.parseInt(br.readLine().trim());
			int[][] map = new int[mapSize][mapSize];
			int dir = 0;
			int fillCnt = 1;
			int nowR = 0;
			int nowC = 0;
			while(true)
			{
				if(fillCnt == mapSize * mapSize + 1) // 모든 칸을 채웠을 경우 출력하고 종료
				{
					for(int row=0;row<mapSize;row++)
					{
						for(int col =0; col<mapSize;col++)
						{
							sb.append(map[row][col]).append(" ");
						}
						sb.append('\n');
					}
					break;
				}
				// 현재칸 채워주고
				map[nowR][nowC] = fillCnt++;
				// 다음 칸 판별
				int nextR = nowR + dirR[dir];
				int nextC = nowC + dirC[dir];
				// 방향을 바꿔줄 조건들
				// 1. 범위가 밖으로 벗어나는 경우
				if(nextR < 0 || nextC < 0 || nextR >=mapSize||nextC >= mapSize)
				{
					dir = (dir + 1) % 4;
					nowR = nowR + dirR[dir];
					nowC = nowC + dirC[dir];
					continue;
				}
				// 2. 다음 칸이 이미 채워진 경우
				if(map[nextR][nextC] != 0)
				{
					dir = (dir + 1) % 4;
					nowR = nowR + dirR[dir];
					nowC = nowC + dirC[dir];
					continue;
				}
				// 현재 위치 갱신
				nowR = nextR;
				nowC = nextC;
			}
		}
		System.out.println(sb.toString());
	}
}
