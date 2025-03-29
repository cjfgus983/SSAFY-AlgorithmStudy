package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int Max;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		arr = new int[N][M];
		Max = Integer.MIN_VALUE;
		
		
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		nm(N,M,K, 0,0);
		
		System.out.println(Max);
		
	}
	
	public static void nm(int n, int m,int k, int now,int sum) {
		if(k==now) {
			Max = Math.max(Max,sum);
			return;
		}
		
		for(int i=0;i<n;i++) {
			for(int j =0;j<m;j++) {
			/*
			if(!visited[arr2[i-1]]) {
				visited[arr2[i-1]] =true;
				arr[now] = arr2[i-1];
				nm(n,m,now+1);
				visited[arr2[i-1]] =false;
			}*/
			
			
				if(!visited[i][j]) {
					boolean flag = true;
					
					for(int a=0;a<4;a++) {
						int nx = i+dx[a];
						int ny = j+dy[a];
						
						if(nx<0||nx>=n||ny<0||ny>=m) continue;
						
						if(visited[nx][ny]) {
							flag=false;
							break;
						}
					}
					if(flag) {
						visited[i][j] = true;
						nm(n,m,k,now+1,sum+arr[i][j]);
						visited[i][j] = false;
					}
				}
			}
			
		}
		
		return;
	}

}
