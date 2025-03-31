package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15990 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int MOD = 1_000_000_009;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int t = Integer.parseInt(br.readLine().trim());
		long[][] dp = new long[100001][4];
		
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;
		
		for(int i=4;i<100001;i++) {
			dp[i][1] = (dp[i-1][2]+dp[i-1][3])%MOD;
			dp[i][2] = (dp[i-2][1]+dp[i-2][3])%MOD;
			dp[i][3] = (dp[i-3][1]+dp[i-3][2])%MOD;
		}
		
		for(int i=0; i< t;i++) {
			int a = Integer.parseInt(br.readLine().trim());
			System.out.println((dp[a][1]+dp[a][2]+ dp[a][3])%MOD);
		}

	}
	

}
