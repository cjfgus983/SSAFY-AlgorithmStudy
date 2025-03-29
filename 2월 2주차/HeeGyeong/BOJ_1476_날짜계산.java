import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1476_날짜계산 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int targetE = Integer.parseInt(st.nextToken());
    int targetS = Integer.parseInt(st.nextToken());
    int targetM = Integer.parseInt(st.nextToken());

    int year = 1, currentE = 1, currentS = 1, currentM = 1;

    while (true) {
      if (currentE == targetE && currentS == targetS && currentM == targetM) {
        System.out.println(year);
        break;
      }

      currentE = ++currentE > 15 ? 1 : currentE;
      currentS = ++currentS > 28 ? 1 : currentS;
      currentM = ++currentM > 19 ? 1 : currentM;
      year++;

    }
  }
}
