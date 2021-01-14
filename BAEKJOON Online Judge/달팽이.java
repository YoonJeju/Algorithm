import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 달팽이 {
	static int N,R,C,Target;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Target = Integer.parseInt(br.readLine());
		
		R = C = N/2 +1;
		
		//배열의 시작을 0이 아닌 1로 맞추고 시작하자.
		map = new int[N+1][N+1];
		
		//달팽이의 초기 이동값은 1칸. 2step 마다 1씩 증가해야한다.
		int step = 1;
		int count =1;
		
		while(true) {
			//상단 이동
			for(int i=0; i<step; i++) {
				map[R][C] = count++;
				R--;
			}
			if(count -1 ==N*N) break;
			
			//우측 이동
			for(int i=0; i<step; i++) {
				map[R][C] = count++;
				C++;
			}
			//2번 이동 했으니 step 이 증가해야함.
			step++;
			
			//하단 이동
			for(int i=0; i<step; i++) {
				map[R][C] = count++;
				R++;
			}
			//좌측 이동
			for(int i=0; i<step; i++) {
				map[R][C] = count++;
				C--;
			}
			step++;
		}
		int resR=0,resC=0;
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				System.out.print(map[r][c] + " ");
				if(map[r][c] ==Target) {
					resR = r;
					resC = c;
				}
			}
			System.out.println();
		}
		System.out.println(resR + " " + resC);
		
	}

}
