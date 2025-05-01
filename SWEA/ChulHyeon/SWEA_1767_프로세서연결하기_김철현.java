import java.io.*;
import java.util.*;

/*
*
* 1. 가장자리에 있는 코어를 제외한 내부에 있는 코어들을 list에 저장
* 2. dfs를 돌릴것
* 2-1. 파라미터는 (코어의 인덱스, 연결된 코어 개수, 전선 전체 길이 합)
* 3. 가장자리까지 쭉 보내봐서 막히는 부분이 없다면 => 맵이 1이나 2가 아니라면 선을 놓을 수 있따 !
* 3-1. 선을 놓을 수 있다면 해당 부분의 맵을 2로 바꾸어 전선을 두었다 라고 표시
* 3-2. 재귀
* 3-3. 선을 논 자리를 0으로바꾸어 롤백하고 다시 재귀
* 4. 기저조건은 인덱스가 코어리스트의 사이즈에 다다랐을 때
* 4-1.현재 연결된 수가 기존보다 많다면 무조건 갱신
* 4-2. 현재 연결된 코어 수가 같다면 전체 길이가 작은걸 선택
*
* */

public class SWEA_1767_프로세서연결하기_김철현 {

    static class Pos{
        int row;
        int col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int size, maxCore, minLength;
    static int[][] map;
    static List<Pos> cores = new ArrayList<>();
    static int[] dirR = {-1, 0, 1, 0};
    static int[] dirC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            size = Integer.parseInt(br.readLine());
            map = new int[size][size];
            cores.clear();

            for (int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 가장자리 코어는 이미 연결되어있음
                    if (map[i][j] == 1 && !(i == 0 || j == 0 || i == size - 1 || j == size - 1)) {
                        cores.add(new Pos(i, j));
                    }
                }
            }

            maxCore = 0;
            minLength = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(minLength).append("\n");
        }

        System.out.print(sb);
    }
    //             선택 인덱스     연결된 개수       길이
    static void dfs(int idx, int connected, int length) {
        // 기저 조건
        if (idx == cores.size()) {
            // 1. 현재 연결된 수가 많다면 길이가 더 크더라도 갱신해줘야 함
            if (connected > maxCore) {
                maxCore = connected;
                minLength = length;
                // 2. 연결된 수가 같다면 더 작은걸로 결과를 갱신
            } else if (connected == maxCore) {
                minLength = Math.min(minLength, length);
            }
            return;
        }

        int nowR = cores.get(idx).row;
        int nowC = cores.get(idx).col;

        for (int dir = 0; dir < 4; dir++) {
            int len = 0;
            int nextR = nowR;
            int nextC = nowC;

            boolean canLay = true; // 이 방향으로 놀 수 있는지 체크
            // 가장자리까지 쭉 보내보기
            while (true) {
                nextR += dirR[dir];
                nextC += dirC[dir];

                if (nextR < 0 || nextC < 0 || nextR >= size || nextC >= size) break; // 가장자리 도착
                if (map[nextR][nextC] != 0) {
                    canLay = false;
                    break;
                }
                len++;
            }

            if (canLay && len > 0) {
                // 전선 설치
                nextR = nowR;
                nextC = nowC;
                // 이 길이만큼 선을 2로 변경
                for (int i = 0; i < len; i++) {
                    nextR += dirR[dir];
                    nextC += dirC[dir];
                    map[nextR][nextC] = 2;
                }
                // 재귀 호출
                dfs(idx + 1, connected + 1, length + len);

                // 전선 복원
                nextR = nowR;
                nextC = nowC;
                // 다시 이 길이만큼 복원
                for (int i = 0; i < len; i++) {
                    nextR += dirR[dir];
                    nextC += dirC[dir];
                    map[nextR][nextC] = 0;
                }
            }
        }
        // 연결하지 않는 선택
        dfs(idx + 1, connected, length);
    }
}