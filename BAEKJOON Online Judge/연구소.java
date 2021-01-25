import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_0125 {
	static int N, M, vCnt, result;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int[][] map;
	static Position virus[];
	static Queue<Position> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		// 바이러스의 개수는 2<= V <=10
		virus = new Position[10];
		queue = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					virus[vCnt++] = new Position(r, c);
				}
			}
		}
		makeWall(0,0,0,map);
		
		System.out.println(result);
	}

	private static void makeWall(int r, int c, int wall, int[][] map) {
		if (wall == 3) {
			bfs(map);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 해당 지점이 빈공간인 경우에만
				// 벽 처리 해주고 wall 카운트 증가
				if (map[i][j] == 0) {
					map[i][j] = 1;
					makeWall(i, j, wall + 1, map);
					map[i][j] = 0;
				}
			} // end for c
		}
	}

	private static void bfs(int[][] map) {
		int[][] copyMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		// 바이러스 주입
		for (int i = 0; i < vCnt; i++) {
			queue.offer(virus[i]);
		}
		// 퍼트리기 시작
		while (!queue.isEmpty()) {
			Position n = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (copyMap[nr][nc] == 0) {
					copyMap[nr][nc] = 2;
					queue.offer(new Position(nr, nc));
				}
			}
		}	//end for while
		int safe =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyMap[i][j] == 0)
					safe++;
			}
		}
		result = Math.max(result, safe);
	}
	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
