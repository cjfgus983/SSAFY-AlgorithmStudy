package BOJ17425;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for(int t= 1;t <= testCase;t++) {
            int n = Integer.parseInt(br.readLine());
            int result = 0;
            for(int i = 1; i <= n; i++) {
                result = result + (i * (n/i));
            }
            System.out.println(result);
        }
    }
}
