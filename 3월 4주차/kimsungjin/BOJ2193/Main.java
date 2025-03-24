import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[91][2];

		dp[1][0] = 0;
		dp[1][1] = 1;

		for (int i = 2; i <= 90; i++) {
			dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
			dp[i][1] = dp[i - 1][0];
		}

		System.out.println(dp[n][0] + dp[n][1]);
	}
}
