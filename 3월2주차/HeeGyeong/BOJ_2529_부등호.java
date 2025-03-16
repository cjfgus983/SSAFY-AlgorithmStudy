
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_2529_부등호 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static char[] signs;
  static int N, k;
  static int[] pArr;
  static boolean[] isSelected = new boolean[10];
  static String min = "9999999999", max = "0";

  public static void main(String[] args) throws IOException {

    k = Integer.parseInt(br.readLine()); // 부등호 개수
    signs = new char[k]; // 부등호 순서
    pArr = new int[k + 1]; // 순열 저장할 배열

    // 부등호 입력받음
    st = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < k; idx++) {
      signs[idx] = st.nextToken().charAt(0);
    }

    makePerm(0);

    sb.append(max).append('\n').append(min);
    System.out.println(sb);
  }

  public static void makePerm(int count) {

    if (count == k + 1) {
      boolean flag = true;
      // 가능한 순열인지 확인.
      for (int idx = 0; idx < k; idx++) {
        switch (signs[idx]) {
          case '<':
            if (pArr[idx] > pArr[idx + 1]) {
              flag = false;
            }
            break;
          case '>':
            if (pArr[idx] < pArr[idx + 1]) {
              flag = false;
            }
            break;
        }
      }

      // 가능한 순열이면
      if (flag == true) {
        StringBuilder sb = new StringBuilder();
        for (int num : pArr) {
          sb.append(num);
        }
        String numStr = sb.toString();

        if (numStr.compareTo(min) < 0) {
          min = numStr;
        }
        if (numStr.compareTo(max) > 0) {
          max = numStr;
        }
      }
      return;
    }

    for (int idx = 0; idx <= 9; idx++) {
      if (isSelected[idx] == false) {
        isSelected[idx] = true;
        pArr[count] = idx;
        makePerm(count + 1);
        isSelected[idx] = false;
      }
    }

  }

}