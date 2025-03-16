import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_14889_스타트와링크 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static int[][] stats;
  static int N, minDiff;
  static boolean[] isSelected = new boolean[20];

  public static void main(String[] args) throws IOException {

    N = Integer.parseInt(br.readLine()); // 총 인원
    stats = new int[N][N];

    for (int row = 0; row < N; row++) {
      st = new StringTokenizer(br.readLine());
      for (int col = 0; col < N; col++) {
        stats[row][col] = Integer.parseInt(st.nextToken());
      }
    }

    minDiff = Integer.MAX_VALUE;
    makeComb(0, 0);

    System.out.println(minDiff);
  }

  public static void makeComb(int count, int itemIdx) {
    // 한 팀(n/2)를 다 뽑았을 때.
    if (count == N / 2) {
      int teamAStats = 0, teamBStats = 0;

      // teamA, teamB의 능력치 계산
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (isSelected[i] && isSelected[j]) {
            teamAStats += stats[i][j];
          }
          if (!isSelected[i] && !isSelected[j]) {
            teamBStats += stats[i][j];
          }
        }
      }

      minDiff = Math.min(minDiff, Math.abs(teamAStats - teamBStats));
      return;
    }

    if (itemIdx == N - 1) {
      return;
    }

    isSelected[itemIdx] = true;
    makeComb(count + 1, itemIdx + 1);
    isSelected[itemIdx] = false;
    makeComb(count, itemIdx + 1);

  }

}
