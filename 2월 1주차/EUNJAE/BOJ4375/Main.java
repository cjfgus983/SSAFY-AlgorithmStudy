package BOJ4375;


/*
 * 문제
2와 5로 나누어 떨어지지 않는 정수 n(1 ≤ n ≤ 10000)가 주어졌을 때, 각 자릿수가 모두 1로만 이루어진 n의 배수를 찾는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있고, n이 주어진다.

출력
각 자릿수가 모두 1로만 이루어진 n의 배수 중 가장 작은 수의 자리수를 출력한다.

예제 입력 1 
3
7
9901
예제 출력 1 
3
6
12
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n;
		String line;
		long oneNum,cnt;
		while((line=br.readLine())!=null) {
			if (line =="") {
				break;
			}
			n = Integer.parseInt(line);
			oneNum = 1;
			cnt = 1;
			while(true) {
				if((oneNum % n) ==0) {
					break;
				}
				else {
					oneNum = oneNum*10 +1;
					oneNum%=n; // 여기가 핵심, ((A%C) *(B%C)) %C = (A*B) %C
					cnt+=1;
				}

			}
			bw.write(cnt+"\n");
		}
		bw.flush();		
	}
}

