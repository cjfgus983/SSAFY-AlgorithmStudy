import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * idea: 9개의 원소에서 7개를 고르는 것은 9C7=36으로 그 경우의 수가 적으므로 완전탐색이 가능하다.
 *       9C7=9C2이므로 입력받을 때 9개 요소의 합을 같이 구하고,
 *       서로 다른 원소를 2개씩 빼서 7개 요소의 합이 100이 되는 경우를 확인한다.
 */

public class BOJ_2309_일곱난쟁이 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] heights = new int[9];
    int heightSum = 0;
    
    for (int idx = 0; idx < 9; idx++) {
      heights[idx] = Integer.parseInt(br.readLine());
      heightSum += heights[idx];
    }

    Arrays.sort(heights);
    
    for (int excl1 = 0; excl1 < 9; excl1++) {
      for (int excl2 = excl1; excl2 < 9; excl2++) {
        if ((heightSum - heights[excl1] - heights[excl2]) == 100) {

          for(int idx = 0; idx < 9; idx++) {
            if (idx != excl1 && idx != excl2) {
              sb.append(heights[idx] + "\n");
            }
          }

          System.out.println(sb);
          return;
        }
      }
    }
  }
}