package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine().trim());
		int[] arr =new int[1001];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for(int i=1; i<=n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[1001];
		
		dp[1] = 1;
		int nowLength;
		int result = 1;
		
		for(int i=2;i<=n;i++) {
			nowLength = 0;
			for(int j =1; j<i;j++) {
				if(arr[j]<arr[i] && nowLength<dp[j]) nowLength=dp[j];
			}
			dp[i] = nowLength+1;
			result = Math.max(dp[i], result);
		}
		
		System.out.println(result);

	}
	

}
