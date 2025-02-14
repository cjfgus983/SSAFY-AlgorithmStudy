import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int n;
    static int m;
    static StringBuilder ans = new StringBuilder();
    
    static void solve(String curNum, int curIndex, int size){
        if(size == m){
            ans.append(curNum).append("\n");
            return;
        }

        if(curIndex > n){
            return;
        }

        solve(curNum + curIndex + " ", curIndex, size+1);
        solve(curNum, curIndex+1, size);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        solve("", 1, 0);
        System.out.println(ans);
    }
}
