package BOJ9095;

/*
 * 문제 1,2,3 더하기
정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.

1+1+1+1
1+1+2
1+2+1
2+1+1
2+2
1+3
3+1
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.

출력
각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.

예제 입력 1 
3
4
7
10
예제 출력 1 
7
44
274
1:1 = 1 |
2:2 = 1+1| 2
3:4 = 1+1+1 | 1+2, 2+1 | 3
4:7 = 1+3|, 2+2,1+1+2 | 1+2+1,2+1+1,3+1,1+1+1+1
5:13 = 1+1+1+1+1 |1+1+1+2 * 4 |1+1+3 *3 , 2+2+1, *3 | 3+2, 2+3 |

1,2,4,7,13

N을 만드는 법 : N-1 읆 만들고 1을 더한다. N-2를 만들고 2를 더한다. N-3을 더하고 3을 더한다.

 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
	static int[] dp = new int[12];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i<12;i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		for (int test_case = 1 ; test_case<=T;test_case++) {
			int n = Integer.parseInt(br.readLine());
			bw.write(dp[n]+"\n");
		}
		bw.flush();
		
	}
}
