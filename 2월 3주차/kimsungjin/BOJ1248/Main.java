import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	static int n;
	static char[][] S;
	static int[] ans;
	static int[][] sum;

	static void recur(int index) {
		if (index == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(ans[i] + " ");
			}
			System.exit(0);
		}

		for (int x = -10; x <= 10; x++) {
			ans[index] = x;

			for (int i = 0; i <= index; i++) {
				for (int j = index; j < n; j++) {
					sum[i][j] += x;
				}
			}

			boolean flag = true;
			for (int i = 0; i <= index; i++) {
				for (int j = i; j <= index; j++) {
					if (S[i][j] == '-' && sum[i][j] >= 0 || S[i][j] == '0' && sum[i][j] != 0
							|| S[i][j] == '+' && sum[i][j] <= 0)
						flag = false;
				}
			}

			if (flag)
				recur(index + 1);

			for (int i = 0; i <= index; i++) {
				for (int j = index; j < n; j++) {
					sum[i][j] -= x;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		ans = new int[n];
		S = new char[n][n];
		sum = new int[n][n];

		String line = br.readLine();
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				S[i][j] = line.charAt(index++);
			}
		}

		recur(0);
	}
}
