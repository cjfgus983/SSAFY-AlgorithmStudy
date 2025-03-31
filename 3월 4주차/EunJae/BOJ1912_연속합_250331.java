package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1912_연속합_250331 {
	public static void main(String[] args) {
		Scanner sc=  new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i<N;i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[N];
		
		dp[N-1] = arr[N-1];
		int max = dp[N-1];
		for(int i = N-2;i>=0;i--) {
			
			dp[i] = arr[i];
			if(dp[i+1]>0) {
				dp[i] = dp[i]+dp[i+1];
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(max);
	}
}
