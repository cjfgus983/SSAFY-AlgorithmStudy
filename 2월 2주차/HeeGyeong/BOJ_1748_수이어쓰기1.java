import java.util.Scanner;

public class BOJ_1748_수이어쓰기1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int resultLength = 0;

    int nLength = String.valueOf(n).length();

    // (주어진 n의 자릿수-1)까지의 길이를 구함
    for (int digit = 1; digit < nLength; digit++) {
      resultLength += 9 * Math.pow(10, digit - 1) * digit;
    }

    // (주어진 n의 자릿수 길이)와 자릿수가 같은 숫자들의 길이를 마저 더함
    int startNum = (int) Math.pow(10, nLength - 1);
    resultLength += (n - startNum + 1) * nLength;

    System.out.println(resultLength);

  }
}
