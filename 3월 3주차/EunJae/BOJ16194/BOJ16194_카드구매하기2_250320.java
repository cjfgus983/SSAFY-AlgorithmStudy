package march3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16194_카드구매하기2_250320{
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static int[] card;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		card = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			card[i]= Integer.parseInt(st.nextToken());
			
		}
		
		for(int i=1;i<=N/2;i++) {
			for(int j=i;j+i<=N;j++) {
				card[i+j] = Math.min(card[i+j], card[i]+card[j]); 
			}
		}
		
		System.out.println(card[N]);
	}
}
