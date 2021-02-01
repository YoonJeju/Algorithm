import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {
	static int N, R, C, Result;
	static int dr[][] = { 
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 좌
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, // 하
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 우
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } // 상
	};

	static int dc[][] = { 
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, 
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, 
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } };
	static double percent[] = { 0.01, 0.01, 0.02, 0.02, 0.05, 0.07, 0.07, 0.1, 0.1, 0 };
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 중앙 값 설정.
		R = C = N / 2 + 1;
		int step = 1;
		while (true) {
			// 좌측 이동.
			for (int i = 0; i < step; i++) {
				C--;
				Tornado(R, C, 0);
				if (R == 1 && C == 1)
					break;
			}
			if (R == 1 && C == 1)
				break;

			// 아래쪽 이동
			for (int i = 0; i < step; i++) {
				R++;
				Tornado(R, C, 1);
			}
			step++;
			// 우측 이동
			for (int i = 0; i < step; i++) {
				C++;
				Tornado(R, C, 2);
			}

			// 위쪽 이동
			for (int i = 0; i < step; i++) {
				R--;
				Tornado(R, C, 3);
			}
			step++;
		}
		System.out.println(Result);
	}

	private static void Tornado(int r, int c, int dir) {
		int totalSand = map[r][c];
		int moveSand = 0;
		for (int d = 0; d < 10; d++) {
			int nr = r + dr[dir][d];
			int nc = c + dc[dir][d];

			moveSand = (int) (map[r][c] * percent[d]);

			// 움직인 모래 양은 전체 모래양에서 빼준다.
			totalSand -= moveSand;

			// 격자를 벗어난 경우
			if (nr < 1 || nc < 1 || nr > N || nc > N) {
				//알파가 벗어나면 total
				//나머지 경우엔 move !!!!!
				if(d == 9) 
					Result+= totalSand;
				else 
					Result += moveSand;
				
				continue;
			}
			// 알파 자리에 들어가는 모래.
			if (d == 9) 
				map[nr][nc] += totalSand;	//알파자리 이동 하는 경우
			else 
				map[nr][nc] += moveSand; // 얹어지는 경우
		}
		// 태풍 도착 지점 0으로 정리.
		map[r][c] = 0;
	}
}
