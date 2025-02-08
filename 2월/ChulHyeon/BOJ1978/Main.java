package BOJ1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] prime = new boolean[1001];

    static void checkPrime()
    {
        // 초기 초기화
        for(int i=2;i<=1000;i++)
        {
            prime[i] = true;
        }
        for(int i=2;i<=Math.sqrt(1000);i++)
        {
            for(int j=i+i;j<=1000;j = j + i)
            {
                prime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        checkPrime();
        int testCase = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i=1;i<=testCase;i++)
        {
            int n = Integer.parseInt(st.nextToken());
            if(prime[n] == true)
            {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
