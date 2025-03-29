package march3;

import java.util.Scanner;

/*
 *  1      2     3     4     5      6       7      8     9 
 * 10 12  21 23 32 34 43 45 54 56  65 67 76 78   87 89  98
 * 9,0으로끝나는것만 +1, 나머지는 +2씩 분기가 늘어남
 * 9의 수 = 이전의 8로 끝난 수
 * 0의 수 = 이전의 1로 끝난 수
 */
class BOJ10844_쉬운계단수_250322 {
	 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[N+1][10];
		for(int i=1;i<=9;i++) {
			dp[1][i] =1;
		}
		int cnt=0;
		for(int i = 2; i<=N;i++) {
			dp[i][0] = dp[i-1][1];
			dp[i][9] = dp[i-1][8];
			for(int j =1;j<=8;j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
			}
		}
		
		for(int i=0;i<=9;i++) {
			cnt+=dp[N][i];
		}
		System.out.println(cnt);
	}
}
