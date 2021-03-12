import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조성RE {
	static int T,N,K,top,res;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] useRoad;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			useRoad = new boolean[N][N];
			top = 0;
			res = 0;
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(top < map[r][c]) top = map[r][c];
				}
			}
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(map[r][c] == top) {
						makeMountainRoad(r,c,top,1,false);
					}
				}
			}
			System.out.println("#" + tc + " " + res);
		}
	
	}
	private static void makeMountainRoad(int r, int c, int top, int move, boolean cut) {
		useRoad[r][c] = true;
		
		if(move > res) res = move;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nc<0 || nr>=N || nc>=N || useRoad[nr][nc]) continue;
			
			if(top > map[nr][nc]) 
				makeMountainRoad(nr,nc,map[nr][nc], move+1,cut);
			
			// 깎을 수 있으면 이동할 곳의 높이는 최대 높이(top) 에서 최소한인 1만큼만 빼야
			// 다음 경로를 선택하는데 있어 가장 유리하다.
			else if(!cut && top > map[nr][nc] - K) 
				makeMountainRoad(nr,nc,top-1, move+1,true);
			
		}
		//사용한 길 복구
		useRoad[r][c] = false;
	}
}
