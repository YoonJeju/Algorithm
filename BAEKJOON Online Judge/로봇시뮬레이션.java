import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇시뮬레이션 {
	static int A,B,N,M;
	static int map[][];
	static Robot R[];
	static int dr[]= {-1,0,1,0};	//상우하좌
	static int dc[]= {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken()) + 1; //col 길이
		B = Integer.parseInt(st.nextToken()) + 1; //row 길이
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 로봇 수
		M = Integer.parseInt(st.nextToken()); // 명령 수
		
		map = new int[B][A];
		R = new Robot[N+1];
		for(int idx=1; idx<=N; idx++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			if(dir.equals("S")) R[idx] = new Robot(row,col,0); 	//상하 반대
			else if(dir.equals("N")) R[idx] = new Robot(row,col,2); 	
			else if(dir.equals("W")) R[idx] = new Robot(row,col,3); 	
			else R[idx] = new Robot(row,col,1);
			map[row][col] = idx;
		}
		
		boolean flag = false;
		out :for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			
			Robot r = R[idx];
			
			if(d.equals("L")){
				R[idx].dir = (r.dir + cnt) %4;
			}
			else if(d.equals("R")) {
				R[idx].dir = (r.dir + cnt *3) %4;
			}
			else {
				int nr = r.r;
				int nc = r.c;
				for(int j=0; j<cnt; j++) {
					nr += dr[r.dir];
					nc += dc[r.dir];
					
					if(nr<1 || nc<1 || nr==B || nc==A) {
						System.out.printf("Robot %d crashes into the wall",idx);
						flag = true;
						break out;
					}
					
					else if(map[nr][nc]!=0) {
						System.out.printf("Robot %d crashes into robot %d",idx, map[nr][nc]);
						flag = true;
						break out;
					}
				}
				//이동 되면 최신 업데이트.
				map[r.r][r.c]= 0;
				map[nr][nc] = idx;
				R[idx] = new Robot(nr,nc,r.dir);
			}
		}
		if(!flag) System.out.println("OK");
	}
	static class Robot{
		int r,c,dir;

		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
