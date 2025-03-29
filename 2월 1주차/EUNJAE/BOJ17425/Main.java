package BOJ17425;



/*
 * 문제
두 자연수 A와 B가 있을 때, A = BC를 만족하는 자연수 C를 A의 약수라고 한다. 예를 들어, 2의 약수는 1, 2가 있고, 24의 약수는 1, 2, 3, 4, 6, 8, 12, 24가 있다. 
자연수 A의 약수의 합은 A의 모든 약수를 더한 값이고, f(A)로 표현한다. x보다 작거나 같은 모든 자연수 y의 f(y)값을 더한 값은 g(x)로 표현한다.

자연수 N이 주어졌을 때, g(N)을 구해보자.

입력
첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 100,000)가 주어진다. 둘째 줄부터 테스트 케이스가 한 줄에 하나씩 주어지며 자연수 N(1 ≤ N ≤ 1,000,000)이 주어진다.

출력
각각의 테스트 케이스마다, 한 줄에 하나씩 g(N)를 출력한다.

예제 입력 1 
5
1
2
10
70
10000
예제 출력 1 
1
4
87
4065
82256014

 *
 *f(A) = A의 약수의 합  g(x) = x 이하의 자연수 n에 대해 f(n)의 합 = Σf(n), n= 1->x
 *f(A) 함수를 구현하고
 *반복문을 통해 g(x)를 구현한다? -> 느림 
 *먼저 값을 다 찾아놓고, 접근
 *
 */


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
		int T = Integer.parseInt(br.readLine());
		
//		for (int index =1; index<MAX_SIZE+1; index++) { //f전처리 -> O(N^2) 의 방식
//			f(index);
//		}
		f(); //f 전처리-> O(NlogN) 가능
		
		for (int index=1;index<MAX_SIZE+1;index++) { //G전처리, 누적합 이용
			G[index] = G[index-1]+ F[index];
		}
		
		for (int test_case = 0; test_case<T;test_case++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(G[N]+"\n");
			
		}
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

