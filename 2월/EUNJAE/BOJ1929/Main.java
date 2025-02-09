package BOJ1929;

/*
 * 문제 소수 구하기
M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000) M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.

출력
한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.

예제 입력 1 
3 16
예제 출력 1 
3
5
7
11
13
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
	static int MAX_SIZE = 1_000_001;
	static boolean[] primes = new boolean[MAX_SIZE];
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		findPrimes();
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		
		int larger = Math.max(num1, num2);
		int smaller = Math.min(num1, num2);
		for(int index=smaller;index<=larger;index+=1) { //2씩 증가하고싶은데, 2 예외처리 하기 귀찮음..
			if(primes[index]) {
				bw.write(index+"\n");
			}
		}
		bw.flush();
		
	}
	
	static void findPrimes() {
		primes[2] = true;
		for (int index=3; index<MAX_SIZE;index+=2) {
			boolean isPrime= true;
			for(int sub=3; sub*sub<=index;sub+=2) {
				if(index%sub==0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
			primes[index]= isPrime;
			}
		}
	}
}
