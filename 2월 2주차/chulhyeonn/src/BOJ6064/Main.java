package BOJ6064;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    static int gcd(int n1, int n2)
    {
        if(n1 > n2)
        {
            int tmp = n1;
            n1 = n2;
            n2 = tmp;
        }
        while(n2 % n1 != 0) // 안 나눠질 동안
        {
            int tmp = n2 % n1;
            n2 = n1;
            n1 = tmp;
        }
        return n1;
    }

    static int lcm(int n1, int n2){
        return n1*n2 / gcd(n1, n2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int result = -1;
            int limit = m * n;
            for(int i = x; i < limit + 1; i=i+m) {
                if((i-y)%n == 0){
                    result = i;
                    break;
                }
            }
            System.out.println(result);
        }
    }
}
