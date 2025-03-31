import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//
//public class Main {
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int n = Integer.parseInt(br.readLine().trim());
//		int[] dp = new int[100001];
//		
//	
//		for(int i=1; i<=n;i++) {
//			for(int j = 1; j*j<=i; j++) {
//				dp[i]= Math.min(dp[i-1]+1, 1+dp[i-j*j]);
//			}
//		}
//		
//		System.out.println(dp[n]);
//	}
//}

public class BOJ_1699_제곱수의합 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			dp[i] = i; // 최악의 경우: 모두 1^2
			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
			}
		}
		
		System.out.println(dp[n]);
	}
}
