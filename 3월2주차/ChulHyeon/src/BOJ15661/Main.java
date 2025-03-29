package BOJ15661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int[][] arr;
    static boolean[] teamA;
    static int minimumResult;

    // 조합
    static void combination(int now, int cnt, int teamSize){
        if(cnt >= teamSize)
        {
            // 조합이 다 되었다면 이제 계산을 해야겠지
            pick();
            return;
        }
        for(int i = now + 1; i < size; i++){
            teamA[i] = true;
            combination( i, cnt + 1, teamSize);
            teamA[i] = false;
        }
    }

    static void pick(){
        int teamAsum = 0;
        int teamBsum = 0;
        // 2중 포문으로 2개를 선택해서
        for(int i = 0; i< size;i++) // teamA 하나를 선택하는 반복문
        {
            if(teamA[i])
            {
                for(int j=i+1; j< size;j++) // teamA 나머지 하나를 선택하는 반복문
                {
                    if(teamA[j])
                    {
                        teamAsum += arr[i][j];
                        teamAsum += arr[j][i];
                    }
                }
            }
            else
            {
                for(int j=i+1; j< size;j++) // teamA 나머지 하나를 선택하는 반복문
                {
                    if(!teamA[j])
                    {
                        teamBsum += arr[i][j];
                        teamBsum += arr[j][i];
                    }
                }
            }
        }
        minimumResult = Math.min(minimumResult,Math.abs(teamBsum-teamAsum));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        size = Integer.parseInt(br.readLine().trim());
        // 초기화
        arr = new int[size][size];
        teamA = new boolean[size];
        minimumResult = Integer.MAX_VALUE;
        // 맵 입력
        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for(int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 알고리즘
        for(int i=1;i<size/2-1; i++)
        {
            combination(-1,0, i);
        }

        // 출력
        System.out.println(minimumResult);
    }
}
