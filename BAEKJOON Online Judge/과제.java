import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 과제 {
	static int N,result;
	static int[] save;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		save = new int[1001];
		PriorityQueue<Hw> Woong = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Woong.offer(new Hw(d, w));
		}
		//시작 날짜와 점수를 모두 입력받은후 높은 점수기준으로 내림차순 정렬을 한다.
		while (!Woong.isEmpty()) {
			Hw now = Woong.poll();
			
			//해당 날에 과제가 가능하면 바로 가장 높은 점수먼저 저장해두고 다음 과제로 넘어간다.
			for(int i=now.date; i>0; i--) {
				if(save[i] ==0) {
					save[i] = now.score;
					break;
				}
				//이미 선점된 과제가 있다면 하나씩 내려가면서 채워나가기.
				else {
					int idx = i -1;
					while(idx!=0) {
						if(save[idx]==0) {
							save[idx] = now.score;
							break;
						}
						idx--;
					}
					break;
				}
			}
		}
		
		for(int i=1; i<=1000; i++) {
			result+=save[i];
		}
		System.out.println(result);
	}

	static class Hw implements Comparable<Hw> {
		int date;
		int score;

		public Hw(int date, int score) {
			this.date = date;
			this.score = score;
		}

		public int compareTo(Hw o) {
			return this.score <= o.score ? 1 : -1;
		}
	}
}
