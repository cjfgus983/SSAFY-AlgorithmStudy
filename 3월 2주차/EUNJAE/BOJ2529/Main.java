package BOJ2529;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * < 와 > 가 k개 나열된 순서열A
 *  부등호 앞뒤에 한자리수 숫자를 넣어서 성립하게 해보세요
 *  
 */
public class Main {
	static boolean visited[] = new boolean[10];
	static String max = "";
	static String min = "";
	
	static int k;
	static char c[];
	static int selected[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		k = Integer.parseInt(br.readLine());
		c = new char[k];
		selected = new int[k+1];
		StringTokenizer st = new StringTokenizer( br.readLine());
		for(int i= 0; i<k; i++) {
			c[i] = st.nextToken().charAt(0);
		}
		
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int depth) {
		
		if (depth == k+1) {
			StringBuilder sb = new StringBuilder();
			for(int i =0; i<=k;i++) {
				sb.append(selected[i]);
			}
			String num = sb.toString();
            if (max.isEmpty() || num.compareTo(max) > 0) {
                max = num;
            }
            if (min.isEmpty() || num.compareTo(min) < 0) {
                min = num;
            }
            return;
		}
		for(int i =0;i<=9;i++) {
			if(!visited[i] && (depth==0
					||(c[depth-1]=='>' && selected[depth-1] > i)
					||(c[depth-1]=='<' && selected[depth-1] < i)
							)) {
				visited[i]=true;
				selected[depth] =i;
				dfs(depth+1);
				visited[i]=false;
			}
		}
	}
}
