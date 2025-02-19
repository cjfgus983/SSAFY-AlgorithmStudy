import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.read);
		StringBuilder sb = new StringBuilder();
		
		String now;
		
		while((now = br.readLine())!=null&& !now.isEmpty()) {
			int input = Integer.parseInt(now);
			int dab = 1;
			int now2 = 1;
			while((now2=now2 %input) !=0) {
				now2= now2*10+1;
				dab++;
				
			}
			sb.append(dab).append("\n");
		}
		System.out.println(sb);
	}
}