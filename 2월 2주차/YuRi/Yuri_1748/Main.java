
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int num = Integer.parseInt(str);
		int sum=0;
		
		for(int i =str.length();i>1;i--) {
			
			int a = num-((int)Math.pow(10,i-1)-1);
			num-=a;
			sum+= a*i;
		}
		
		sum+=num;
		
		System.out.println(sum);
	}
}
