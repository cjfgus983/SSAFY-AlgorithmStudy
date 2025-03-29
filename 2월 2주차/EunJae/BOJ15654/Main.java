package BOJ15654;



/*
 * 문제 N과 M (5)
N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. N개의 자연수는 모두 다른 수이다.

N개의 자연수 중에서 M개를 고른 수열

입력
첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1 
3 1
4 5 2
예제 출력 1 
2
4
5
 */
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;
public class Main {
	static int[] arr,ans;
	static int M,N;
	static boolean[] visited;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ans = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0);
		bw.flush();
	}
	
	
	static void dfs(int depth) throws IOException {
		if(depth==M) {
			for(int i : ans) {
//				System.out.print(i+" ");
				bw.write(i+" ");
			}
//			System.out.println();
			bw.write("\n");
			return;
		}
		
		else {
			for(int i = 0; i<N;i++) {
				if(!visited[i]) {
					visited[i]= true;
					ans[depth] = arr[i];
					dfs(depth+1);
					visited[i]= false;
				}
			}
			
		}
	}
}
