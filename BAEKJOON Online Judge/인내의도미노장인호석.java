import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인내의도미노장인호석 {
	static int N,M,R,res;
	static int[][] map;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static boolean[][] mapState;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//행
		M = Integer.parseInt(st.nextToken());	//열
		R = Integer.parseInt(st.nextToken());	//라운드
		
		map = new int[N+1][M+1];	//도미노 맵.
		mapState = new boolean[N+1][M+1];	//도미노가 넘어간 곳인지 아닌지 체크하기 위한 배열. true = 넘어간 것
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; i++) {
			//공격수 처리
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			
			//시작 도미노가 넘어가 있지 않다면, 넘겨주고 포인트 올려주고 시작.
			if(!mapState[r][c]) {
				mapState[r][c] = true;
				res++;
			}
			attack(r,c,d,map[r][c]-1);	//행, 열, 방향, 도미노길이
			
			//수비수 처리
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			mapState[r][c] = false;
			
		}
		System.out.println(res);
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=M; c++){
				if(c<M)
					System.out.print(mapState[r][c] ? "F " : "S ");
				else
					System.out.print(mapState[r][c] ? "F" : "S");
			}
			System.out.println();
		}
		
	}
	private static void attack(int r, int c, String d, int cnt) {
		Queue<Position> q = new LinkedList<>();
		int dir;
		
		if(d.contains("E")) dir =0;
		else if(d.contains("W")) dir=1;
		else if(d.contains("S")) dir=2;
		else dir=3;
		
		int nr =r;
		int nc =c;
		
		for(int i=0; i< cnt; i++) {
			nr += dr[dir];
			nc += dc[dir];
			if(nr <1 || nc<1 || nr>N || nc>M) continue;
			
			//넘어가지 않은 도미노라면
			//해당 도미노의 위치값을 큐에 담아주고, 도미노 넘겨주기.
			if(!mapState[nr][nc]) { // false 안넘어 간거.
				q.offer(new Position(nr,nc));
				mapState[nr][nc] = true;
				res++;	//포인트 증가
			}
		}
		while(!q.isEmpty()) {
			Position n = q.poll();
			attack(n.r,n.c,d,map[n.r][n.c]-1);
		}
	}
	static class Position{
		int r,c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}