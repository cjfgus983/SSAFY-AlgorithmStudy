import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기_김철현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int testCase = 1; testCase <= 10; testCase++) {
            // 첫번째 쓰레기값 버리기
            br.readLine();
            Queue<Integer> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0;idx<8;idx++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }
            int minus = 1;
            while(true){
                int front = queue.poll();
                front = front - minus;
                if(front <= 0 )
                {
                    queue.add(0);
                    break;
                }
                queue.add(front);
                minus = (minus + 1);
                if(minus == 6)
                    minus = 1;

            }
            sb.append("#").append(testCase).append(" ");
            for(int element : queue){
                sb.append(element).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
