import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {
	static int L,R,C;
	static boolean[][][] visited;
	static int dr[] = {-1, 1, 0, 0, 0, 0}; //상하좌우위아래
    static int dc[] = {0, 0, -1, 1, 0, 0};
    static int dh[] = {0, 0, 0, 0, 1, -1};
	static char map[][][];
	static Queue<Position> queue;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L==0 || R ==0 || C==0) break;

			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			queue = new LinkedList<>();
			boolean flag = false;
			String str;
			for(int l =0; l<L; l++) {
				for(int r=0; r<R; r++) {
					str = br.readLine(); 
					for(int c=0; c<C; c++) {
						map[l][r][c] = str.charAt(c);
						if(map[l][r][c]=='S') {
							queue.offer(new Position(l,r,c,0));
							visited[l][r][c] = true;
						}
					}
				}
				str = br.readLine();
			}
			
			out :while(!queue.isEmpty()) {
				Position n  = queue.poll();
				
				for(int d=0; d<6; d++) {
					int nr = n.r + dr[d];
					int nc = n.c + dc[d];
					int nh = n.h + dh[d];
					
					if(nr<0 || nc<0 || nh<0 || nr>=R || nc>=C || nh>=L) continue;
					
					if(map[nh][nr][nc] =='E') {
						n.move++;
						System.out.println("Escaped in " + n.move + " minute(s).");
						flag = true;
						break out;
					}
					
					if(visited[nh][nr][nc]) continue;
					
					if(map[nh][nr][nc] =='.') {
						queue.offer(new Position(nh,nr,nc,n.move+1));
						visited[nh][nr][nc] = true;
					}
				}
			}	//end for while
			if(!flag) System.out.println("Trapped!");
		}
		
		
	}
	static class Position{
        int h,r,c;
        int move;
        public Position(int h, int r, int c, int move) {
        	this.h = h;
        	this.r = r;
            this.c = c;
            this.move = move;
        }
    }
}
