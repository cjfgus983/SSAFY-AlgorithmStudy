import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 N개의 값 중에서 길이가 M인 순열을 사전 순으로 출력하는 문제.
 */
public class BOJ_15654_N과M_5 {

  static int N;
  static int M;
  static int[] numArr;
  static int[] pArr;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    numArr = new int[N];
    StringTokenizer numSt = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < N; idx++) {
      numArr[idx] = Integer.parseInt(numSt.nextToken());
    }
    pArr = new int[M];
    visited = new boolean[N];
    Arrays.sort(numArr); // 사전순으로 출력하기 위해서 오름차순으로 정렬

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

    // 주어진 N개의 숫자 순회
    for (int idx = 0; idx < numArr.length; idx++) {
      // 방문하지 않은 숫자면
      if (visited[idx] == false) {
        visited[idx] = true;
        pArr[length] = numArr[idx]; // 배열에 숫자 추가
        backtrack(length + 1);
        visited[idx] = false;

      }
    }

  }

}
