package BOJ15661;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 스타트와 링크
 * 축구하자 평일오후 신청자만
 * N명있다.
 * 능력치 Sij +Sji = i,j가 팀일때 팀에 추가되는능력치
 * 각 팀의 능력치차가 최소가 되도록 팀구성하기 (능력치 차의 최소값)
 * 풀이
 * N은 20이라, 2^20 정도만 선택하면 괜찮다. (대략백만?)
 * A팀 B팀을 따로 선택한다.
 * 차이로 최솟값 갱신해보자
 */
public class Main {
	static int board[][];
	static int minValue = Integer.MAX_VALUE;
	static int N;
	static int [] teamA,teamB;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		teamA = new int[N];
		teamB = new int[N];
		for(int i = 1; i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		dfs(1,0,0);
		System.out.println(minValue);
	}
	
	static void dfs(int elementIndex, int indexA,int indexB) {
		
		if (indexA+indexB == N) {
//			System.out.printf("team A : %s, %d point \nteam B: %s %d point\n",
//					Arrays.toString(teamA),getPoint(teamA),Arrays.toString(teamB),getPoint(teamB));
			minValue= Math.min(minValue,Math.abs(getPoint(teamA)-getPoint(teamB)));
			return;
		}
		//A팀으로 영입
		teamA[indexA] = elementIndex;
		dfs(elementIndex+1,indexA+1,indexB);
		teamA[indexA] = 0;
		teamB[indexB] = elementIndex;
		dfs(elementIndex+1,indexA,indexB+1);
		teamB[indexB] = 0;
	}
	
	static int getPoint(int Team[]) {
		int total =0;
		for(int i = 0; i<N;i++) {
			int x= Team[i];
			for(int j= i+1; j<N;j++) {
				int y= Team[j];
				total+=board[x][y]+board[y][x];
			}
		}
		return total;
	}
}