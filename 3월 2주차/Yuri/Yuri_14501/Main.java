
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<int[]> arr = new ArrayList<>();
	static int Max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr.add(new int[]{a,b});
			
		}
		
		solution(0,0);
		
		System.out.println(Max);
		

	}
	
	public static void solution(int now, int sum) {
		//System.out.println(now+ " " +sum);
		if(now==N) {
			Max = Math.max(Max, sum);
			return;
		}
		
		if(now+arr.get(now)[0]<=N) solution(now+arr.get(now)[0], sum+arr.get(now)[1]);
		solution(now+1, sum);
		
	}

}
