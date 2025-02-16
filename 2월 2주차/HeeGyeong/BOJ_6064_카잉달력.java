import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6064_카잉달력 {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken()) - 1; // 나머지 연산 사용으로 인해 0부터 시작하므로 -1해줌.
      int y = Integer.parseInt(st.nextToken()) - 1;

      boolean found = false;

      int lcm = (m / gcd(m, n)) * n; // m과 n의 최소공배수 구함
      // m과 n의 최소공배수를 종료조건으로 설정.
      for (int year = x; year < lcm; year += m) {
        // 해당하는 해인 경우 출력하고 끝냄
        if (year % n == y) {
          System.out.println(year + 1);
          found = true;
          break;
        }
      }

      // x:y로 표현되는 해를 못 찾은 경우
      if (found == false) {
        System.out.println(-1);
      }

    }
  }

  // 최대공약수 구함
  public static int gcd(int a, int b) {
    if (b == 0)
      return a;
    return gcd(b, a % b);
  }
}
