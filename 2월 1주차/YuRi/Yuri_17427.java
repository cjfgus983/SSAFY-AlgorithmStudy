import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		//StringTokenizer st = new StringTokenizer(br.readLine());
		long sum=0;
		
		//StringBuilder sb = new StringBuilder();
		
		for(int i =1;i<= t;i++ ) {
			sum += i* (t/i);
		}
		
		System.out.println(sum);
	}
	
}