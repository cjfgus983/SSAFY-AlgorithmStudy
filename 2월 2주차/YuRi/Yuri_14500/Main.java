
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N,M;
	static int Max = 0;
	static boolean[][] visited;
	static int[] dx = {-1, 0 ,1,0};
	static int[] dy = {0,1,0,-1};
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr= new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j< M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i =0;i<N;i++){
			for(int j =0;j<M;j++) {
				visited[i][j] = true;
				sol(1,arr[i][j],i,j);
				visited[i][j] = false;
			}
		}
		
		System.out.println(Max);
		
	}
	
	public static void sol(int num, int now_sum,int x, int y) {
		if(num ==4) {
			Max = Math.max(Max, now_sum);
			return;
		}
		
		for(int i =0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y +dy[i];
			
			if(nx >=N || nx<0 ||ny>=M || ny<0) continue;
			
			if(!visited[nx][ny]) {
				if(num==2) {
					visited[nx][ny] = true;
					sol(num+1, now_sum+arr[nx][ny], x,y);
					visited[nx][ny] = false;
				}
				visited[nx][ny] = true;
				sol(num+1, now_sum+arr[nx][ny], nx,ny);
				visited[nx][ny] = false;
				
			}
		}
	}
}
