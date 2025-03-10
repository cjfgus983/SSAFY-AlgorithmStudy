import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
*
* add
* 1 << x : 0000..00001 을 왼쪽으로 x만큼 쉬프트함 * 2하는 효과
* |= : or 연산
* remove
* &= : and 연산
* ~ : not 연산
*** 쉬프트해서 not 하면 해당 부분만 0이고 나머지는 1이 될거야
* 그렇다면 원본에서 삭제하고 싶은 비트는 0으로 없어지겠지
*
* check
* 1. 쉬프트 연산 하고
* 2. 원본 set과 비교
* 3. 해당 값이 1 인지 0 인지로 확인
*
* toggle
* ^= xor 연산 - 서로 다른 값이어야 1임
* 원본에 1 이 있으면 같은 1이 들어와서 0 이 되어 없어질거고
* 원본에 0 이 있으면 다른 1이 들어오서 1 이 될 것임
*
* all
* 1. 21만큼 쉬프트하고
* 2. 그거를 -1 해버리면 0111111.....111 이 될 것
*
* empty 그냥 =0 으로 초기화
*
*
* */

public class BOJ_11723_집합_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int commandCnt = Integer.parseInt(br.readLine()); // 명령어 수 입력
        int set = 0; // 비어있는 공집합 초기화

        while (commandCnt-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String oper = st.nextToken(); // 명령어 읽기
            int x;

            switch (oper) {
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set |= (1 << x);
                    break;

                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    set &= ~(1 << x);
                    break;

                case "check":
                    x = Integer.parseInt(st.nextToken());
                    if ((set & (1 << x)) != 0) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;

                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    set ^= (1 << x);
                    break;

                case "all":
                    set = (1 << 21) - 1; // 1~20 비트를 모두 켜기
                    break;

                case "empty":
                    set = 0; // 공집합으로 초기화
                    break;
            }
        }

        // 전체 출력 한 번에 출력
        System.out.print(sb.toString());
    }
}
