package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16194 {
	static int dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		int[] card = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for(int i=0; i< n;i++) {
			card[i+1] = Integer.parseInt(st.nextToken());
		}
		dp[0]=0;
		for(int i = 1; i<=n;i++) {
			for(int j = 1;j<=i;j++) {
				dp[i] = Math.min(dp[i], card[j]+dp[i-j]);
			}
		}
		System.out.println(dp[n]);
		
	}
	

}
