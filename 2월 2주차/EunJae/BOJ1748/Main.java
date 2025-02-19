package BOJ1748;
/*
 * 문제 수 이어 쓰기
1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.

1234567891011121314151617181920212223...

이렇게 만들어진 새로운 수는 몇 자리 수일까? 이 수의 자릿수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 100,000,000)이 주어진다.

출력
첫째 줄에 새로운 수의 자릿수를 출력한다.

예제 입력 1 
5
예제 출력 1 
5
예제 입력 2 
15
예제 출력 2 
21
예제 입력 3 
120
예제 출력 3 
252

1~9 +1 N
10~99 +2 
100~999 +3
1000~9999 +4
5-> 1이 5개
12 -> 2가 3개, 1이 9개
138 -> 3이 39개, 2가 90개 1이 9개
1234 -> 4가 235개 3이 900개,2가 90개 2가 1이9개
120 -> 3이 21개, 2가 90개 1이 9개
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());

        int length = 1;  // 현재 자리수
        int count = 9;   // 해당 자리수에서 가능한 숫자의 개수
        int total = 0;   // 총 자릿수 합

        int num = N;

        while (num > count) {
            total += length * count;  // 현재 자리수의 숫자 개수만큼 더함
            num -= count;             // 남은 숫자 갱신
            length++;                 // 자리수 증가
            count *= 10;              // 자리수 증가에 따른 숫자 개수 증가
//            System.out.println(length+":"+total+","+num);
        }

        // 마지막 남은 자리수 처리
        total += length * num;

        System.out.println(total);
	}
}
