import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int n;
    static int m;
    static int[] num;
    static StringBuilder ans = new StringBuilder();

    static void print(){
        for (int i = 0; i < num.length; i++) {
            ans.append(num[i]+1).append(" ");
        }
        ans.append("\n");
    }

    static void check(){
        for (int i = num.length-1; i > 0; i--) {
            if(num[i] == n){
                num[i] = 0;
                num[i-1]++;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[m];

        while(num[0] != n){
            print();
            num[m-1]++;
            check();
        }

        System.out.println(ans);
    }
}
