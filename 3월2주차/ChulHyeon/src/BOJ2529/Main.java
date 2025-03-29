package BOJ2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static char[] cmpArr;
    static int[] resultArr;
    static boolean[] visit = new boolean[10];

    static boolean smallFlag = false;
    static boolean bigFlag = false;

    static void dfsSmall(int cnt){
        if(cnt > size)
        {
            if(smallFlag)
                return;
            smallFlag = true;
            for(int a : resultArr)
            {
                System.out.print(a);
            }
            System.out.println();
            return;
        }
        if(smallFlag)
            return;

        int cmpIdx = cnt - 1;

        // 시작 부분 가장 처음 인덱스에 넣기
        if(cnt == 0)
        {
            for(int i = 0; i < 10; i++)
            {
                visit[i] = true;
                resultArr[cnt] = i;
                dfsSmall(cnt + 1);
                visit[i] = false;
            }
            return;
        }
        // 작은 배열 찾기
        for(int i = 0; i < 10; i++)
        {
            if(visit[i])
                continue;
            if((cmpArr[cmpIdx] == '<' && resultArr[cnt - 1] < i) || (cmpArr[cmpIdx] == '>' && resultArr[cnt - 1] > i)) // 현재가 더 커야 한다면
            {
                visit[i] = true;
                resultArr[cnt] = i;
                dfsSmall(cnt + 1);
                visit[i] = false;
            }
        }
    }

    static void dfsBig(int cnt){
        if(cnt > size)
        {
            if(bigFlag)
                return;
            bigFlag = true;
            for(int a : resultArr)
            {
                System.out.print(a);
            }
            System.out.println();
            return;
        }
        if(bigFlag)
            return;

        int cmpIdx = cnt - 1;

        // 시작 부분 가장 처음 인덱스에 넣기
        if(cnt == 0)
        {
            for(int i = 9; i >= 0; i--)
            {
                visit[i] = true;
                resultArr[cnt] = i;
                dfsBig(cnt + 1);
                visit[i] = false;
            }
            return;
        }
        // 큰 배열 찾기
        for(int i = 9; i >= 0; i--)
        {
            if(visit[i])
                continue;
            if((cmpArr[cmpIdx] == '<' && resultArr[cnt - 1] < i) || (cmpArr[cmpIdx] == '>' && resultArr[cnt - 1] > i)) // 현재가 더 커야 한다면
            {
                visit[i] = true;
                resultArr[cnt] = i;
                dfsBig(cnt + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 입력
        size = Integer.parseInt(br.readLine());
        cmpArr = new char[size];
        resultArr = new int[size + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++) {
            cmpArr[i] = st.nextToken().charAt(0);
        }

        dfsBig(0);
        // resultArr 초기화
        dfsSmall(0);

    }
}
