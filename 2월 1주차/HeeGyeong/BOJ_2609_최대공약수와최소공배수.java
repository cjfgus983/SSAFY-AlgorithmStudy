import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class BOJ_2609_최대공약수와최소공배수 {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt();
    int B = sc.nextInt();
    Set<Integer> aAliquotSet = getAliquot(A);
    Set<Integer> bAliquotSet = getAliquot(B);

    aAliquotSet.retainAll(bAliquotSet);
    int gcd = aAliquotSet.stream().max(Integer::compare).orElse(1); //최대공약수
    int lcm = (A * B) / gcd; // 최소공배수

    System.out.println(gcd);
    System.out.println(lcm);
  }
  
  public static Set<Integer> getAliquot(int x) {
    Set<Integer> aliquotSet = new HashSet<>();
    int sqrt = (int) Math.sqrt(x);
    for (int num = 1; num <= sqrt; num++) {
      if (x % num == 0) {
        aliquotSet.add(num);
        aliquotSet.add(x / num == num ? 0 : x / num);
      }
    }
    return aliquotSet;
  }
}


