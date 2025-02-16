import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 값 중에서 M개의 숫자를 순서를 고려해서 뽑는 경우를 구하는 순열 문제이다.
 */
public class BOJ_15649_N과M_1 {

  static int N;
  static int M;
  static int[] pArr;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    pArr = new int[M];
    visited = new boolean[N + 1];

    backtrack(0);

    System.out.println(sb);

  }

  private static void backtrack(int length) {
    // 길이가 M인 순열이 완성되면 Stringbuilder에 추가하고 리턴함.
    if (length == M) {
      for (int i = 0; i < pArr.length; i++) {
        sb.append(pArr[i] + " ");
      }
      sb.append('\n');
      return;
    }

    for (int num = 1; num <= N; num++) {
      // 방문하지 않은 숫자면
      if (visited[num] == false) {
        visited[num] = true;
        pArr[length] = num; // 배열에 숫자 추가
        backtrack(length + 1);
        visited[num] = false;

      }
    }

  }

}
