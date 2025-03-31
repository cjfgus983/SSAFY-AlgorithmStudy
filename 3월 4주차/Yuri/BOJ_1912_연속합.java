import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912_연속합 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[100000];
		
		
		st = new StringTokenizer(br.readLine().trim());
		
		dp[0] = Integer.parseInt(st.nextToken());
		int max=dp[0];
		int number;
		for(int i=1; i<n;i++) {
			number =Integer.parseInt(st.nextToken());
			dp[i] = Math.max(number, dp[i-1]+number);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
