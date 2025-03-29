
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0;t<T;t++) {
			st  = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			boolean flag = false;
			
			for(int i = x;i<=N*M;i+=M) {
				if((i-1)%N+1 == y) {
					flag = true;
					System.out.println(i);
					break;
				}
			}
			
			if(!flag) System.out.println(-1);
		}
	}

}
