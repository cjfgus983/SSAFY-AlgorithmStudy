import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static int n;
    static int m;
    static int[] num;
    static StringBuilder ans = new StringBuilder();

    static void solve(String curNum, int curIndex, int size){
        if(size == m){
            ans.append(curNum).append("\n");
            return;
        }

        if(curIndex > n){
            return;
        }

        solve(curNum + num[curIndex] + " ", curIndex, size+1);
        solve(curNum, curIndex+1, size);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        solve("", 1, 0);
        System.out.println(ans);
    }
}