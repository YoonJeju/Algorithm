import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4 {
	static int N,M,K,res;
	static int[] seq;
	static boolean[] selected;
	static Status[] cmd;
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,1,0,-1};
	static int[][] map,copyMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	//회전 연산 수
		
		res = Integer.MAX_VALUE;
		map = new int[N+1][M+1];
		copyMap = new int[N+1][M+1];
		cmd = new Status[K];
		seq = new int[K];
		selected = new boolean[K];
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=M; c++) {
				copyMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//회전 연산 명령 입력
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cmd[k] = new Status(r,c,s);
		}
		selectOrder(0);
		
		System.out.println(res);
	}
	
	//순열로 순서 정해주기
	private static void selectOrder(int idx) {
		if(idx == K) {
			//맵 카피
			copy();
			for(int i=0; i<K; i++) {
				Rotate(seq[i]);
			}
			//각 행 값 더해서 최소값 찾기
			int r=1;
			while(r<=N) {
				int sum = 0;
				for(int c=1; c<=M; c++) {
					sum+= map[r][c];
				}
				if(sum<res) res = sum;
				r++;
			}
			return;
		}
		for(int i=0; i<K; i++) {
			if(selected[i]) continue;
			seq[idx] = i;
			selected[i] = true;
			selectOrder(idx+1);
			selected[i] = false;
		}
	}

	private static void copy() {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=M; c++) {
				map[r][c] =copyMap[r][c];
			}
		}
	}

	private static void Rotate(int idx) {
		int r = cmd[idx].r - cmd[idx].s;
		int c = cmd[idx].c - cmd[idx].s;
		int s = cmd[idx].s * 2;
		while(s>1) {
			int tmp = map[r][c];
			int dir =-1;
			int nr=0; 
			int nc=0;
			for(int i=0; i<4; i++) {
				dir++;
				nr= r + dr[dir];
				nc= c + dc[dir];
				for(int j=0; j<s; j++) {
					if(i==3 && j==s-1) {
						map[r][c] = tmp;
						c+=dc[dir];
						break;
					}
					map[r][c] = map[nr][nc];
					r+=dr[dir];
					c+=dc[dir];
					nr+=dr[dir];
					nc+=dc[dir];
				}
			}
			s-=2;
			dir=-1;
			r++; c++;
		}
	}

	static class Status{
		int r,c,s;

		public Status(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}