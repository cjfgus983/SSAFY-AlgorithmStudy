package BOJ11660;

/*
*
* mapSize / testCase 입력
* map 입력
* testCase 입력
*
* 부분합을 사용해야 하므로 2차원 배열 0은 냅두고 1부터 시작하는것을 map으로 한다.
* 1. map 을 입력 받는다.
* 2. sum 2차원 배열을 두고 1행 가로열을 채운다
* 3. 1열 세로열을 채운다
* 4. 2 2 3 4 로 입력이 들어왔다면 sum[3][4] - sum[2][2] 가 정답!
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int mapSize= Integer.parseInt(st.nextToken()); // 맵의 크기
        int testCase = Integer.parseInt(st.nextToken()); // 테스트케이스 개수

        int map[][] = new int[mapSize + 1][mapSize + 1]; // 맵
        int sum[][] = new int[mapSize + 1][mapSize + 1]; // 누적합 배열

        // 1. 맵 입력
        for(int row = 1; row <= mapSize; row++) {
            st= new StringTokenizer(br.readLine());
            for(int col = 1; col <= mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        // 4. 누적합 계산
        for(int row = 1; row <= mapSize; row++) {
            for(int col = 1; col <= mapSize; col++) {
                sum[row][col] = map[row][col] + sum[row - 1][col] + sum[row][col - 1] - sum[row-1][col-1];
            }
        }
        // 5. 테케 만큼 구해서 출력
        while((testCase--) > 0)
        {
            st = new StringTokenizer(br.readLine());
            int firstR = Integer.parseInt(st.nextToken());
            int firstC = Integer.parseInt(st.nextToken());
            int secondR = Integer.parseInt(st.nextToken());
            int secondC = Integer.parseInt(st.nextToken());
            // 누적합 출력 이 때 내 기준 위에 줄 싹, 왼쪽 줄 싹 빼주고 11시방향 대각선 부분은 2번 빼준 거므로 한번 더해준다.
            sb.append(sum[secondR][secondC] - sum[firstR - 1][secondC] - sum[secondR][firstC-1]
            + sum[firstR-1][firstC-1]).append('\n');
        }
        System.out.println(sb.toString());
    }
}
