import java.io.*;
import java.util.*;

public class Main {
    static int l;
    static int c;
    static final String vowel = "aeiou";
    static char[] ch;
    static char[] password;
    static StringBuilder ans = new StringBuilder();

    static void getWord(int cnt, int index) {
        if (cnt == l) {
            int vowelCnt = 0;
            int consCnt = 0;
            for (char c : password) {
                if (vowel.contains(String.valueOf(c))) vowelCnt++;
                else consCnt++;
            }

            if (vowelCnt >= 1 && consCnt >= 3)
                ans.append(password);

            return;
        }

        if (index >= c) {
            return;
        }

        password[cnt] = ch[index];
        getWord(cnt + 1, index + 1);
        getWord(cnt, index + 1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        ch = new char[c];
        password = new char[l + 1];
        password[l] = '\n';

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            ch[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(ch);

        getWord(0, 0);
        System.out.println(ans);
    }
}