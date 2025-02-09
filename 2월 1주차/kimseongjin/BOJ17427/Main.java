package BOJ17427;
import java.io.*;

/**
 * 각 자연수의 약수들을 나열해본다면 특징을 알 수 있다.
 * 1 -> 1
 * 2 -> 1   2
 * 3 -> 1       3
 * 4 -> 1   2       4
 * 5 -> 1               5
 * 6 -> 1   2   3           6
 * ...
 * <p>
 * 위의 특징으로 미루어 보아 g(N)에 포함되는 자연수 x의 개수는 N/x * x 임을 알 수 있다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long ans = 0;
        for (int i = 1; i <= n; i++) ans += (long) n / i * i;
        System.out.println(ans);
    }
}
