package BOJ1037;

import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 1 과 N 이 아닌 약수의 개수와 약수들이 입력으로 주어짐
        int cnt = Integer.parseInt(br.readLine());
        // 1. cnt가 1일 땐 제곱하면 될거같고
        // 2. cnt가 2 이상일 땐 가장 큰 약수 * 가장 작은 약수 하면 될 거 같음
        String input = br.readLine();
        st = new StringTokenizer(input, " ");
        ArrayList<Integer> arr = new ArrayList<>();
        while(st.hasMoreTokens()) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        // case 1
        if(arr.size() == 1)
        {
            result = arr.get(0) * arr.get(0);
        }

        // case 2
        Collections.sort(arr);
        result = arr.get(0) * arr.getLast();

        // 출력
        System.out.println(result);
    }
}
