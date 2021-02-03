import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점심식사시간 {
	static int N, T, res;
	static int[] manSelect;
	static int[][] map;
	static List<Position> man, exit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N + 1][N + 1];
			for (int i = 0; i < 10; i++)
				man = new ArrayList<>();
			for (int i = 0; i < 2; i++)
				exit = new ArrayList<>();

			for (int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1)
						man.add(new Position(r, c)); // 사람의 위치 좌표를 man 리스트에 저장
					else if (map[r][c] > 1)
						exit.add(new Position(r, c)); // 비상구의 위치 좌표를 exit 리스트에 저장

				}
			}
			res = Integer.MAX_VALUE;
			manSelect = new int[man.size()]; // 각 사람들의 비상구 번호 선택 배열.
			selectEXIT(0);
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void selectEXIT(int cnt) {
		if (cnt == man.size()) {
			goExit();
			return;
		}
		for (int i = 0; i < 2; i++) {
			manSelect[cnt] = i;
			selectEXIT(cnt + 1);
		}
	}

	private static void goExit() {
		int time = 0;
		int escape = 0;
		int stair[] = new int[2];
		Queue<Status> queue = new LinkedList<>();

		int arriveTime = 0;
		for (int i = 0; i < man.size(); i++) {
			int er = exit.get(manSelect[i]).r;
			int ec = exit.get(manSelect[i]).c;
			// 도착시간을 저장 할 땐 대기 시간을 고려해 +1 로 저장!
			arriveTime = Math.abs(man.get(i).r - er) + Math.abs(man.get(i).c - ec) + 1;
			queue.offer(new Status(arriveTime, manSelect[i], arriveTime + map[er][ec], false));
		}

		while (escape!=man.size()) {
			time++;
			int size = queue.size();
			int stairCnt[] = new int[2];	//계단 사용이 끝난 수를 처리하기 위한 용도.
			stairCnt[0] = stairCnt[1] = 0;
			
			for (int i = 0; i < size; i++) {
				Status now = queue.poll();

				// 계단을 내려갈 수 있는 시간이 되면!
				if (now.startTime == time) {
					// 이미 계단을 이동 중인 녀석이면
					if (now.moving) {
						// 도착 조건 체크 후 아닐때 다시 +1 해서 오퍼.
						if (now.startTime + 1 == now.endTime) {
							escape++;
							stairCnt[now.exitNum]++;	//여기서 미리 감소를 시키면 아래에서 1초빠르게 계단 진입이 가능해진다.
							if(escape==man.size()) time++;
						}
						else
							queue.offer(new Status(now.startTime + 1, now.exitNum, now.endTime, true));

					}
					// 계단 입구 대기 였다면
					else {
						if (stair[now.exitNum] < 3) {
							stair[now.exitNum]++;
							queue.offer(new Status(now.startTime + 1, now.exitNum, now.endTime, true));
						}
						// 계단이 사용 불가이면, 출발시간과 도착시간이 똑같이 지연된다.
						else {
							queue.offer(new Status(now.startTime + 1, now.exitNum, now.endTime + 1, false));
						}
					}
				}
				// 아직 비상구에 도착하지 않은 경우
				else if (now.startTime > time)
					queue.offer(new Status(now.startTime, now.exitNum, now.endTime, false));
			} // end for for
			stair[0] -= stairCnt[0];
			stair[1] -= stairCnt[1];
		} // end for while
		if (res > time)
			res = time;
	}

	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Status {
		int startTime;
		int exitNum;
		int endTime;
		boolean moving;

		public Status(int startTime, int exitNum, int endTime, boolean moving) {
			this.startTime = startTime;
			this.exitNum = exitNum;
			this.endTime = endTime;
			this.moving = moving;
		}
	}
}