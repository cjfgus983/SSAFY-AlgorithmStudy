import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

/**
 * N은 항상 32비트 부호있는 정수로 표현할 수 있으므로 int형 안에서 처리 가능함.
 * 양수 A는 진짜 약수 중 최솟값과 최대값을 곱한 값이다.
 */
public class BOJ_1037_약수 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] aliquotArr = new int[N];
    for (int idx = 0; idx < N; idx++) {
      aliquotArr[idx] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(aliquotArr);
    System.out.println(aliquotArr[0] * aliquotArr[N-1]);

  }
  
}
