import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 경쟁적전염 {
	static int N, K, time;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int[][] map;
	static PriorityQueue<Virus> pq;
	static List<Virus> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		pq = new PriorityQueue<>();
		list = new ArrayList<>();
		map = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] > 0)
					pq.offer(new Virus(r, c, map[r][c]));
			}
		}

		int S = sc.nextInt();
		int X = sc.nextInt();
		int Y = sc.nextInt();
		
		while(time!=S) {
			Spread();
		}
		System.out.println(map[X][Y] !=0 ? map[X][Y] : 0 );
	}

	private static void Spread() {
		while (!pq.isEmpty()) {
			Virus v = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = v.r + dr[d];
				int nc = v.c + dc[d];

				if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

				if(map[nr][nc] != 0) continue;
				
				map[nr][nc] = v.no;
				list.add(new Virus(nr,nc,v.no));
			}
		}
		
		for(int i=0; i<list.size(); i++) 
			pq.offer(list.get(i));
		
		list.clear();
		time++;
	}

	static class Virus implements Comparable<Virus> {
		int r, c, no;

		public Virus(int r, int c, int no) {
			this.r = r;
			this.c = c;
			this.no = no;
		}
		@Override
		public int compareTo(Virus o) {
			return this.no >= o.no ? 1: -1;
		}
	}
}
