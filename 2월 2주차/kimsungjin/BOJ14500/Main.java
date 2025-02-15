import java.io.*;
import java.util.*;

public class Main {
    static int[][][] tetromino = {
            {{0, 0, 0, 0}, {0, 1, 2, 3}},
            {{0, 1, 2, 3}, {0, 0, 0, 0}},

            {{0, 0, 1, 1}, {0, 1, 0, 1}},

            {{0, 1, 2, 2}, {0, 0, 0, 1}},
            {{0, 0, 0, 1}, {0, 1, 2, 0}},
            {{0, 0, 1, 2}, {0, 1, 1, 1}},
            {{0, 1, 1, 1}, {0, -2, -1, 0}},

            {{0, 1, 2, 2}, {0, 0, 0, -1}},
            {{0, 1, 1, 1}, {0, 0, 1, 2}},
            {{0, 0, 1, 2}, {0, 1, 0, 0}},
            {{0, 0, 0, 1}, {0, 1, 2, 2}},

            {{0, 1, 1, 2}, {0, 0, 1, 1}},
            {{0, 0, -1, -1}, {0, 1, 1, 2}},
            {{0, 1, 1, 2}, {0, 0, -1, -1}},
            {{0, 0, 1, 1}, {0, 1, 1, 2}},

            {{0, 0, 0, 1}, {0, 1, 2, 1}},
            {{0, 1, 2, 1}, {0, 0, 0, -1}},
            {{0, 0, 0, -1}, {0, 1, 2, 1}},
            {{0, 1, 2, 1}, {0, 0, 0, 1}}
    };

    static int n;
    static int m;
    static int[][] board;
    static int ans = 0;

    static void getTetromino(int x, int y) {
        for (int i = 0; i < 19; i++) {
            int size = 0;
            boolean flag = true;

            for (int k = 0; k < 4; k++) {
                int nx = x + tetromino[i][0][k];
                int ny = y + tetromino[i][1][k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    flag = false;
                    break;
                }

                size += board[nx][ny];
            }

            if (flag) ans = Math.max(ans, size);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                getTetromino(i, j);
            }
        }

        System.out.println(ans);
    }
}