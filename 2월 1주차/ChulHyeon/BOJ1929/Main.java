package BOJ1929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        st = new StringTokenizer(input, " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        // M 이상 N 이하 소수 구하기
        // 에라토스 테네스의 체
        boolean prime[] = new boolean[1_000_001];
        // 초기화
        for(int i=2;i<= 1_000_000;i++)
        {
            prime[i] = true;
        }
        // 소수 아닌거 찾기
        for(int i=2;i<=Math.sqrt(1_000_000);i++)
        {
            for(int j = i+i; j<= 1_000_000;j = j+i)
            {
                prime[j] = false;
            }
        }
        // for 문으로 소수 찾기
        for(int i=M;i<=N;i++)
        {
            if(prime[i])
            {
                System.out.println(i);
            }
        }
    }
}
