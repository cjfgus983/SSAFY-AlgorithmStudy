package BOJ2309;
/*
20
7
23
19
10
15
25
8
13
난쟁이 키의 합이 100이고
7개의 난쟁이를 찾아 오름차순으로 배열
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int sum = 0;
        for(int i=0;i<9;i++)
        {
            int n = Integer.parseInt(br.readLine()); // 입력 받고
            arr.add(n);
            sum += n;
        }
        // 2중 포문으로
        boolean flag = true;
        for(int i=0;i<9;i++)
        {
            if(flag == false)
            {
                break;
            }
            for(int j=i+1;j<9;j++)
            {
                int x = arr.get(i);
                int y = arr.get(j);
                if(sum - x - y == 100)
                {
                    flag = false;
                    arr.remove(j);
                    arr.remove(i);
                    break;
                }
            }
        }
        Collections.sort(arr);
        for(int a : arr)
        {
            System.out.println(a);
        }
    }
}
