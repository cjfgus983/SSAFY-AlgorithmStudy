import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_VALUE = 1_000_001;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int N = Integer.parseInt(br.readLine());
		
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		//StringTokenizer st = new StringTokenizer(br.readLine());
		
		long[] arr = new long[MAX_VALUE];
		long[] arr_sum = new long[MAX_VALUE];
		Arrays.fill(arr, 1);

		for(int i =2;i<MAX_VALUE; i++) {
			for(int j =1;i*j<MAX_VALUE;j++) {
				arr[i*j] +=i;
			}
		}
		
		for(int i = 1;i<MAX_VALUE;i++) {
			arr_sum[i] = arr_sum[i-1] + arr[i];
		}
		
		
		for(int i=0; i<N;i++) {
			int a = Integer.parseInt(br.readLine());
			
			sb.append(arr_sum[a]).append("\n");
		}
		
		
		System.out.println(sb);
	}
}