import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14002_가장긴증가하는부분수열4 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] seq = new int[N]; // 주어지는 수열 저장
    int[] dp = new int[N]; // 해당 자리 수로 끝나는 부분수열 중 가장 긴 수열의 길이
    int[] prev = new int[N]; // 직전의 숫자 저장

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < N; idx++) {
      seq[idx] = Integer.parseInt(st.nextToken()); // 입력 받음
    }

    for (int idx = 0; idx < N; idx++) {
      dp[idx] = 1; // 각 자리 수로 시작하면 수열의 길이 1이므로, 1로 초기화
      prev[idx] = -1; // 직전의 인덱스를 -1로 초기화
    }

    int maxLength = 1, maxIdx = 0;
    for (int idx = 1; idx < N; idx++) {
      for (int prevIdx = 0; prevIdx < idx; prevIdx++) {
        if (seq[prevIdx] < seq[idx] && dp[idx] < dp[prevIdx] + 1) { // 이전의 수보다 그 값이 크고, 부분집합의 길이가 더 긴 경우
          dp[idx] = dp[prevIdx] + 1;
          prev[idx] = prevIdx;
        }
      }

      // 마지막 수로 끝나는 부분집합의 길이가 가장 크다는 보장 없으므로, 가장 긴 부분집합의 길이 알기 위함.
      if (dp[idx] > maxLength) {
        maxLength = dp[idx];
        maxIdx = idx;
      }
    }

    System.out.println(dp[maxIdx]);
    StringBuilder sb = new StringBuilder();

    int seqIdx = maxIdx;
    for (int count = 0; count < dp[maxIdx]; count++) {
      sb.insert(0, seq[seqIdx] + " ");
      seqIdx = prev[seqIdx];
    }
    System.out.println(sb);
  }

}
