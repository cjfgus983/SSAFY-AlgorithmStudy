import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_15661_링크와스타트 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static int[][] stats;
  static int N, minDiff;
  static boolean[] isSelected = new boolean[20];

  public static void main(String[] args) throws IOException {

    N = Integer.parseInt(br.readLine()); // 총 인원
    stats = new int[N][N]; // 능력치

    // 입력받음
    for (int row = 0; row < N; row++) {
      st = new StringTokenizer(br.readLine());
      for (int col = 0; col < N; col++) {
        stats[row][col] = Integer.parseInt(st.nextToken());
      }
    }

    minDiff = Integer.MAX_VALUE; // 능력치의 차이 최솟값
    makeSubset(0); // 부분집합 계산

    System.out.println(minDiff);
  }

  public static void makeSubset(int count) {

    int teamAStats = 0, teamBStats = 0, teamACount = 0;
    // teamA, teamB의 능력치 계산
    for (int i = 0; i < N; i++) {
      if (isSelected[i] == true) {
        teamACount += 1; // teamA의 인원 계산
      }
      for (int j = 0; j < N; j++) {
        // stats[][] 전체 순회하면서 둘이 같은 팀일경우 능력치에 더함.
        if (isSelected[i] && isSelected[j]) {
          teamAStats += stats[i][j];
        }
        if (!isSelected[i] && !isSelected[j]) {
          teamBStats += stats[i][j];
        }
      }
    }

    // teamA의 인원이 1명 이상 총인원 미만인 경우만 능력치 최솟값 업데이트
    if (teamACount >= 1 && teamACount < N) {
      minDiff = Math.min(minDiff, Math.abs(teamAStats - teamBStats));
    }

    if (count == N - 1) { // 총인원 다 고려했을 때 -> return
      return;
    }

    isSelected[count] = true;
    makeSubset(count + 1);
    isSelected[count] = false;
    makeSubset(count + 1);

  }

}
