package BOJ15649;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int n;
    static int m;
    static boolean[] visit;
    static int[] arr;

    static void dfs(int cnt)
    {
        if(cnt == m)
        {
            for(int i : arr)
            {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        for(int i=1;i<=n;i++)
        {
            if(visit[i] == true)
                continue;
            visit[i] = true;
            arr[cnt] = i;
            dfs(cnt + 1);
            visit[i] =false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        visit = new boolean[n + 1];
        arr = new int[m];

        dfs(0);
    }
}
