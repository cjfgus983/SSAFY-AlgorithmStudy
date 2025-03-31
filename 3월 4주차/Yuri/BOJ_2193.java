package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2193 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		long[][] dp = new long[91][3];
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for(int i=2;i<91;i++) {
			dp[i][0] = dp[i-1][2];
			dp[i][1] = dp[i-1][0];
			dp[i][2] = dp[i][0] +dp[i][1];
		}
		
		System.out.println(dp[n][2]);

	}
	

}
