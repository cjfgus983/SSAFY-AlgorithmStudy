package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int k;
	static long Max, Min;
	static char[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine().trim());
		arr = new char[k];
		
		String[] str = br.readLine().trim().split(" ");
		for(int i=0; i <k;i++) {
			arr[i] = str[i].charAt(0);
		}
		
		Max = Integer.MIN_VALUE;
		Min = Integer.MAX_VALUE;
		visited = new boolean[10];
		
		for(int i=0;i<10;i++) {
			visited[i]= true;
			solution(0,String.valueOf(i));
			visited[i] = false;
		}
		
		if(String.valueOf(Max).length()<k+1)System.out.println("0"+Max);
		else System.out.println(Max);
		if(String.valueOf(Min).length()<k+1)System.out.println("0"+Min);
		else System.out.println(Min);
		
	}
	
	public static void solution(int count,String str) {
		
		//System.out.println(count +" " + str);
		if(count ==k) {
			long num = Long.parseLong(str);
			Min = Math.min(Min, num);
			Max = Math.max(Max, num);
			
			
			return;
		}
		
		for(int i=0;i<10;i++) {
			
			if(visited[i]) continue;
			//System.out.println("hi"+str+String.valueOf(i)+count+arr[count]);
			switch(arr[count]) {
			case '<':
				if ((str.charAt(count)-'0')<i) {
					visited[i] = true;
					//System.out.println("1 : "+str+String.valueOf(i));
					solution(count+1,str+String.valueOf(i));
					visited[i]= false;
				}
				break;
			case '>':
				if ((str.charAt(count)-'0')>i) {
					visited[i] = true;
					//System.out.println("2 : "+str+String.valueOf(i));
					solution(count+1,str+String.valueOf(i));
					visited[i]= false;
				}
				break;
			}
			
		}
	}
}
