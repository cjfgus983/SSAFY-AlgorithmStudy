import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] T;
	static int[] P;
	static int max = -1;

	static void recur(int day, int price) {
		if (day > N) {
			return;
		}
		if (day == N) {
			max = Math.max(price, max);
			return;
		}

		recur(day + 1, price);
		recur(day + T[day], price + P[day]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0);
		System.out.println(max);
	}
}
