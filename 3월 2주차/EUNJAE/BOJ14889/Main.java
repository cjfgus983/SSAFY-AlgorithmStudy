package BOJ14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 스타트와 링크
 * 축구하자 평일오후 신청자만
 * N명있으니 (짝수)  N/2 명씩 팀짠다.
 * 능력치 Sij +Sji = i,j가 팀일때 팀에 추가되는능력치
 * 각 팀의 능력치차가 최소가 되도록 팀구성하기 (능력치 차의 최소값)
 * 풀이
 * N은 20이라, 2^20 정도만 선택하면 괜찮다. (대략백만?)
 * N/2 조합으로 A팀뽑고,나머지들 B팀으로 만든다.
 * 차이로 최솟값 갱신해보자 데헷ㅇㅇㅇㅇ
 */
public class Main {
	static int board[][];
	static int minValue = Integer.MAX_VALUE;
	static int N;
	static int [] teamA,teamB;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		teamA = new int[N/2];
		teamB = new int[N/2];
		for(int i = 0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		dfs(0,0,0);
		System.out.println(minValue);
	}
	
	static void dfs(int elementIndex, int indexA,int indexB) {
		if (indexA == N/2) {
			for(int i = elementIndex;i<N;i++) {
				teamB[indexB] = i;
				indexB+=1;
			}
			minValue= Math.min(minValue,Math.abs(getPoint(teamA)-getPoint(teamB)));
			return;
		}
		if (indexB == N/2) {
			for(int i = elementIndex;i<N;i++) {
				teamA[indexA] = i;
				indexA+=1;
			}
			minValue= Math.min(minValue,Math.abs(getPoint(teamA)-getPoint(teamB)));
			return;
		}
		//A팀으로 영입
		teamA[indexA] = elementIndex;
		dfs(elementIndex+1,indexA+1,indexB);
		teamB[indexB] = elementIndex;
		dfs(elementIndex+1,indexA,indexB+1);
	}
	
	static int getPoint(int Team[]) {
		int total =0;
		for(int i = 0; i<N/2;i++) {
			for(int j= i+1; j<N/2;j++) {
				int x= Team[i];
				int y= Team[j];
				total+=board[x][y]+board[y][x];
			}
		}
		return total;
	}
}
