import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1929_소수구하기 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());



    List<Integer> primeNums = getPrimeNum(1_000_000);

    for (int num : primeNums) {
      if (num >= M && num <= N) {        
        sb.append(num + "\n");
      }
    }
    System.out.println(sb);

  }

  // 에라토스테네스의 체
  private static List<Integer> getPrimeNum(int maxNum) {
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

    List<Integer> primes = new ArrayList<>();
    for (int i = 2; i <= maxNum; i++) {
      if (isPrime[i]) {
        primes.add(i);
      }
    }
    return primes;
  }
}
