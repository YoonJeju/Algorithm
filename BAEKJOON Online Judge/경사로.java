import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {
	static int N, X, result;
	static boolean[] passR, passC, used;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		passR = new boolean[N]; // 가로 활주로에 대한 가능 여부
		passC = new boolean[N]; // 세로 활주로에 대한 가능 여부
		// 모든 활주로는 모두 사용가능하다는 설정으로 만들어두기.
		Arrays.fill(passR, true);
		Arrays.fill(passC, true);

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		CheckRowRoad();
		CheckColRoad();

		for (int i = 0; i < N; i++) {
			if (passR[i]) 
				result++;
			if (passC[i]) 
				result++;
		}
		System.out.println(result);
	}

	private static void CheckColRoad() {
		// 아래로
		for (int c = 0; c < N; c++) {
			int road = 1;
			used = new boolean[N]; // 좌우 점검에서 사용했으니 다시 초기화.
			for (int r = 0; r < N - 1; r++) {
				// 인접한 칸의 값 차이가 2보다 크면 해당 라인은 활주로 생성 불가능.
				if (Math.abs(map[r][c] - map[r + 1][c]) >= 2) {
					passC[c] = false;
					break;
				}
				// 길의 길이가 같으면 길이 추가해주고 다음.
				if (map[r][c] == map[r + 1][c]) {
					road++;
					continue;
				}
				// 값이 내려가는건 아래이동에서 체크해줄 필요가 없음.
				if (map[r][c] > map[r + 1][c]) {
					road = 1;
					continue;
				}
				// 길의 길이가 최소 활주로 길이보다 작은데, 다음갚이 언덕이면 불가능.
				if (road < X && map[r][c] < map[r + 1][c]) {
					passC[c] = false;
					break;
				}

				// 길의 길이 충분하고, 다음값이 하나 높으면 활주고 가능.
				if (road >= X && map[r + 1][c] == map[r][c] + 1) {
					// 이용한 활주로는 사용 처리 해두기.
					for (int i = r; i > r - X; i--)
						used[i] = true;
					road = 1;
				}
			}
			// 위로
			road = 1;
			out: for (int r = N - 1; r > 0; r--) {
				if (Math.abs(map[r][c] - map[r - 1][c]) >= 2) {
					passC[c] = false;
					break;
				}
				// 길의 길이가 최소 활주로 길이보다 작은데, 다음갚이 언덕이면 불가능.
				if (road < X && map[r][c] < map[r - 1][c]) {
					passC[c] = false;
					break;
				}
				// 값이 내려가는건 위로이동에서 체크해줄 필요가 없음.
				if (map[r][c] > map[r - 1][c]) {
					road = 1;
					continue;
				}

				// 길의 길이가 같으면 길이 추가해주고 다음.
				if (map[r][c] == map[r - 1][c]) {
					road++;
					continue;
				}
				// 길의 길이 충분하고, 다음값이 하나 높으면 활주고 가능.
				if (road >= X && map[r - 1][c] == map[r][c] + 1) {
					// 활주로를 만들어야하는데 이미 사용중이면. 안됨. 기각!
					for (int i = r; i < r + X; i++) {
						if (used[i]) {
							passC[c] = false;
							break out;
						}
					}
					road = 1;
				}
			}
		}
	}

	private static void CheckRowRoad() {
		for (int r = 0; r < N; r++) {
			// 오른쪽
			int road = 1;
			used = new boolean[N];
			for (int c = 0; c < N - 1; c++) {
				// 인접한 칸의 값 차이가 2보다 크면 해당 라인은 활주로 생성 불가능.
				if (Math.abs(map[r][c] - map[r][c + 1]) >= 2) {
					passR[r] = false;
					break;
				}
				// 활주로의 길이가 같으면 길이 추가해주고 다음.
				if (map[r][c] == map[r][c + 1]) {
					road++;
					continue;
				}
				// 값이 내려가는건 우측이동에서 체크해줄 필요가 없음.
				if (map[r][c] > map[r][c + 1]) {
					road = 1;
					continue;
				}

				// 활주로의 길이가 최소 활주로 길이보다 작은데, 다음갚이 언덕이면 불가능.
				if (road < X && map[r][c] < map[r][c + 1]) {
					passR[r] = false;
					break;
				}
				// 길의 길이 충분하고, 다음값이 하나 높으면 활주고 가능.
				if (road >= X && map[r][c + 1] == map[r][c] + 1) {
					// 이용한 활주로는 사용 처리 해두기.
					for (int i = c; i > c - X; i--)
						used[i] = true;
					road = 1;
				}

			}
			// 왼쪽
			road = 1;
			out: for (int c = N - 1; c > 0; c--) {
				// 인접한 칸의 값 차이가 2보다 크면 해당 라인은 활주로 생성 불가능.
				if (Math.abs(map[r][c] - map[r][c - 1]) >= 2) {
					passR[r] = false;
					break;
				}
				// 길의 길이가 같으면 길이 추가해주고 다음.
				if (map[r][c] == map[r][c - 1]) {
					road++;
					continue;
				}
				// 값이 내려가는건 좌측이동에서 체크해줄 필요가 없음.
				if (map[r][c] > map[r][c - 1]) {
					road = 1;
					continue;
				}

				// 길의 길이가 최소 활주로 길이보다 작은데, 다음갚이 언덕이면 불가능.
				if (road < X && map[r][c] < map[r][c - 1]) {
					passR[r] = false;
					break;
				}
				// 길의 길이 충분하고, 다음값이 하나 높으면 활주고 가능.
				if (road >= X && map[r][c - 1] == map[r][c] + 1) {
					// 활주로를 만들어야하는데 이미 사용중이면. 안됨.기각!
					for (int i = c; i < c + X; i++) {
						if (used[i]) {
							passR[r] = false;
							break out;
						}
					}
					road = 1;
				}
			}
		}
	}
}