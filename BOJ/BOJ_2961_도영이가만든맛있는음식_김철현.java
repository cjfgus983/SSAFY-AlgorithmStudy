import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* 부분조합을 재귀로 호출하여 결과값을 Math.min 으로 구하자
* 근데 주의해야 할 점이 있음
* 아무것도 재료를 안넣는건 안됨
* 그래서 파라미터로 nowCnt 를 넣고 이를 재귀할때 마다 +1 or +0 을 하여
* 기저조건에서 nowCnt == 0 이라면 값을 갱신하지 않고 리턴하자.
*
* */

public class BOJ_2961_도영이가만든맛있는음식_김철현 {

    static int ingredientCnt;
    static int[] sour;
    static int[] bit;
    static boolean[] visit;
    static int result = Integer.MAX_VALUE;

    static void subset(int nowidx, int nowCnt){
        if(nowidx == ingredientCnt){
            if(nowCnt == 0) // 재료가 하나도 없으면 리턴
                return;
            int sourScore = 1;
            int bitScore = 0;
            for(int idx = 0; idx < ingredientCnt; idx++){
                if(visit[idx]){
                    sourScore *= sour[idx];
                    bitScore += bit[idx];
                }
            }
            result = Math.min(Math.abs(sourScore - bitScore), result);
            return;
        }

        // 이번 인덱스 넣고 재귀
        visit[nowidx] = true;
        subset(nowidx+1, nowCnt+1);
        // 이번 인덱스 넣지 않고 제귀
        visit[nowidx] = false;
        subset(nowidx + 1, nowCnt);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        ingredientCnt = Integer.parseInt(br.readLine().trim());

        // 초기화
        sour = new int[ingredientCnt];
        bit = new int[ingredientCnt];
        visit = new boolean[ingredientCnt];
        // 입력
        for(int idx = 0; idx < ingredientCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            sour[idx] = Integer.parseInt(st.nextToken());
            bit[idx] = Integer.parseInt(st.nextToken());
        }
        subset(0, 0);
        System.out.println(result);
    }
}
