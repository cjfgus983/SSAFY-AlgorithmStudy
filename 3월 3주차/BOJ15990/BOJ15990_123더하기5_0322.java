package march3;

import java.util.Scanner;
/*
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 3가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다. 단, 같은 수를 두 번 이상 연속해서 사용하면 안 된다.

1+2+1
1+3
3+1
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 */
public class BOJ15990_123더하기5_0322 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 0;
		int T = sc.nextInt();
		int[] N = new int[T];
		for(int tc = 0; tc<T;tc++) {
			N[tc] = sc.nextInt();
			max = Math.max(max, N[tc]);
		}
		
		long[][] dp = new long[max+1][4];
		dp[1][1] = 1; //1
		dp[2][2] = 1; //2
		dp[3][3] = 1; //3, 1+2, 2+1
		dp[3][2] = 1;
		dp[3][1] = 1;
		//dp[4] = ;//1+3, 3+1, 1+2+1
		
		
		for(int i = 4;i<=max;i++) {
			dp[i][1] = (dp[i-1][2] + dp[i-1][3])%1_000_000_009;
			dp[i][2] = (dp[i-2][1] + dp[i-2][3])%1_000_000_009;
			dp[i][3] = (dp[i-3][1] + dp[i-3][2])%1_000_000_009;
		}
		
        for (int i = 0; i < T; i++) {
            int num = N[i];
            System.out.println((dp[num][1] + dp[num][2] + dp[num][3]) % 1_000_000_009);
        }

	}
}
