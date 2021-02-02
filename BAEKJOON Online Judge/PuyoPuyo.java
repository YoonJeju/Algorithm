import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class PuyoPuyo {
	static int destroy;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean Puyo;
	static boolean[][] visited;
	static Queue<Pair> queue;
	static List<Pair> PuyoPoint;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new char[13][7];
		queue = new LinkedList<>();
		//4개 이상의 블럭이 쌓인 뿌요 좌표를 저장해두고, 부숴줄 좌표 기억해 둘 ArrayList.
		PuyoPoint = new ArrayList<>();
		//데이터 입력셋
		for(int i=1; i<=12; i++) {
			String str = sc.next();
			for(int j=0; j<6; j++) {
				map[i][j+1] = str.charAt(j);
			}
		}
		//반복문을 아래에서부터 위로 올라가는 형태로 만들어서 . 이아니면 BFS로 가져들어가자.
		//들어가서 4개이상 4방위로 연결이 되있는지 체크하면서 List 에 위치값을 담아주기.
		//4개이상인 경우 그 좌표의 값들을 . 으로 모두 변경해주자.
		while(true) {
			Puyo=false;
			visited = new boolean[13][7];
			for(int r=12; r>0; r--) {
				for(int c=1; c<=6; c++) {
					if(map[r][c]!='.' && !visited[r][c]) {
						queue.offer(new Pair(r,c));
						PuyoPoint.add(new Pair(r,c));
						visited[r][c] =true;
						BFS();
					}
				}
			}
			downMap();
			
			//한번도 Puyo 가 안됬다면 그대로 죵료.
			if(!Puyo)
				break;
			else {
				destroy++;
				Puyo = false;
			}
		}
		System.out.println(destroy);
		
		
		
	}
	
	private static void downMap() {
		for(int r=12; r>0; r--) {
			for(int c=1; c<=6; c++) {
				if(map[r][c]!='.') continue;
				for(int up =r-1; up>0; up--) {
					if(map[up][c]!='.') {
						map[r][c] = map[up][c];
						map[up][c] = '.';
						break;
					}
				}
			}
		}
	}

	private static void BFS() {
		int cnt=1;
		
		while(!queue.isEmpty()) {
			Pair p = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if (nr < 1 || nc < 1 || nr > 12 || nc > 6)
					continue;

				if (visited[nr][nc])
					continue;
				
				if(map[nr][nc] == map[p.r][p.c]) {
					visited[nr][nc] = true;
					queue.offer(new Pair(nr,nc));
					PuyoPoint.add(new Pair(nr,nc));
					cnt++;
				}
			}
		}
		//뭉친 뿌요가 4개 이상이면.
		if(cnt>=4) {
			//PuyoPoint 에 쌓아둔 뿌요 를 모두 . 으로 바꿔주자.
			for(int i=0; i<PuyoPoint.size(); i++) {
				int r = PuyoPoint.get(i).r;
				int c= PuyoPoint.get(i).c;
				map[r][c] ='.';
			}
			Puyo = true;
		}
		else 
			PuyoPoint.clear();
	}

	static class Pair{
		int r,c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}