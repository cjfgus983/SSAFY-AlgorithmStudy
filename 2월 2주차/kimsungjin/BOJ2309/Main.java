import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarf = new int[9];

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
            sum += dwarf[i];
        }

        Arrays.sort(dwarf);

        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                if(sum - dwarf[i] -dwarf[j] == 100){
                    for (int k = 0; k < 9; k++) {
                        if(k==i || k==j) continue;
                        else System.out.println(dwarf[k]);
                    }

                    System.exit(0);
                }
            }
        }
    }
}