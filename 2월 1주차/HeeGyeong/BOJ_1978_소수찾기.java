import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1978_소수찾기 {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    Set<Integer> nums = new HashSet<>();
    for (int idx = 0; idx < N; idx++) {
      nums.add(Integer.parseInt(st.nextToken()));
    }

    Set<Integer> primeNums = getPrimeNum(1000);

    nums.retainAll(primeNums); // 교집합 구함
    System.out.println(nums.size());

  }

  // 에라토스테네스의 체 
  private static Set<Integer> getPrimeNum(int maxNum) {
    boolean[] isPrime = new boolean[maxNum + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = false; 
    isPrime[1] = false; 

    for (int num = 2; num * num <= maxNum; num++) {
      if (isPrime[num]) {
        for (int n = num * num; n <= maxNum; n += num) {
          isPrime[n] = false; // 배수를 제거
        }
      }
    }

    Set<Integer> primes = new HashSet<>();
    for (int i = 2; i <= maxNum; i++) {
      if (isPrime[i]) {
        primes.add(i);
      }
    }
    return primes;
  }
}
