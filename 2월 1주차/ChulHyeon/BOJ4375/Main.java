package BOJ4375;


//2와 5로 나누어떨어지지 않는 정수 n(1 ≤ n ≤ 10000)가 주어졌을 때,
//각 자릿수가 모두 1로만 이루어진 n의 배수를 찾는 프로그램을 작성하시오.

// 1 씩 자리수를 늘려갈건데
// 3 이런 애들을 보자
// 11 % 7 == 4
// 111 % 7 == 6
// 1111 % 7 == 5

// 11 % 7 == 4
// 41 % 7 == 6
// 61 % 7 == 5

// 이런 식으로 나머지

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input;
        while((input = br.readLine()) != null)
        {
            int n = Integer.parseInt(input);
            int multi = 0;
            int cnt = 0;
            while(true)
            {
                multi = (multi * 10 + 1) % n;
                cnt++;
                if(multi % n == 0) // 나누어 떨어지면 종료
                {
                    break;
                }
            }
            System.out.println(cnt);
        }

    }




    // 오답 풀이
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        String inputStr = "";
//
//        while((inputStr = br.readLine()) != null)
//        {
//            StringBuilder sb = new StringBuilder("1");
//            int input = Integer.parseInt(inputStr);
//            // 1 씩 늘려가면서 input으로 나누어 지는게 정답임
//            boolean flag = true;
//            while(flag)
//            {
//                long multi = Long.parseLong(sb.toString());
//                if(multi%input == 0)
//                {
//                    flag = false;
//                    System.out.println(sb.length());
//                }
//                sb.append("1");
//            }
//        }
//    }
}