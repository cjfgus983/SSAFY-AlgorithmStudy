package BOJ15651;




/*
 * 문제 N 과 M (3)
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 M개를 고른 수열
같은 수를 여러 번 골라도 된다.
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1 
3 1
예제 출력 1 
1
2
3
 */


import java.io.*;
import java.util.StringTokenizer;;
public class Main {
	static int N,M;
	static int[] ans;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = new int[M];
		
		dfs(0);
		bw.flush();
	}
	
	
	static void dfs(int depth) throws Exception {
		if(depth==M) {
			for(int i:ans) {
				bw.write(i+" ");
			}
			bw.write("\n");
			return;
		}
		else {
			for(int i=1;i<=N;i++) {
				ans[depth]=i;
				dfs(depth+1);
			}
		}
	}
}
