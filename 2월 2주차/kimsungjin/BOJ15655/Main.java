import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int x;
    static int y;
    static int[] num;

    // 현재 인덱스, 골라야 하는 원소 개수
    static void getComb(int n, int r, String comb) {
        if (n < r) return;

        if (r == 0) System.out.println(comb);
        else {
            getComb(n - 1, r - 1, comb + num[x - n + 1] + " ");
            getComb(n - 1, r, comb);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        num = new int[x + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= x; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        getComb(x, y, "");
    }
}