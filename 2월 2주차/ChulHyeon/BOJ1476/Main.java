package BOJ1476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int result = 1;
        while(true)
        {
            if(result % 15 == E && result % 28 == S && result % 19 == N)
            {
                break;
            }
            result++;
        }
        System.out.println(result);
    }
}
