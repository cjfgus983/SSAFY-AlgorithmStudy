import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int ans = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void backTracking(int index, int cnt, int sum) {
        if (cnt == k) {
            ans = Math.max(ans, sum);
            return;
        }

        if (index == n * m) {
            return;
        }

        int x = index / m;
        int y = index % m;

        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;

            if (visited[nx][ny]) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            visited[x][y] = true;
            backTracking(index + 1, cnt + 1, sum + map[x][y]);
            visited[x][y] = false;
        }
        backTracking(index + 1, cnt, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 0, 0);
        System.out.println(ans);
    }
}