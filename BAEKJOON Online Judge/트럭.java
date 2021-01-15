import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트럭 {
	static int N, W, L, Time;
	static int[] truck;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 트럭 수
		W = Integer.parseInt(st.nextToken()); // 다리 길이
		L = Integer.parseInt(st.nextToken()); // 하중

		truck = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		goTruck();
		System.out.println(Time);
	}

	private static void goTruck() {
		int curWeight = 0; // 현재 무게.
		int carNum = 1; // 현재 차 번호
		int Pass = 0; // 다리를 건넌 차의 대 수.
		int[] truckTime = new int[N + 1];

		while (Pass!=N) {
			Time++;
			// 다리에 차량이 있는 상황이라면
			// 1번 차량부터 현재 진행중인 차량 번호까지 반복문을 돌고
			// 트럭 시간이 돌아가고 있는 차량은 시간을 감소
			
			//현재 이동중인 차량을 체크하고 통과한 경우 Pass처리해야함.
			for (int i = 1; i <= N; i++) {
				if (truckTime[i] > 0) {
					truckTime[i]--;
					// 트럭이 도착하면
					if (truckTime[i] == 0) {
						curWeight -= truck[i];
						Pass++;
					}
				}
			}
			//무게를 초과하지 않는 조건이라면 다음 차량 진행 가능.
			if (carNum <=N && curWeight + truck[carNum] <= L) {
				truckTime[carNum] = W;
				curWeight += truck[carNum++];
			}
		}
	}
}
