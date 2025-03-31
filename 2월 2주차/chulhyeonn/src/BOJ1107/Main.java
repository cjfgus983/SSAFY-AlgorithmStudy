package BOJ1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean[] ban = new boolean[10];
        // 입력
        int want = Integer.parseInt(br.readLine());
        int banCnt=Integer.parseInt(br.readLine());
        if(banCnt != 0)
        {
            String input = br.readLine();
            st = new StringTokenizer(input);
            while(st.hasMoreTokens())
            {
                int number = Integer.parseInt(st.nextToken());
                ban[number]=true;
            }
        }
        // 1. want가 100 그대로일 경우
        if(want == 100){
            System.out.println(0);
            return;
        }
        // 2. 다 고장나서 + - 로만 이동해야 할 경우
        if(banCnt == 10){
            System.out.println(Math.abs(want - 100));
            return;
        }
        // 3. 1개도 고장 안났을 경우
        int result = Integer.MAX_VALUE;
        if(banCnt == 0)
        {
            result = Integer.toString(want).length();
        }
        // 4. 시작하자... upper 부터
        int upper = 0;
        for(upper = want; upper <= 999999;upper++)
        {
            String upperStr = Integer.toString(upper);
            boolean flag = true;
            for(int j = 0; j < upperStr.length(); j++)
            {
                if(ban[upperStr.charAt(j)-'0'] == true)
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                break;
            }
        }
        // 4-1. lower
        int lower = 0;
        for(lower = want; lower >= 0; lower--)
        {
            String lowerStr = Integer.toString(lower);
            boolean flag = true;
            for(int j = 0; j < lowerStr.length(); j++)
            {
                if(ban[lowerStr.charAt(j)-'0'] == true)
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                break;
            }
        }
        if(lower < 0)
        {
            lower = 999999;
        }
        int upperCnt = Math.abs(upper - want) + Integer.toString(upper).length();
        int lowerCnt = Math.abs(want - lower) + Integer.toString(lower).length();
        result = Math.min(Math.abs(want-100), Math.min(result, Math.min(upperCnt, lowerCnt)));
        System.out.println(result);
    }
}
