/*
5 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4
4 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3
5 0 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5
5 0 0 3 1 1 2 1 2 2 0 3 0 0 5 1 0 4

1. 재귀 인자로 팀A 인덱스, 팀B 인덱스를 넣는다.
 1-1. 팀A가 6까지 갔다는건 다 돌았다는 것
 1-2. 팀B가 6까지 갔다는건 팀A와 매칭할 수 있는 경우의 수를 돌았다는 것
2. 각 경우의 수를 보며 어느 한쪽이 승이고 어느 한쪽이 패 일때 가지고 있는 가능 승/패의 영이 1 이상이면 재귀 호출
    2-1. 원상복구 꼭 해줘야 함

* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵_김철현 {
    static int[][] countries = new int[6][3];
    static boolean isValid;

    static void dfs(int teamA, int teamB) {
        // 모든 경기(15경기)를 다 확인했으면 검증 완료
        if (teamA == 6) {
            isValid = true;
            return;
        }

        // 다음 경기 팀 설정 (teamB 증가)
        if (teamB == 6) {
            dfs(teamA + 1, teamA + 2); // 다음 국가로 넘어감
            return;
        }

        // A가 승, B가 패
        if (countries[teamA][0] > 0 && countries[teamB][2] > 0) {
            countries[teamA][0]--;
            countries[teamB][2]--;
            dfs(teamA, teamB + 1);
            countries[teamA][0]++;
            countries[teamB][2]++;
        }

        // A와 B가 무승부
        if (countries[teamA][1] > 0 && countries[teamB][1] > 0) {
            countries[teamA][1]--;
            countries[teamB][1]--;
            dfs(teamA, teamB + 1);
            countries[teamA][1]++;
            countries[teamB][1]++;
        }

        // B가 승, A가 패
        if (countries[teamA][2] > 0 && countries[teamB][0] > 0) {
            countries[teamA][2]--;
            countries[teamB][0]--;
            dfs(teamA, teamB + 1);
            countries[teamA][2]++;
            countries[teamB][0]++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 입력
        for(int testCase = 1; testCase <=4; testCase++) {
            st = new StringTokenizer(br.readLine());
            int country = 0;
            int winDrawLose = 0;
            int totalScore = 0;
            isValid = false;
            for(int idx = 0; idx < 18; idx++) {
                country = idx / 3;
                winDrawLose = idx % 3;
                int score = Integer.parseInt(st.nextToken());
                countries[country][winDrawLose] = score;
                if(score > 0) totalScore += score;
            }

            dfs(0, 1);
            if(totalScore != 30)
                isValid = false;
            if(isValid)
                sb.append(1).append(" ");
            else
                sb.append(0).append(" ");
        }
        System.out.println(sb.toString().trim());


    }
}
