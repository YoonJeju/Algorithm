import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {
	static int N, M, K, R, C, NR, NC,result;
	static int[][] sticker, temp, note;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		note = new int[N][M];
		// 스티커 개수 만큼 반복
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int size = 0;

			sticker = new int[R][C];
			NR = R;
			NC = C;

			// 스티커 입력
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
					if (sticker[r][c] == 1)
						size++;
				}
			}

			oout: for (int d = 0; d < 4; d++) {
				for (int r = 0; r <=N - R; r++) {
					for (int c = 0; c <=M - C; c++) {
						boolean isOk = true;
						out: for (int stR = 0; stR < R; stR++) {
							for (int stC = 0; stC < C; stC++) {
								if (note[r + stR][c + stC] == 1 && sticker[stR][stC] == 1) {
									isOk = false;
									break out;
								}
							}
						}

						if (isOk) {
							for (int stR = 0; stR < R; stR++) {
								for (int stC = 0; stC < C; stC++) {
									if (sticker[stR][stC] == 1) {
										note[r+stR][c+stC] = 1;
									}
								}
							}
							result+=size;
							break oout;
						}
					}
				}

				rotate();

			}

		}
		System.out.println(result);
	}

	private static void rotate() {
		NR = C;
		NC = R;
		temp = new int[NR][NC];
		
		//열 -> 행    행 -> 전체 R크기 - 1 - 현재 r
		for (int r = 0; r < NC; r++) {
			for (int c = 0; c < NR; c++) {
				temp[c][NC - 1 - r] = sticker[r][c];
			}
		}
		sticker = new int[NR][NC];

		for (int r = 0; r < NR; r++) {
			for (int c = 0; c < NC; c++) {
				sticker[r][c] = temp[r][c];
			}
		}

		R = NR;
		C = NC;
	}
}
