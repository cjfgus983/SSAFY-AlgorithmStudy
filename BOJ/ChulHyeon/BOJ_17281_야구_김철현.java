package ChulHyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

9팩으로 먼저 순열을 정하고
게임 돌여봐야 할 것 같은데
일단 1번은 4번타자 고정 이거 주의하자

1. 재귀 함수로 순열을 찾는다
1-1. 근데 0번 인덱스의 선수는 순열 3번에 고정해야 하므로 재귀함수에서 현재 cnt가 3일 경우 다른 조건을 추가해서 4번 인덱스로 넘긴다
2. 기저 조건에서 game() 매서드를 실행한다.
3. game 에서는 시작할 때 주요 변수들을 초기화한다.
3-1. 각각 1타, 2타, 3타, 홈런일 때 처리를 if문을 통해 나눠서 해당 순열에서의 sum을 구한다.
3-2. 최종 result의 값을 갱신하여 결과 출력
*/
public class BOJ_17281_야구_김철현 {

    static int inning; // 총 경기 이닝
    static int[] sequence = new int[9];
    static boolean[] check = new boolean[9];
    static int result = 0;
    static int[][] player; // player[0][1] 은 1이닝에서 1선수가 치는 공

    static int nowInning = 0;
    static int nowPlayerIdx = 0; // 처음엔 0번째 선수
    static int outCnt = 0; // 현재 아웃 횟수
    static boolean[] juru = new boolean[3];


    static int game()
    {
        int sum = 0;
        nowInning = 0;
        nowPlayerIdx = 0;
        outCnt = 0;
        juru = new boolean[3];
        while(true)
        {
            if(outCnt >= 3 && nowInning == inning - 1){ // 게임 자체가 끝날 조건
                result = Math.max(result, sum);
                break;
            }
            else if(outCnt >= 3) // 이닝 하나가 끝날 조건
            {
                outCnt = 0;
                nowInning++;
                juru = new boolean[3]; // 주자 상태 초기화
            }
            else { // 그냥 게임 할 때
                if(player[nowInning][sequence[nowPlayerIdx]] == 0) { // 현재 플레이어 아웃 일 떄
                    outCnt++;
                    nowPlayerIdx = (nowPlayerIdx + 1) % 9;
                }
                else if(player[nowInning][sequence[nowPlayerIdx]] == 1) { // 현재 플레이어 안타 일 때
                    if(juru[2] == true) // 3루수 있었으면
                    {
                        sum++;
                        juru[2] = false;
                    }
                    if(juru[1] == true) { // 2루수 있었으면
                        juru[2] = true;
                        juru[1] = false;
                    }
                    if(juru[0] == true) { // 1루수 있었으면
                        juru[1] = true;
                        juru[0] = false;
                    }
                    // 현재 타자 1루로
                    juru[0] = true;
                    nowPlayerIdx = (nowPlayerIdx + 1) % 9;
                }
                // 현재 플레이어 2루타 일 때
                else if(player[nowInning][sequence[nowPlayerIdx]] == 2) {
                    if(juru[2] == true) // 3루수 있었으면
                    {
                        sum++;
                        juru[2] = false;
                    }
                    if(juru[1] == true) { // 2루수 있었으면
                        sum++;
                        juru[1] = false;
                    }
                    if(juru[0] == true) { // 1루수 있었으면
                        juru[2] = true;
                        juru[0] = false;
                    }
                    // 현재 타자 2루로
                    juru[1] = true;
                    nowPlayerIdx = (nowPlayerIdx + 1) % 9;
                }
                // 현재 플레이어 3루타 일 때
                else if(player[nowInning][sequence[nowPlayerIdx]] == 3) {
                    if(juru[2] == true) // 3루수 있었으면
                    {
                        sum++;
                        juru[2] = false;
                    }
                    if(juru[1] == true) { // 2루수 있었으면
                        sum++;
                        juru[1] = false;
                    }
                    if(juru[0] == true) { // 1루수 있었으면
                        sum++;
                        juru[0] = false;
                    }
                    // 현재 타자 2루로
                    juru[2] = true;
                    nowPlayerIdx = (nowPlayerIdx + 1) % 9;
                }
                else if(player[nowInning][sequence[nowPlayerIdx]] == 4) { // 홈런일 때
                    int juruCnt = 0;
                    for(int idx = 0; idx < 3;idx++)
                    {
                        if(juru[idx]) {
                            juruCnt++;
                            juru[idx] = false;
                        }
                    }
                    sum += juruCnt;
                    sum++;
                    nowPlayerIdx = (nowPlayerIdx + 1) % 9;
                }
            }
        }
        return sum;
    }

    static void dfs(int cnt)
    {
        if(cnt >= 9) // 여기서 순열은 완성 됐고 게임 돌려야함
        {
            result = Math.max(result, game());
            return;
        }
        for(int idx=0;idx<9;idx++)
        {
            if(check[idx] == true)
                continue;
            if(cnt == 3)
            {
                cnt++;
                sequence[cnt] = idx;
                check[idx] = true;
                dfs(cnt+1);
                check[idx] = false;
            }
            else {
                sequence[cnt] = idx;
                check[idx] = true;
                dfs(cnt+1);
                check[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 3번 타자로 고정인 1번 선수
        sequence[3] = 0;
        check[0] = true;
        // 입력
        inning = Integer.parseInt(br.readLine().trim());
        player = new int[inning][9];
        for(int nowInning = 0; nowInning < inning; nowInning++)
        {
            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0; idx < 9; idx++)
            {
                player[nowInning][idx] = Integer.parseInt(st.nextToken());
            }
        }

        // 먼저 재귀로 순열 정하기
        dfs(0);
        System.out.println(result);
    }
}