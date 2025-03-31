package BOJ13458;

import java.util.Scanner;

/*
총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.

감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 B명이고, 부감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 C명이다.

각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.

각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n 개의 시험장
        // 각 시험장에 있는 응시자 수
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int B = sc.nextInt(); // 총 시험 감독관이 한 반에서 케어 가능한 응시자 수
        int C = sc.nextInt(); // 부 시험 감독관이 한 반에서 케어 가능한 응시자 수
        // 각 시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 ((최소 수))를 출력한다.
        // 총 감독관은 한 반에 하나 // 부 감독관은 여러명 있을 수 있음
        long result = 0;
        for(int i = 0; i < n; i++) {
            // 총 감독관 하나 넣기
            result++;
            if(arr[i] - B <= 0) // 총 감독관 하나로 되는 경우
            {
                continue;
            }
            else
            {
                arr[i] = arr[i] - B;
                result = result + (arr[i] / C);
                if(arr[i] % C == 0)
                    continue;
                else
                    result++;
            }
        }
        System.out.println(result);
    }
}
