import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	final static int MAX_VALUE = 1_000_001;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int N = Integer.parseInt(br.readLine());
		
		boolean[] primeNumber = new boolean[MAX_VALUE];
	
		
		for(int i=3; i< MAX_VALUE;i+=2) {
			for(int j=3;i*j<MAX_VALUE;j+=2) {
				if( !primeNumber[i*j]) primeNumber[i*j] = true;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		//int N = Integer.parseInt(br.readLine());
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int a;
		int flag=0;
		
		while((a = Integer.parseInt(br.readLine())) !=0) {
			for(int i =3;i<=a/2;i+=2) {
				if(!primeNumber[i]&&!primeNumber[a-i]) {
					sb.append(a+" = "+i+" + "+(a-i)).append("\n");
					flag=1;
					break;
				}
			}
			if (flag==0) sb.append("Goldbach's conjecture is wrong.");
		}
		
		
		System.out.println(sb);
	}
	
}