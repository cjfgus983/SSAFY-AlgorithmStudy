import java.io.*;

public class Main {
    static int k;
    static String[] inequality;
    static int[] ansBuffer;
    static boolean[] visited;
    static String max;
    static String min;

    static void solve(int cnt) {
        if (cnt > k) {
            String str = bufferToString();
            if (max.compareTo(str) < 0) {
                max = str;
            }

            if (min.compareTo(str) > 0) {
                min = str;
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i])
                continue;

            if (inequality[cnt - 1].equals("<")) {
                if (ansBuffer[cnt - 1] > i) continue;
                visited[i] = true;
                ansBuffer[cnt] = i;
                solve(cnt + 1);
                visited[i] = false;
            } else {
                if (ansBuffer[cnt - 1] < i) continue;
                visited[i] = true;
                ansBuffer[cnt] = i;
                solve(cnt + 1);
                visited[i] = false;
            }
        }
    }

    static String bufferToString() {
        StringBuilder sb = new StringBuilder();
        for (int i : ansBuffer) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        visited = new boolean[10];
        ansBuffer = new int[k + 1];
        inequality = br.readLine().split(" ");

        max = "0".repeat(k + 1);
        min = "9".repeat(k + 1);

        for (int i = 0; i < 10; i++) {
            ansBuffer[0] = i;
            visited[i] = true;
            solve(1);
            visited[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }
}