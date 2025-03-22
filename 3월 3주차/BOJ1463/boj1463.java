package march3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1463 {
	static BufferedReader br;
	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception {
	br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());
	dp = new int[1000001];
	dp[1] =0;
	for(int i = 2; i<1000001;i++) {
		dp[i] = dp[i-1]+1;
		
		if(i%2==0) {
			dp[i]= Math.min(dp[i],dp[i/2]+1);
		}
		if(i%3==0) {
			dp[i] = Math.min(dp[i],dp[i/3]+1);
		}
	}
	System.out.println(dp[N]);
	
	
}
}
