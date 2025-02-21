import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Main {
	static int[][] talk;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		talk = new int[N][2];
		visited = new boolean[N];
		for(int i =0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int endDay = Integer.parseInt(st.nextToken())+i; //끝나는날
			int pay = Integer.parseInt(st.nextToken());
			talk[i] = new int[]{endDay,pay};
		}

		int[] dp = new int[N+2];
		int endDay=-1;
		for(int day = N-1;day>=0;day--) {
			endDay = talk[day][0];
			if(endDay<=N ) {
				dp[day] = Math.max(talk[day][1]+dp[endDay],dp[day+1]);
			}
			else {
				dp[day] = dp[day+1];
			}
		}
		System.out.println(dp[0]);
}
}
