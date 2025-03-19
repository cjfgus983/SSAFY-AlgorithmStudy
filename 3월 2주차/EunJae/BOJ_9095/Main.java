package BOJ_9095;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
	static int[] dp = new int[12];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i<12;i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		for (int test_case = 1 ; test_case<=T;test_case++) {
			int n = Integer.parseInt(br.readLine());
			bw.write(dp[n]+"\n");
		}
		bw.flush();
		
	}
}