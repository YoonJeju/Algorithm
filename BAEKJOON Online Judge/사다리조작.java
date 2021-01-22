import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작 {
	static int N,M,H,res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//세로선
		M = Integer.parseInt(st.nextToken());	//가로선의 개수.
		H = Integer.parseInt(st.nextToken());	//가로선을 놓을 수 있는높이
		
		int[][] map = new int[H+1][N+1];	//[사다리의 세로 위치][가로 위치]
		res = Integer.MAX_VALUE;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//점선 번호(위에서 부터 1~)
			int b = Integer.parseInt(st.nextToken());	//b번 세로선과 b+1번 세로선 연결.
			
			map[a][b] = 1;
		}
		
		if(H==0) {
			System.out.println(0);
			System.exit(0);
		}
		
		addLadder(map,1,0);	//높이 가로 사다리
		System.out.println(res > 3 ? "-1" : res);
	}
	
	private static void addLadder(int[][] map,int nowC, int ldr) {
		if(ldr > 3 || nowC==N) return;	//4부터는 더이상 탐색 할 필요 없음, 모든 세로선 점검 도 탐색 끝
		
		if(checkLadder(map)) {
			res = Math.min(ldr, res);
			return;
		}
		//r = 세로선 번호.
		for(int r=1; r<=H; r++) {
			//사다리는 자신을 포함 좌우 모두 선이 연결되어있지 않아야 연결이 가능하다.
			if(map[r][nowC-1] ==0 && map[r][nowC] ==0 && map[r][nowC+1]==0) {
				//사다리 연결
				map[r][nowC] = 1;
				addLadder(map,nowC,ldr+1);
				map[r][nowC] = 0;
			}
		}
		//위 반복문에서 모든 세로선에 대해 nowC(가로선) 번호 를 점검했으므로 아래 층 가로선을 모두 체크해야함.
		addLadder(map,nowC+1,ldr);
		
		
	}

	private static boolean checkLadder(int[][] map) {
		//1번 선부터 N-1 번까지 처리한 CNT 가 N-1 개면 남은 사다리는 확인하지 않아도 됨.
		for(int ldr=1; ldr<N; ldr++) {
			int ldrNum = ldr;	//움직일 사다리 위치.
			int height =1;
			
			//도착지점은 H+1 위치.
			while(true) {
				if(map[height][ldrNum]==1) { //세로선의 범위를 넘지 않고, 오른쪽 길이 있다면
					ldrNum++;
				}
				else if(map[height][ldrNum-1]==1) {	//왼쪽길
					ldrNum--;
				}
				//좌우 길이 없으면 종료조건을 점검하면서 아래로 한칸 이동.
				if(++height == H+1){
					break;
				}
			}
			//하나라도 도착지점이 같지 않다면 False
			if(ldr!=ldrNum)
				return false;
		}
		return true;
	}
}
