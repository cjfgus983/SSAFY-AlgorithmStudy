package BOJ2609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int gcd(int n, int m)
    {
        // n 이 큰거 m 이 작은거
        if(n < m)
        {
            int tmp = n;
            n = m;
            m = tmp;
        }
        while(m != 0)
        {
            int tmp = n;
            n = m;
            m = tmp % m;
        }
        return n;
    }

    public static int lcm(int n, int m)
    {
        return n * m / gcd(n, m);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N, M;
        String input = br.readLine();
        st = new StringTokenizer(input, " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(gcd(N, M));
        System.out.println(lcm(N, M));
    }
}
