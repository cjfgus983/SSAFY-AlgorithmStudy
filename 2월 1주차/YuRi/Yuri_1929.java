import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int result=0;
	
		
		for(int i=a; i<=b;i++) {
			if(check(i)) sb.append(i).append("\n");
		}
		
		
		System.out.println(sb);
	}
	
	public static boolean check(int a) {
		int sqrt = (int) Math.sqrt(a);
		if(a==1) return false;
		for(int i =2;i<=sqrt; i++) {
			if(a %i ==0) return false;
		}
		return true;
	}
}