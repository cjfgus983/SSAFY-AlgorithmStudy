package BOJ3085;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/*
가장 긴 행과 가장 긴 열 중 하나 선택가능
1. 한 번 교환 가능

 */

public class Main {

    static char[][] map;

    static int[] dr = {0,1};
    static int[] dc = {1,0};
    static int result = 0;

    static void changeAndCheck(int row, int col, char nowValue)
    {
        for(int i=0;i<2;i++)
        {
            int nextRow= row+dr[i];
            int nextCol= col+dc[i];
            // 범위 체크
            if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map.length)
                continue;
            // 다음꺼랑 다를 때만 교환
            if(nowValue != map[nextRow][nextCol])
            {
                char tmp = map[row][col];
                map[row][col] = map[nextRow][nextCol];
                map[nextRow][nextCol] = tmp;
            }
            else // 안 다르면 교환 안함
            {
                continue;
            }
            // 교환한 상태에서
            // 가장 긴 부분 찾기 - 1 행
            for(int r = 0; r<map.length;r++)
            {
                char nowChar = map[r][0];
                int cnt = 1;
                for(int c = 1; c<map.length;c++)
                {
                    if(nowChar == map[r][c]) // 같으면 cnt 증가
                    {
                        cnt++;
                    }
                    else{
                        result = Math.max(result,cnt);
                        cnt = 1;
                        nowChar = map[r][c];
                    }
                }
                result = Math.max(result,cnt);
            }
            // 가장 긴 부분 찾기 - 1 열
            for(int c = 0; c<map.length;c++)
            {
                char nowChar = map[0][c];
                int cnt = 1;
                for(int r = 1; r<map.length;r++)
                {
                    if(nowChar == map[r][c]) // 같으면 cnt 증가
                    {
                        cnt++;
                    }
                    else{
                        result = Math.max(result,cnt);
                        cnt = 1;
                        nowChar = map[r][c];
                    }
                }
                result = Math.max(result,cnt);
            }
            // 원상복구
            char tmp = map[row][col];
            map[row][col] = map[nextRow][nextCol];
            map[nextRow][nextCol] = tmp;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        System.out.println(dr.length);

        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        // 입력
        for(int i=0; i<n; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = inputString.charAt(j);
            }
        }

        // 알고리즘 - 바꿔봐야 하지
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                changeAndCheck(i,j,map[i][j]);
            }
        }
        System.out.println(result);
    }
}
