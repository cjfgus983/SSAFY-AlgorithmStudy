import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* 000....1111 이런 배열을 하나 두고 1인 부분에 해당하는 재료만 선택하는 코드
* 1. 1의 개수는 1부터 모든 재료의 개수만큼 선택할 수 있도록 반복문을 돌린다.
*
* 2. 1의 개수가 정해졌다면 해당 배열에 대해 전체 순열을 구한다.
* 3. 전체 순열을 구했다면 해당 순열의 1에 해당하는 재료를 모두 더해준다.
* 4. limit보다 작다면 result값을 갱신해서 결과를 구한다.
*
* */

public class SWEA_5215_햄버거다이어트_NextPermutation_김철현 {

    static int[] tastes; // 재료의 맛 점수
    static int[] kcals; // 재료의 칼로리
    static int ingredientCnt; // 재료 개수
    static int limitKcal; // 제한 칼로리
    static int result; // 결과값

    // 다음 순열 생성 함수
    static boolean nextPermutation(int[] select) {
        int i = select.length - 1;
        while (i > 0 && select[i - 1] >= select[i]) {
            i--;
        }
        if (i == 0) return false; // 다음 순열이 없다는 것

        // i-1 과 교환할 조금 더 큰 j 찾기
        int j = select.length - 1;
        while (select[i - 1] >= select[j]) {
            j--;
        }

        // i-1 / j 교환
        int temp = select[i - 1];
        select[i - 1] = select[j];
        select[j] = temp;

        // i 부터 끝 까지 오름차순으로 변환
        j = select.length - 1;
        while (i < j) {
            temp = select[i];
            select[i] = select[j];
            select[j] = temp;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        for (int testCase = 1; testCase <= T; testCase++) {
            // 초기화
            result = 0;

            // 입력
            st = new StringTokenizer(br.readLine().trim());
            ingredientCnt = Integer.parseInt(st.nextToken());
            limitKcal = Integer.parseInt(st.nextToken());

            tastes = new int[ingredientCnt];
            kcals = new int[ingredientCnt];

            for (int i = 0; i < ingredientCnt; i++) {
                st = new StringTokenizer(br.readLine().trim());
                tastes[i] = Integer.parseInt(st.nextToken());
                kcals[i] = Integer.parseInt(st.nextToken());
            }

            // 1개만 고를래 ? 2개 고를래? 아니면 다 고를래?
            for (int selectCnt = 1; selectCnt <= ingredientCnt; selectCnt++) {
                int[] select = new int[ingredientCnt];

                // selectCnt개를 만큼 1로 둠
                for (int i = ingredientCnt - selectCnt; i < ingredientCnt; i++) {
                    select[i] = 1;
                }

                // 모든 순열 확인
                do {
                    int sumTaste = 0;
                    int sumKcal = 0;

                    // 현재 순열에 따라 1인 부분에 한해서 맛 점수와 칼로리 합 계산
                    for (int i = 0; i < ingredientCnt; i++) {
                        if (select[i] == 1) {
                            sumTaste += tastes[i];
                            sumKcal += kcals[i];
                        }
                    }
                    // 제한 칼로리 이하인 경우, result 갱신
                    if (sumKcal <= limitKcal) {
                        result = Math.max(result, sumTaste);
                    }

                } while (nextPermutation(select));
            }

            // 출력
            sb.append("#").append(testCase).append(" ").append(result).append('\n');
        }

        // 출력
        System.out.println(sb.toString());
    }
}
