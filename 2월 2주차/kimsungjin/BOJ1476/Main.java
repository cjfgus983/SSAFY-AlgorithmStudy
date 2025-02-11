import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; ; i++) {
            if (i % 15 == e - 1 && i % 28 == s - 1 && i % 19 == t - 1) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}