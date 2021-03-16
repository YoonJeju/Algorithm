import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 금속막대 {
	static Screw screw[];
	static int[] num;
	static boolean[] sel;
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			screw = new Screw[N];

			for (int i = 0; i < N; i++)
				screw[i] = new Screw();
			num = new int[N];
			sel = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				screw[i].front = Integer.parseInt(st.nextToken());
				screw[i].back = Integer.parseInt(st.nextToken());
			}
			
			System.out.print("#" + tc + " ");
			permutation(0);

		}

	}

	private static void permutation(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				System.out.print(screw[num[i]].front + " " + screw[num[i]].back + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (sel[i])continue;
			if (cnt > 0) {
				if (screw[num[cnt - 1]].back != screw[i].front)
					continue;
			}
			num[cnt] = i;
			sel[i] = true;
			permutation(cnt + 1);
			sel[i] = false;
		}
	}

	static class Screw {
		int front, back;

		public Screw(int front, int back) {
			this.front = front;
			this.back = back;
		}

		public Screw() {
			this.front = 0;
			this.back = 0;
		}
	}
}
