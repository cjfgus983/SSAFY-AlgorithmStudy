import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int result=0;
	
		
		for(int i=0; i<N;i++) {
			int a = Integer.parseInt(st.nextToken());
			if(check(a)) result+=1;
		}
		
		//StringTokenizer st = new StringTokenizer(br.readLine());
		//
		//StringBuilder sb = new StringBuilder();
		
		System.out.println(result);
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