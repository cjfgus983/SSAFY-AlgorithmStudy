package BOJ1476;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAXVALUE = 15 * 28 * 19;

    static boolean[][] arr = new boolean[3][MAXVALUE + 1];

    public static void check(char type, int value)
    {
        if(type == 'E')
        {
            for(int i = value; i <= MAXVALUE; i = i + 15)
            {
                arr[0][i] = true;
            }
        }
        if(type == 'S')
        {
            for(int i = value; i <= MAXVALUE; i = i + 28)
            {
                arr[1][i] = true;
            }
        }
        if(type == 'M')
        {
            for(int i = value; i <= MAXVALUE; i = i + 19)
            {
                arr[2][i] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        check('E',E);
        check('S',S);
        check('M',M);

        for(int i = 1; i <= MAXVALUE; i++)
        {
            if(arr[0][i]&&arr[1][i]&&arr[2][i])
            {
                System.out.println(i);
                break;
            }
        }
    }
}