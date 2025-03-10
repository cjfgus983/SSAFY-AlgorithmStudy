import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* 재귀로 돌릴건데 같이 있는 것에 대한 제한이 걸린 놈들 처리가 문제임
* 2차원 배열을 하나 추가로 두자!!
* ban[1][2] = true 가 의미하는 것은
* 1 재료와 2 재료가 동시에 같은 곳에 들어갈 수 없다는 것이다.
*
* */


public class SWEA_3421_수제버거장인_김철현 {

    static boolean[][] ban;
    static boolean[] check;

    static int ingredientCnt;
    static int conditionCnt;
    static int result;

    static void dfs(int nowIdx){
        if(nowIdx > ingredientCnt){
            // 기저조건에 닿으면 출력
            result++;
            return;
        }
        // 1. 현재 재료 선택 x
        dfs(nowIdx +1);

        // 2. 현재 재료를 선택할 수 있는지 확인
        boolean canSelect = true;
        for (int i = 1; i <= ingredientCnt; i++) {
            if (check[i] && ban[nowIdx][i]) { // for 문을 돌면서 체크되어있는데(이미 재료로 들어가 있는데) 나랑 ban되어 있다면
                canSelect = false;
                break;
            }
        }

        // 3. 선택 가능한 경우 선택하고 탐색
        if (canSelect) {
            check[nowIdx] = true;
            dfs(nowIdx + 1);
            check[nowIdx] = false; // 백트래킹
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine().trim());
            ingredientCnt = Integer.parseInt(st.nextToken()); // 재료의 개수
            conditionCnt = Integer.parseInt(st.nextToken()); // 조건의 개수

            // 초기화
            result = 0;
            check = new boolean[ingredientCnt + 1];
            ban = new boolean[ingredientCnt + 1][ingredientCnt + 1];
            while((conditionCnt--)>0){
                st = new StringTokenizer(br.readLine().trim());
                int first = Integer.parseInt(st.nextToken()); // 첫 번째 재료
                int second = Integer.parseInt(st.nextToken()); // 두 번째 재료
                ban[first][second] = true;
                ban[second][first] = true;
            }
            dfs(1);

            sb.append("#").append(testCase).append(" ").append(result).append('\n');
        }
        System.out.println(sb.toString());
    }
}
