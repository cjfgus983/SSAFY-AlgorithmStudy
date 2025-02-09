package BOJ17427;

// 24의 약수는 1, 2, 3, 4, 6, 8, 12, 24가 있다. 자연수 A의 약수의 합은 A의 모든 약수를 더한 값이고,
// f(A)로 표현한다. x보다 작거나 같은 모든 자연수 y의 f(y)값을 더한 값은 g(x)로 표현한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;
        String input = br.readLine();
        int n = Integer.parseInt(input);
        for(int i=1; i<=n; i++) {
            result = result + (i * (n/i));
        }
        System.out.println(result);
    }
}
