import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 나이트의이동 {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static Position end;
	static Queue<Position> horse;
	static int[] dr = {-1,-2,-2,-1,1,2,2,1};
	static int[] dc = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			N = sc.nextInt();
			horse = new LinkedList<>();
			visit = new boolean[N][N];
			int horseX = sc.nextInt();
			int horseY = sc.nextInt();
			horse.offer(new Position(horseX,horseY,0));
			end = new Position(sc.nextInt(),sc.nextInt(),0);
			
			visit[horseX][horseY]=true;	//초기위치 방문 처리
			
			BFS();
		}
	}
	
	
	private static void BFS() {
		
		while(!horse.isEmpty()) {
			Position ch = horse.poll();
			if(ch.r == end.r && ch.c == end.c ) {	//말 위치와 도착지점이 같으면 종료.
				System.out.println(ch.cnt);
				return;
			}
			for(int d=0; d<8; d++) {
				int nr = ch.r + dr[d];
				int nc = ch.c + dc[d];
				
				if(nr <0 || nc <0 || nr>=N || nc>=N)
					continue;
				
				if(visit[nr][nc]) 
					continue;
				
				visit[nr][nc]=true;
				horse.offer(new Position(nr,nc,ch.cnt+1));
			}
		}
	}
	static class Position {
		int r, c, cnt;

		public Position(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	} 
}