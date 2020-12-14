import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조성 {
	static int N, K, high, res;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			res = high = 0;
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (high < map[r][c])
						high = map[r][c];
				}
			}
			findTop();

			System.out.println("#" + tc + " " + res);
		}
	}

	private static void findTop() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == high) {
					makeLoad(r, c, high, 1, false);
				}
			}
		}
	}

	private static void makeLoad(int r, int c, int height, int move, boolean cut) {
		if (move > res)
			res = move;

		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;

			if (visited[nr][nc])
				continue;

			if (height > map[nr][nc]) {
				makeLoad(nr, nc, map[nr][nc], move + 1, cut);
			} else if (!cut && map[nr][nc] - K < height) {
				makeLoad(nr, nc, height - 1, move + 1, true);
			}
		}
		visited[r][c] = false;
	}
}
