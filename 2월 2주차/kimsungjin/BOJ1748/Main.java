import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int ans = 0;

        for (int i = 1; i < 10; i++) {
            if (n < Math.pow(10, i)) {
                ans += (int) (i * (n - (Math.pow(10, i - 1))) + 1);
                break;
            } else {
                ans += (int) (i * (Math.pow(10, i) - Math.pow(10, i - 1)) + 1);
            }
        }

        System.out.println(ans);
    }
}