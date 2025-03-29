import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

//백준 1107번
public class BOJ_1107_리모컨 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int targetNum = Integer.parseInt(br.readLine());
    int brokenBtsCount = Integer.parseInt(br.readLine());
    HashSet<Integer> brokenBtnSet = new HashSet<>();
    // 고장난 버튼이 있는 경우만 입력받음.
    if (brokenBtsCount != 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int idx = 0; idx < brokenBtsCount; idx++) {
        brokenBtnSet.add(Integer.parseInt(st.nextToken()));
      }
    }

    int minPushCount = Integer.MAX_VALUE;

    // 1. 이동하려고 하는 채널이 100인 경우 -> 0 출력 후 종료한다.
    if (targetNum == 100) {
      System.out.println(0);
      return;
    }

    // 2. 100에서 +/- 버튼만 눌러서 이동하는 경우
    minPushCount = Math.min(minPushCount, Math.abs(targetNum - 100));

    // 3. 번호를 누르는 경우 -> 0~999,999까지 가능한 경우를 다 해본다.
    HashSet<Integer> availableNumBtnSet = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    availableNumBtnSet.removeAll(brokenBtnSet);

    for (int closestNum = 0; closestNum <= 999_999; closestNum++) {
      // 누를 수 있는 숫자인지 확인함
      String closestNumStr = Integer.toString(closestNum);
      boolean flag = true;
      for (int i = 0; i < closestNumStr.length(); i++) {
        // 고장난 버튼 숫자가 포함되어 있으면
        if (!availableNumBtnSet.contains(closestNumStr.charAt(i) - '0')) {
          flag = false;
          break;
        }
      }
      // 누를 수 있는 숫자면 -> (숫자 누르는 횟수 + -/+ 누르는 횟수 계산 해서 minPush값이랑 비교함)
      if (flag == true) {
        minPushCount = Math.min(minPushCount, closestNumStr.length() + Math.abs(targetNum - closestNum));
      }
    }

    System.out.println(minPushCount);

  }

}