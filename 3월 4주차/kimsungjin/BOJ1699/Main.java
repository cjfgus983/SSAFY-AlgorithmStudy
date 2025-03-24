import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE);

		for (int i = 1; i * i <= 100000; i++) {
			dp[i * i] = 1;
		}

		for (int i = 1; i <= 100000; i++) {
			for (int j = 1; j * j < i; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + dp[j * j]);
			}
		}

		System.out.println(dp[N]);
	}
}
