package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10844 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int MOD = 1_000_000_000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		long[][] dp = new long[101][10];
		
		for(int i=1; i<=9;i++) {
			dp[1][i] =1;
		}
		
		for(int i=2;i<101;i++) {
			dp[i][0] = (dp[i-1][1])%MOD;
			dp[i][9] = (dp[i-1][8])%MOD;
			for(int j=1; j<9;j++) {
				dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1])%MOD;
			}
		}
		
		long result=0;
		for(int i=0; i< 10;i++) {
			result+=dp[n][i]%MOD;
		}
		System.out.println(result%MOD);

	}
	

}
