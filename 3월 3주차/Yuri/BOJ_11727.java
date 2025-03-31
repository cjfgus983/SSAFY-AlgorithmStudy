package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727 {
	// 1 3 5 11
	static int dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;
		for(int i = 3; i<1001; i++) {
			dp[i] = (dp[i-1] + dp[i-2]*2)%10007;
		}
		System.out.println(dp[n]);
	}
	

}
