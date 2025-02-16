package BOJ1748;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long result = 0;
        long digit = 1; // 자리수 (1, 10, 100, 1000, ...)
        int length = 1; // 현재 자리수의 길이

        while(digit * 10 <= n)
        {
            result = result + (length * 9 * digit);
            length++;
            digit = digit * 10;
        }

        result = result + (n - digit + 1) * length;
        System.out.println(result);
    }
}

