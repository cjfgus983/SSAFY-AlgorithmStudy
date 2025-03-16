import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
  static int n;
  static int[] days;
  static int[] fee;
  static int maxFee;
  static int totalFee;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    n = Integer.parseInt(br.readLine());
    days = new int[n];
    fee = new int[n];
    maxFee = 0;
    totalFee = 0;

    // 기간과 금액 입력받음.
    for (int idx = 0; idx < n; idx++) {
      st = new StringTokenizer(br.readLine());
      days[idx] = Integer.parseInt(st.nextToken());
      fee[idx] = Integer.parseInt(st.nextToken());
    }

    // 재귀호출 Start
    acceptCounsel(0);

    System.out.println(maxFee);

  }

  public static void acceptCounsel(int dayIdx) {
    // 종료조건
    if (dayIdx >= n) {
      maxFee = Math.max(maxFee, totalFee);
      return;
    }

    // 1. dayIdx날 선택하는 경우
    if (dayIdx + days[dayIdx] <= n) {

      totalFee += fee[dayIdx];
      acceptCounsel(dayIdx + days[dayIdx]);
      totalFee -= fee[dayIdx];
    }

    // 2. dayIdx날에 상담을 선택하지 않고 다음날로 넘어감.
    acceptCounsel(dayIdx + 1);

  }
}
