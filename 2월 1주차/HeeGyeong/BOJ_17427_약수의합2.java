import java.util.Scanner;

/**
 * idea: g(N)는 x = 1,2,3,4 ...N에서 (N/x)*x의 값을 모두 더한것과 같다.
 * 유의사항: N이 최대 10^6이므로 오버플로우 방지를 위해 long타입으로 계산해야 한다.
 */
public class BOJ_17427_약수의합2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long result = 0;

        for (int x = 1; x <= N; x++) {
            result += (long) (N / x) * x;
        }
        System.out.println(result);
    }
}


