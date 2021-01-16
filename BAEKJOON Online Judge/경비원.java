import java.util.Scanner;

public class 경비원 {
	static class Point{
		int dir;
		int dist;
		public Point(int dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
	}
	static int R,C,N,DIST;
	static Point home;
	static Point[] store;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		C = sc.nextInt();
		R = sc.nextInt();
		N = sc.nextInt();
		
		store = new Point[N];
		for(int i=0; i<N; i++) {
			store[i] = new Point(sc.nextInt(),sc.nextInt());
		}
		home = new Point(sc.nextInt(),sc.nextInt());
		
		for(int i=0; i<N; i++) {
			//동근이 위치가 북쪽일 때
			if(home.dir==1) {
				if(store[i].dir==1)
					DIST += Math.abs(store[i].dist - home.dist);
				else if(store[i].dir==2)
					DIST += Math.min(home.dist + store[i].dist, C - home.dist + C - store[i].dist) + R;
				else if(store[i].dir==3)
					DIST += home.dist + store[i].dist;
				else
					DIST += C-home.dist + store[i].dist;
			}
			//동근이 위치가 남쪽일 때
			else if(home.dir==2) {
				if(store[i].dir==1)
					DIST += Math.min(home.dist + store[i].dist, C - home.dist + C - store[i].dist) + R;
				else if(store[i].dir==2)
					DIST += Math.abs(store[i].dist - home.dist);
				else if(store[i].dir==3)
					DIST += home.dist + R - store[i].dist;
				else
					DIST += C-home.dist + R - store[i].dist;
			}
			//동근이 위치가 서쪽일 때
			else if(home.dir==3) {
				if(store[i].dir==1)
					DIST +=store[i].dist + home.dist;
				else if(store[i].dir==2)
					DIST += store[i].dist +  R - home.dist;
				else if(store[i].dir==3)
					DIST += Math.abs(store[i].dist - home.dist);
				else
					DIST += Math.min(home.dist + store[i].dist, R - home.dist + R - store[i].dist) + C;
			}
			//동근이 위치가 동쪽일떼
			else {
				if(store[i].dir==1)
					DIST += C - store[i].dist + home.dist;
				else if(store[i].dir==2)
					DIST += C - store[i].dist +  R - home.dist;
				else if(store[i].dir==3)
					DIST += Math.min(home.dist + store[i].dist, R - home.dist + R - store[i].dist) + C;
				else
					DIST += Math.abs(store[i].dist - home.dist);
			}
		}
		System.out.println(DIST);
	}
}