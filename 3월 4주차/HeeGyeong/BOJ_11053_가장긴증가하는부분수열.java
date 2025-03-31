import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] seq = new int[N]; // 주어지는 수열 저장
    int[] dp = new int[N]; // 해당 자리 수로 끝나는 부분수열 중 가장 긴 수열의 길이 
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < N; idx++) {
      seq[idx] = Integer.parseInt(st.nextToken());
    }

    Arrays.fill(dp, 1); // 각 자리 수로 시작하면 수열의 길이 1이므로, 1로 초기화

    for (int idx = 1; idx < N; idx++) {
      for (int prevIdx = 0; prevIdx < idx; prevIdx++) {
        if (seq[prevIdx] < seq[idx]) { // 이전의 수보다 그 값이 큰 경우
          dp[idx] = Math.max(dp[idx], dp[prevIdx] + 1);
        }
      }
    }

    Arrays.sort(dp); // 마지막 수로 끝나는 부분집합의 길이가 가장 크다는 보장 없으므로,
                     // 어떤 수로 끝나든 상관없이 가장 긴 부분집합의 길이 알기 위해 정렬.

    System.out.println(dp[N - 1]);
  }

}
