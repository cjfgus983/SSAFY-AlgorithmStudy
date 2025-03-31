import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 값 중에서 중복을 허용해서 뽑은 M개의 숫자로 순열을 만드는 문제.
 */
public class BOJ_15651_N과M_3 {

  static int N;
  static int M;
  static int[] pArr;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    pArr = new int[M];

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

    // 1~N까지의 숫자 순회
    for (int num = 1; num <= N; num++) {
      pArr[length] = num; // 배열에 idx=0부터 숫자 추가
      backtrack(length + 1);

    }

  }

}
