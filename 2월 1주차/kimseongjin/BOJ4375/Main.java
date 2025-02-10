package BOJ4375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 모듈러 연산의 특징을 이용해 브루트포스 풀이.
 * <p>
 * (A + B) % M = ((A % M) + (B % M)) % M
 * <p>
 * f(x) = 1이 x번 반복되는 자연수라고 할 때,
 * f(1) = 1, f(x) = f(x-1) * 10 + 1
 * f(x) % n = ( f(x-1) % n + 1 ) % n
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열 입력
        String input;

        // 입력이 종료될때까지 반복
        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            int count = 1;
            int mod = 1;
            while (mod % n != 0) {
                mod = (mod * 10 + 1) % n;
                count++;
            }
            System.out.println(count);
        }
    }
}
