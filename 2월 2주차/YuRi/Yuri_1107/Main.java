

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int N,M;
		int result =0;
		boolean[] isBreak = new boolean[10];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		if(M>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<M;i++) {
				isBreak[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		result = Math.abs(N-100);
		
		for(int i =0; i <1_000_000;i++) {
			String num = String.valueOf(i);
			Boolean flag=true;
		
			for(int j=0; j<num.length();j++) {
				if (isBreak[num.charAt(j)-'0']) {
					flag = false;
					break;
				}
			}
			
			if(flag) result = Math.min(result, num.length()+Math.abs(i-N));
		}
		
		
		System.out.println(result);
	}
	
}