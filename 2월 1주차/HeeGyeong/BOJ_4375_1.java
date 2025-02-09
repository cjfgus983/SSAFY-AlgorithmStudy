import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4375_1 {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;

    while ((line = br.readLine()) != null && !line.isEmpty()) {
      int inputNum = Integer.parseInt(line);
      int digit = 1;
      int only1Num = 1;

      while (only1Num % inputNum != 0) {
        only1Num = ((only1Num * 10) + 1) % inputNum;
        digit += 1;
      }

      System.out.println(digit);

    }

  }

}
