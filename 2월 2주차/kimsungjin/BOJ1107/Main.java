import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int ans;
    static String[] brokenNum;

    static boolean isBroken(int channel) {
        String channelString = String.valueOf(channel);

        for (int i = 0; i < m; i++) {
            if (channelString.contains(brokenNum[i]))
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        brokenNum = new String[m];

        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                brokenNum[i] = st.nextToken();
            }
        }

        ans = Math.abs(n - 100);

        for (int i = 0; i < n + ans; i++) {
            if (!isBroken(i)) {
                ans = Math.min(ans, Math.abs(n - i) + String.valueOf(i).length());
            }
        }

        System.out.println(ans);
    }
}