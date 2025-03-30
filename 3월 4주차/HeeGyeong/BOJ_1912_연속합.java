import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1912_연속합 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] seq = new int[N]; // 주어지는 수열 저장
    int[] dp = new int[N]; // 해당 자리 수를 포함하는 부분수열 중 그 요소의 합의 최댓값 

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < N; idx++) {
      int curNum = Integer.parseInt(st.nextToken());
      seq[idx] = curNum;
      dp[idx] = curNum;

    }

    for (int idx = 1; idx < N; idx++) {
      // (이전의 수를 포함하는 합 + 현재 수) 가 (현재 수를 포함하는 합)보다 큰 경우
      if (dp[idx] < dp[idx - 1] + seq[idx]) {
        dp[idx] = dp[idx - 1] + seq[idx];
      }
    }

    Arrays.sort(dp); // 마지막 수로 끝나는 부분집합의 합이 가장 크다는 보장 없으므로,
                     // 어떤 수로 끝나든 상관없이 가장 높은 부분집합의 합 알기 위해 오름차순 정렬.

    System.out.println(dp[N - 1]);
  }

}
