package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		
		System.out.println(solution(n,0));
	}
	
	static int solution(int now, int count) {
		if(now <= 1) return count;
		
		return Math.min(solution(now/2, count+1+(now%2)),solution(now/3, count+1+(now%3)));
	}
}
