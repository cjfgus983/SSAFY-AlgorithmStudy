package BOJ1978;

/*
 * 문제 소수 찾기
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

입력
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

출력
주어진 수들 중 소수의 개수를 출력한다.

예제 입력 1 
4
1 3 5 7
예제 출력 1 
3
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
	static boolean[] primes = new boolean[1001];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		isPrime();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int tmp;
		int cnt=0;
		for(int index =0; index<N;index++) {
			tmp = Integer.parseInt(st.nextToken());
			if (primes[tmp]) {
				cnt+=1;
			}
		}
		bw.write(cnt+"");
		bw.flush();
	}
	
	static void isPrime() {
		primes[2] = true;
		primes[0] = primes[1] = false;
		for(int index=3 ;index<1001;index+=1) {
			primes[index]= true;
			for(int sub =2; sub*sub<=index;sub+=1) {
				if(index%sub ==0) {
					primes[index]=false;
					break;
				}
				

		}
	}
}
}
