import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부먹왕국의차원관문 {
	static int N, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int res = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			int[] kingdom = new int[N];

			st = new StringTokenizer(br.readLine());
			int zeroCnt = 0;
			for (int i = 0; i < N; i++) {
				kingdom[i] = Integer.parseInt(st.nextToken());

				if (kingdom[i] == 0) {
					zeroCnt++;

					if (zeroCnt >= D) {
						res++;
						zeroCnt = 0;
					}
				}
				else zeroCnt =0;
			}
			System.out.println("#" + tc + " " + res);
		}
	}
}