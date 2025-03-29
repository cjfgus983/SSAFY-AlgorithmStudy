package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095 {
	// 1 3 5 11
	static int dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int t = Integer.parseInt(br.readLine().trim());
		dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i<12; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		for(int test = 0; test<t;test++) {
			System.out.println(dp[Integer.parseInt(br.readLine().trim())]);
		}
		
	}
	

}
