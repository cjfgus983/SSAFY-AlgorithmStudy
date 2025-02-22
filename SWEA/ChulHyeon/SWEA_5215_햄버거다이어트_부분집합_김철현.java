import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *
 * 맛 점수 / 칼로리
 *
 * 부분집합
 * 1. 재귀 호출
 * 2. 모든 원소를 돌았을 때 기저조건
 * 3. for문으로 전체 원소 탐색하며 visit에 체크된 원소들은 더해서 result값과 비교
 *
 *
 * */

public class SWEA_5215_햄버거다이어트_부분집합_김철현 {

    static int[] tastes; // 인덱스 재료의 맛
    static int[] kcals; // 인덱스 재료의 칼로리
    static boolean[] visit; // 재료를 넣었는지 확인

    static int ingredientCnt; // 재료 개수
    static int limitKcal; // 제한 칼로리

    static int result; // 결과값
    // 현재 원소 탐색 nowidx, 칼로리합 sumKcal, 맛 합 sumTaste
    static void subSet(int nowidx)
    {
        // 2. 기저조건 - 모든 원소를 탐색했다면 종료
        if(nowidx == ingredientCnt)
        {
            int sumKcal=0;
            int sumTaste=0;
            for(int i = 0; i < kcals.length; i++)//3. for문으로 전체 원소 탐색하며 visit에 체크된 원소들은 더해서 result값과 비교
            {
                if(visit[i])
                {
                    sumKcal += kcals[i];
                    sumTaste += tastes[i];
                }
            }
            if(sumKcal > limitKcal){
                return;
            }
            else
            {
                result = Math.max(result, sumTaste);
                return;
            }
        }

        visit[nowidx] = true;
        subSet(nowidx + 1);
        visit[nowidx] = false;
        subSet(nowidx + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for(int testCase = 1; testCase <= T; testCase++)
        {
            // 초기화
            result = 0;
            // 입력
            st = new StringTokenizer(br.readLine().trim());
            ingredientCnt = Integer.parseInt(st.nextToken());
            limitKcal = Integer.parseInt(st.nextToken());

            tastes = new int[ingredientCnt];
            kcals = new int[ingredientCnt];
            visit = new boolean[ingredientCnt];

            for(int input = 0; input < ingredientCnt; input++)
            {
                st = new StringTokenizer(br.readLine().trim());
                tastes[input] = Integer.parseInt(st.nextToken());
                kcals[input] = Integer.parseInt(st.nextToken());
            }

            // 1. 재귀 호출
            subSet(0);
            sb.append("#").append(testCase).append(" ").append(result).append('\n');
        }
        System.out.println(sb.toString());
    }
}