package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	//static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//visited = new boolean[N+1];
		arr= new int[M];
		
		nm(N,M, 0,1);
		
	}
	
	public static void nm(int n, int m, int now, int start) {
		if(m==now) {
			for(int a :arr) {
				System.out.print(a+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start;i<=n;i++) {
			/*
			if(!visited[i]) {
				visited[i] =true;
				arr[now] = i;
				nm(n,m,now+1);
				visited[i] =false;
			}
			*/
			
			arr[now] = i;
			nm(n,m,now+1,i+1);
		}
		
		return;
	}

}
