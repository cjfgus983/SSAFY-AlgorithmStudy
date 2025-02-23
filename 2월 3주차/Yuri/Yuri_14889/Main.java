package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int result= Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 0; j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solution(0,0);
		
		System.out.println(result);
		
	}
	
	public static void solution(int now,int index) {
		//System.out.println(now + " " + index);
		if(now == N/2) {
			getScore();
			return;
		}
		
		if(index == N) return;
		
		/*
		for(int i =0; i<N-1;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			for(int j = i+1;j<N;j++) {
				if(visited[j]) continue;
				visited[j] = true;
				int hab=0;
				for(int now1 : arr1) hab+= arr[now1][i] + arr[i][now1];
				for(int now2 : arr2) hab-= arr[now2][j] + arr[j][now2];
				solution(now+1,new ArrayList(arr1.add(i)), arr2.add(j),nowResult+hab);
				
			}
		}*/
		
		if(!visited[index]) {
			visited[index] = true;
			solution(now+1,index+1);
			visited[index] = false;
		}
		solution(now, index+1); 
		
	}
	
	
	public static void getScore() {
		int hab=0;
		
		for(int i = 0;i<N-1;i++) {
			for(int j = i+1; j<N;j++) {
				if(visited[i] && visited[j]) hab+= arr[i][j] + arr[j][i];
				else if(!visited[i] && !visited[j]) hab-= arr[i][j] + arr[j][i];
			}
		}
		
		result = Math.min(result, Math.abs(hab));
	}
}
