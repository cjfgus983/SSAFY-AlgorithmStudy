package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static char[][] input;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine().trim());
        
        arr = new int[n];
        input = new char[n][n];
        
        String str = br.readLine().trim();
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                input[i][j] = str.charAt(num++);
            }
        }
        
        solution(0);
    }
    
    public static void solution(int count) {
        
        if (count == n) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.exit(0);  // 프로그램을 종료합니다.
        }
        
        for (int i = -10; i < 11; i++) {
            arr[count] = i;
            if (isValid(count + 1)) {
                solution(count + 1);
            }
        }
    }
    
    public static boolean isValid(int count) {
        for (int i = 0; i < count; i++) {
            int sum = 0;
            for (int j = i; j < count; j++) {
                sum += arr[j];
                switch (input[i][j]) {
                    case '+':
                        if (sum <= 0) return false;
                        break;
                    case '0':
                        if (sum != 0) return false;
                        break;
                    case '-':
                        if (sum >= 0) return false;
                        break;
                }
            }
        }
        return true;
    }
}
