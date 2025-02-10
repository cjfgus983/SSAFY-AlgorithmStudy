package BOJ6588;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] prime = new boolean[1000001];
        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i <= 1000000; i++) {
            if (!prime[i]) continue;
            for (int j = 2; i * j <= 1000000; j++) {
                prime[i * j] = false;
            }
        }


        StringBuilder ans = new StringBuilder();
        int n = 0;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            int left = 3;
            int right = n - 3;

            while (true) {
                if(prime[left] && prime[right]){
                    ans.append(n).append(" = ").append(left).append(" + ").append(right).append("\n");
                    break;
                }
                if(left > right) {
                    System.out.println("Goldbach's conjecture is wrong.");
                    break;
                }
                left += 2;
                right -= 2;
            }

        }

        System.out.println(ans);
    }
}