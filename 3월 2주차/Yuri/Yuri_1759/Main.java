package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] chars;
    static char[] result;
    static List<String> passwords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine().trim());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        chars = new char[C];
        result = new char[L];
        
        st = new StringTokenizer(br.readLine().trim());
        
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(chars);
        
        solution(0, 0);
        
        for (String password : passwords) {
            System.out.println(password);
        }
    }

    public static void solution(int start, int depth) {
        if (depth == L) {
            if (isValid(result)) {
                passwords.add(new String(result));
            }
            return;
        }
        
        for (int i = start; i < C; i++) {
            result[depth] = chars[i];
            solution(i + 1, depth + 1);
        }
    }

    public static boolean isValid(char[] password) {
        int vowels = 0;
        int consonants = 0;
        
        for (char c : password) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
        
        return vowels >= 1 && consonants >= 2;
    }
}
