
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		//StringBuilder sb = new StringBuilder();
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i =0; i< t; i++) {
			int now = Integer.parseInt(st.nextToken());
			max = Math.max(max, now);
			min = Math.min(min, now);
		}
		
		System.out.println(max*min);
	}
}