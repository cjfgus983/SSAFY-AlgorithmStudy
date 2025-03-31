package BOJ14500;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,-1,1};
	static int N,M;
	static int[][] board;
	static int total;
	static boolean[][] visited;
	static int ans = -1;
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				visited[i][j] = true;  // 방문 표시 추가
		        dfs(1, i, j, board[i][j], "");

		        visited[i][j] = false; // 원래 상태로 복구
		        ㅗ(i,j);			
			}
		}
		
		System.out.println(ans);
	}
 	
 	
	static void ㅗ(int r, int c) {
		int total =board[r][c];
		int min = Integer.MAX_VALUE;
		int cnt =0;
		for(int d=0;d<dr.length;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			//범위밖이거나, 방문했다면 스킵
			if(nr<0 || nr>=N || nc<0 || nc>=M) {
				continue;
			}
			cnt++;
			total += board[nr][nc];
			min = Math.min(min, board[nr][nc]);
		}
		if(cnt==4) {
		total -= min;
		}
		ans = Math.max(ans, total);	
	}
	
	static void dfs(int cnt,int r,int c,int total,String a) {	
//		a+="("+r+","+c+") ";
//		if(total>ans) {
//			System.out.println("현재 "+cnt+"개째, 좌표: ("+a+") total : "+total);
//			System.out.println("찾았다. "+total);
//		}

		if(cnt==4) {
			ans =Math.max(total, ans);
			//4번째 블록까지 봤다면, 리턴
			return;
		}
		else { //4번째 블록을 안봤다면, 4방탐색후 안간 블록을 방문
			for(int d=0;d<dr.length;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				//범위밖이거나, 방문했다면 스킵
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue;
				else {
					//아니라면 방문
					visited[nr][nc] = true;
					dfs(cnt+1,nr,nc,total+board[nr][nc],a);
					visited[nr][nc] = false;
				}
			}
		}
	}
}
