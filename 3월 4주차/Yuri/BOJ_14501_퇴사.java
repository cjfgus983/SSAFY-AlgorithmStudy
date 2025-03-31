import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] t = new int[N + 2]; // 상담 기간
		int[] p = new int[N + 2]; // 상담 수익
		int[] dp = new int[N + 2]; // i일까지 얻을 수 있는 최대 수익

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N + 1; i++) {
			// 이전까지의 최대 수익을 이어받기
			dp[i] = Math.max(dp[i], dp[i - 1]);

			// 상담을 선택할 수 있는 경우
			if (i + t[i] <= N + 1) {
				dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
			}
		}

		System.out.println(dp[N + 1]);
	}
}
