import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자 {
	static int T,R,C,res;
	static char[][] map;
	static int[][] dist;
	static Queue<Pos> que;
	static int dr[]= {-1,1,0,0};
	static int dc[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			res = 0;
			map = new char[R][C];
			dist = new int[R][C];
			que = new LinkedList<>();
			for(int r=0; r<R; r++) {
				String str = br.readLine();
				for(int c=0; c<C; c++) {
					map[r][c] = str.charAt(c);
				}
			}
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c]=='W') {
						que.offer(new Pos(r,c));
					}
				}
			}
			bfs();
			distSum();
			System.out.println("#" + tc + " " + res);
			
		}
	}
	
	private static void distSum() {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				res+=dist[r][c];
			}
		}
	}

	private static void bfs() {
		while(!que.isEmpty()) {
			Pos n = que.poll();
			for(int d=0; d<4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				if(nr<0 || nc<0 || nr>=R || nc>=C || map[nr][nc]=='W') continue;
				if(dist[nr][nc] >=1) continue;
				
				dist[nr][nc] = dist[n.r][n.c]+ 1;
				que.offer(new Pos(nr,nc));
			}
		}
	}

	static class Pos{
		int r,c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
