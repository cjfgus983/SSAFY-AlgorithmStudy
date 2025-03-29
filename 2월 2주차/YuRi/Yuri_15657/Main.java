package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[] arr2;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[10001];
		arr= new int[M];
		arr2 = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i =0;i<N;i++) arr2[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr2);
		nm(N,M, 0,1);
		
		System.out.println(sb);
		
	}
	
	public static void nm(int n, int m, int now,int start) {
		if(m==now) {
			for(int a :arr) {
				sb.append(a+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<=n;i++) {
			/*
			if(!visited[arr2[i-1]]) {
				visited[arr2[i-1]] =true;
				arr[now] = arr2[i-1];
				nm(n,m,now+1);
				visited[arr2[i-1]] =false;
			}*/
			
			
			arr[now] = arr2[i-1];
			nm(n,m,now+1,i);
			
		}
		
		return;
	}

}
