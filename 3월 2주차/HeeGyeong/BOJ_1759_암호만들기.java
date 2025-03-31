import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_1759_암호만들기 {
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static char chars[];
  static int[] nums = new int[8];
  static int isVowel, L, C;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken()); // 암호의 길이
    C = Integer.parseInt(st.nextToken()); // 가능성 있는 문자의 개수
    chars = new char[C];

    st = new StringTokenizer(br.readLine());
    for (int idx = 0; idx < C; idx++) {
      chars[idx] = st.nextToken().charAt(0);
    }
    Arrays.sort(chars); // 입력받아서 사전순으로 정렬.

    // 비트연산 위해 모음의 인덱스에 1의 값을 넣은 isVowel 초기화
    for (int idx = 0; idx < C; idx++) {
      int c = chars[idx];
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        isVowel |= (1 << idx);
      }
    }

    // 가능한 암호 찾기
    makeSubset(0, 0);

    System.out.println(sb);

  }

  public static void makeSubset(int count, int selectedInfo) { // 현재 고려 요소 idx, 선택된 문자 bitmask

    int selectedCharCount = Integer.bitCount(selectedInfo);
    int vowelCount = Integer.bitCount(selectedInfo & isVowel);

    // 선택된 문자의 개수가 암호의 길이와 같은 경우
    if (selectedCharCount == L) {
      // 모음의 개수가 1이상, 자음의 개수가 2이상인 경우
      if (vowelCount >= 1 && (selectedCharCount - vowelCount) >= 2) {
        // 선택된 문자들 출력
        for (int idx = 0; idx < C; idx++) {
          if ((selectedInfo & (1 << idx)) != 0) {
            sb.append(chars[idx]);
          }
        }
        sb.append('\n');
      }

      return;
    }

    // 모든 요소를 다 고려했을 경우 재귀 호출 종료.
    if (count == C) {
      return;
    }

    // 현재 고려요소 + 1, 현재 요소 선택한 다음 재귀 호출
    makeSubset(count + 1, selectedInfo | (1 << count));
    // 고려요소에 +1 해주고 재귀 호출.
    makeSubset(count + 1, selectedInfo);

  }
}
