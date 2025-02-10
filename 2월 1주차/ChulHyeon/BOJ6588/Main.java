package BOJ6588;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 1000001;

    static boolean[] prime = new boolean[MAX];

    public static void checkPrime(){
        // 초기 true로 초기화
        for(int i = 2;i<MAX;i++)
        {
            prime[i] = true;
        }

        for(int i=2; i<Math.sqrt(MAX); i++){
            for(int j=i+i; j<MAX; j = j + i){
                prime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        checkPrime();

        while(true)
        {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            for(int i = 3;i<n;i = i + 2)
            {
                if(prime[i] && prime[n-i]) // 둘 다 소수면
                {
                    sb.append(n).append(" = ").append(i).append(" + ").append(n-i);
                    System.out.println(sb);
                    sb.delete(0, sb.length());
                    break;
                }
            }
        }
    }
}
