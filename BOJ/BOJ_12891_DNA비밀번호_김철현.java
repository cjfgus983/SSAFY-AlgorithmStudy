import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* 슬라이딩 윈도우
* startidx endidx를 두고 각각을 1씩 증가시키며 현재 각 유효 dna 개수를 카운트한다
* endidx가 strlen보다 작을동안 반복하며
* startidx endidx를 각각 1씩 증가시키며 반복
* startidx - 1 의 원소가 유효 dna 일때는 nowCnt 에서 빼주고
* endidx의 원소가 유효 dna 일 때는 nowCnt++ 를 해준다.
*
* */

public class BOJ_12891_DNA비밀번호_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        int strlen = Integer.parseInt(st.nextToken());
        int passlen = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        st = new StringTokenizer(br.readLine().trim());
        // 아래 만큼 각 dna가 필요하다
        int Acnt = Integer.parseInt(st.nextToken());
        int Ccnt = Integer.parseInt(st.nextToken());
        int Gcnt = Integer.parseInt(st.nextToken());
        int Tcnt = Integer.parseInt(st.nextToken());
        // 현재 dna의 개수
        int nowAcnt = 0;
        int nowCcnt = 0;
        int nowGcnt = 0;
        int nowTcnt = 0;
        // 슬라이딩 윈도우
        int startIdx = 0;
        int endIdx = passlen-1;
        // 먼저 현재 윈도우에서의 각 개수를 보기
        for(int idx = startIdx; idx <= endIdx; idx++) {
            if(str.charAt(idx) == 'A') {
                nowAcnt++;
            }
            else if(str.charAt(idx) == 'C') {
                nowCcnt++;
            }
            else if(str.charAt(idx) == 'G') {
                nowGcnt++;
            }
            else if(str.charAt(idx) == 'T') {
                nowTcnt++;
            }
        }
        int result = 0;
        while(endIdx < strlen)
        {
            // 1. 현재 조건 만족하는지 확인
            if(nowAcnt >= Acnt && nowCcnt >= Ccnt && nowGcnt >= Gcnt && nowTcnt >= Tcnt) {
                result++;
            }
            // 2. startidx 와 endidx를 1씩 증가
            startIdx++; endIdx++;
            // 3. 이전 startidx의 원소는 카운트에서 삭제
            if(str.charAt(startIdx - 1) == 'A'){
                nowAcnt--;
            }
            else if(str.charAt(startIdx - 1) == 'C'){
                nowCcnt--;
            }
            else if(str.charAt(startIdx - 1) == 'G'){
                nowGcnt--;
            }
            else if(str.charAt(startIdx - 1) == 'T'){
                nowTcnt--;
            }
            // 4. endidx원소 카운트 증가
            if (endIdx < strlen) {
                if (str.charAt(endIdx) == 'A') {
                    nowAcnt++;
                } else if (str.charAt(endIdx) == 'C') {
                    nowCcnt++;
                } else if (str.charAt(endIdx) == 'G') {
                    nowGcnt++;
                } else if (str.charAt(endIdx) == 'T') {
                    nowTcnt++;
                }
            }
        }
        System.out.println(result);
    }
}