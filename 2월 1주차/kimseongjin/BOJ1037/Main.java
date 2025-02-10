package BOJ1037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 약수를 오름차순으로 정렬한다.
 * 2. 약수의 개수를 확인한다.
 *  2-1 약수의 개수가 홀수일 때, 가운데 값의 제곱수가 정답.
 *  2-2 약수의 개수가 짝수일 때, 가장 작은 약수와 가장 큰 약수의 곱이 정답.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 약수를 오름차순으로 정렬한다.
        Arrays.sort(numbers);

        // 약수의 개수를 확인한다.
        if (n % 2 == 0)
            // 약수의 개수가 짝수일 때, 가장 작은 약수와 가장 큰 약수의 곱이 정답.
            System.out.println(numbers[0] * numbers[n - 1]);
        else
            // 약수의 개수가 홀수일 때, 가운데 값의 제곱수가 정답.
            System.out.println(numbers[n / 2] * numbers[n / 2]);
    }
}
