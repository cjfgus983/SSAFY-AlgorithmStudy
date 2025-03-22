import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[12];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4; i<=11; i++){
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }
}