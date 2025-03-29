package BOJ17427;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int MAX_SIZE =1_000_001;
	static long[] F = new long[MAX_SIZE+1];
	static long[] G = new long[MAX_SIZE+1];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		for (int index =1; index<MAX_SIZE+1; index++) { //f전처리 -> O(N^2) 의 방식
//			f(index);
//		}
		f(); //f 전처리-> O(NlogN) 가능
		
		for (int index=1;index<MAX_SIZE+1;index++) { //G전처리, 누적합 이용
			G[index] = G[index-1]+ F[index];
		}
		
		int N = Integer.parseInt(br.readLine());
		bw.write(G[N]+"\n");
		bw.flush();
		
	}
	
//	static int f(int N) { //비효율적인 코드
//		int total = 0;
//		for(int i = 1; i<=N;i++) {
//			if(N%i==0) total+= i;
//		}
//		return total;
//}
	
	static void f() {
		for (int i = 1; i < MAX_SIZE+1; i++) {  // i는 약수 후보 (1부터 MAX까지)
		    for (int j = i; j <= MAX_SIZE; j += i) {  // i의 배수들(j)은 i를 약수로 가짐
		        F[j] += i;  // j의 약수 중 하나인 i를 더함
		    }
		}
	}
}