import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어벨트위에로봇 {
	static int N,K,stop,result;
	static Robot belt[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new Robot[N*2+1];
		
		for(int i=0; i<=N*2; i++)
			belt[i] = new Robot();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N*2; i++) {
			belt[i].life = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			result++;
			//1. 벨트 회전
			Rotation();
			//2. 로봇 이동 & 3. 로봇 올리기
			Move();
			if(stop>=K) break;
		}
		System.out.println(result);
	}
	
	private static void Move() {
		for(int i=N-1; i>0; i--) {
			if(!belt[i].robot) continue;
			//다음 자리의 내구도가 있고, 로봇이 없다면
			if(belt[i+1].life>0 && !belt[i+1].robot) {
				belt[i].robot = false;
				belt[i+1].life--;
				belt[i+1].robot = true;
				if(belt[i+1].life==0) stop++;
			}
		}
		
		//3단계 로봇 올리기.
		if(!belt[1].robot && belt[1].life >0) {
			belt[1].robot = true;
			belt[1].life--;
			if(belt[1].life==0) stop++;
		}
		
	}

	private static void Rotation() {
		//0번 지 공간은 임시 저장 공간으로 활용!
		belt[0].life = belt[N*2].life;
		for(int i=N*2; i>1; i--) {
			belt[i].life = belt[i-1].life;
			belt[i].robot = belt[i-1].robot;
		}
		belt[1].life = belt[0].life;
		belt[1].robot = belt[0].robot;
		
		//제일 끝에 있는 로봇 밀어내기
		if(belt[N].robot) belt[N].robot = false;
	}

	static class Robot{
		int life;
		boolean robot;
		
		public Robot() {
			this.life =0;
			this.robot = false;
		}

		public Robot(int life, boolean robot) {
			this.life = life;
			this.robot = robot;
		}
	}
}
