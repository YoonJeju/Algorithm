import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 거울 {
	static int N, M;
	static int[][] map;
	static int dr[] = {-1,0,1,0};	//상 좌 하 우
	static int dc[] = {0,-1,0,1};
	static StringBuilder sb = new StringBuilder(); 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1)
					map[i][j] = -1;
				else
					map[i][j] = 0;
			}
		}
		setting();
		solve();
		sb.deleteCharAt(sb.length()-1);
		System.out.print(sb.toString());
	}
	private static void setting() {
		int mirror = 1;
		
		int r = 1, c = 0;
		// 왼쪽(내려가기)
		for (int i = 0; i < N; i++) 
			map[r++][c] = mirror++;
		c++;
		
		// 하단(오른쪽으로 가기)
		for (int i = 0; i < M; i++) 
			map[r][c++] = mirror++;
		r--;

		// 우측(위로가기)
		for (int i = 0; i < N; i++) 
			map[r--][c] = mirror++;
		c--;

		// 상단(왼쪽으로가기)
		for (int i = 0; i < M; i++) 
			map[r][c--] = mirror++;
	}
	//상 좌 하 우
	private static void solve() {
		int r=1,c=1;
		// >
		for (int i = 0; i < N; i++) 
			shoot(r++,c,3);
		r--;

		// 위
		for (int i = 0; i < M; i++) 
			shoot(r,c++,0);
		c--;
		
		// <(위로가기)
		for (int i = 0; i < N; i++) 
			shoot(r--,c,1);
		r++;
		
		// 상단(왼쪽으로가기)
		for (int i = 0; i < M; i++) 
			shoot(r,c--,2);
	}

	private static void shoot(int r, int c, int dir) {
			if(map[r][c] > 0) {
				sb.append(map[r][c] + " ");
				return;
			}
			//거울이 설치 되있다면 방향 바꿔주기!
			if(map[r][c]==-1) {
				if(dir==0) dir=3;
				else if(dir==1) dir=2;
				else if(dir==2) dir=1;
				else dir=0;
				
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				shoot(nr,nc,dir);
			}
			//거울이 없다면 직진!
			else {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				shoot(nr,nc,dir);
			}
	}
}
