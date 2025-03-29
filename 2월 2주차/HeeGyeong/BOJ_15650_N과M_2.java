import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 값 중에서 M개의 숫자로 만드는 순열 중 오름차순인 순열을 구하는 문제.
 */
public class BOJ_15650_N과M_2 {

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
    pArr = new int[M + 1]; // pArr[0]=0으로 지정해 두기 위해서 크기를 M+1로 할당한다.
    visited = new boolean[N + 1];

    backtrack(1);

    System.out.println(sb);

  }

  private static void backtrack(int length) {
    // 길이가 M인 순열이 완성되면 Stringbuilder에 추가하고 리턴함.
    if (length == (M + 1)) {
      for (int i = 1; i < pArr.length; i++) {
        sb.append(pArr[i] + " ");
      }
      sb.append('\n');
      return;
    }

    for (int num = 1; num <= N; num++) {
      // 방문하지 않은 숫자이고
      // 순열을 저장해놓은 배열의 바로 직전에 저장한 숫자보다 큰 경우
      if (visited[num] == false && pArr[length - 1] < num) {
        visited[num] = true;
        pArr[length] = num; // 배열에 숫자 추가
        backtrack(length + 1);
        visited[num] = false;

      }
    }

  }

}
