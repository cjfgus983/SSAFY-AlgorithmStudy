import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [idea]
 * - 17427_약수의합2 문제가 여러개 주어지는 문제
 * - 일반 반복문으로 풀면 중복되는 계산이 많아서 시간초과나므로 
 *   f(x)와 g(x)값을 미리 구해서 저장해 놓아야 함.
 * 유의사항: Scanner로 입력값 받으면 시간초과로 통과 못함.
 */
public class BOJ_17425_약수의합 {
  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());
    int MAX_N = 1_000_000;
    long[] fxResult = new long[MAX_N+1];
    long[] gxResult = new long[MAX_N + 1];
    
    Arrays.fill(fxResult, 1);

    for (int x = 2; x <= MAX_N; x++) {
      for (int y = 1; x*y <= MAX_N; y++) {
        fxResult[x*y] += x;
      }
    }

    for (int x = 1; x <= MAX_N; x++) {
      gxResult[x] = gxResult[x - 1] + fxResult[x];
    }

    for (int i = 0; i < T; i++) {
      int N = Integer.parseInt(br.readLine());
      sb.append(gxResult[N] + "\n");
    }
    System.out.println(sb);
  }
}