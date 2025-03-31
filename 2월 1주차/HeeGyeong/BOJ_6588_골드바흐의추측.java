import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_6588_골드바흐의추측 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    boolean[] isPrime = getPrimeNum(1_000_000);

    while (true) {
      int n = Integer.parseInt(br.readLine());
      if (n == 0)
        break; 

      boolean flag = false;

      for (int a = 3; a <= n / 2; a += 2) { 
        if (isPrime[a] && isPrime[n - a]) {
          sb.append(n).append(" = ").append(a).append(" + ").append(n - a).append("\n");
          flag = true;
          break;
        }
      }

      if (!flag)
        sb.append("Goldbach's conjecture is wrong.\n");
    }

    System.out.println(sb);

  }

  // 에라토스테네스의 체
  private static boolean[] getPrimeNum(int maxNum) {
    boolean[] isPrime = new boolean[maxNum + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;

    for (int num = 2; num * num <= maxNum; num++) {
      if (isPrime[num]) {
        for (int n = num * num; n <= maxNum; n += num) {
          isPrime[n] = false; // 배수 제거
        }
      }
    }
    return isPrime;
  }
}
