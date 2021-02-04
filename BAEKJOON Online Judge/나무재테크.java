import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
		봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터  양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.

여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.

가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다. 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.

겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
		 */

public class 나무재테크 {
	static int N, M, K;
	static int[][] energy;
	static int[][] map;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static ArrayList<Tree> liveTree, deadTree;
	static PriorityQueue<Tree> tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt(); // 나무 입력 개수
		K = sc.nextInt(); // 재테크 년도

		map = new int[N + 1][N + 1]; // 0. 양분값들을 저장할 공간 1. 나무 데이터를 저장할 공간
		energy = new int[N + 1][N + 1]; // 겨울에 증가할 양분 데이터 저장 셋.

		// tree = new LinkedList<>();
		tree = new PriorityQueue<>();

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				energy[r][c] = sc.nextInt(); // 추가 양분 값
				map[r][c] = 5; // 초기 양문 값들은 모두 5
			}
		}
		for (int i = 0; i < M; i++) {
			tree.offer(new Tree(sc.nextInt(), sc.nextInt(), sc.nextInt())); // 나무 좌표와 나이값 저장.
		}

		liveTree = new ArrayList<>();	//살아있는 나무 데이터 저장
		deadTree = new ArrayList<>();	//죽은 나무 데이터 저장.

		for (int year = 0; year < K; year++) {

			while (!tree.isEmpty()) {
				Tree tr = tree.poll();
				// 봄. 나무 위치값으로 찾아가서 양분이 나이만큼 있으면 나무 심고, 만약 나무가 여러개면 나이 어린 친구부터. ++나무나이
				if (map[tr.r][tr.c] >= tr.tree_age) { // 양분이 나무의 나이를 수용할 수 있으면
					map[tr.r][tr.c] -= tr.tree_age; // 나무의 나이만큼 양분을 흡수하고
					tr.tree_age++; // 나무 나이 증가
					liveTree.add(tr);
				}
				else 
					deadTree.add(tr);
			}
			
			// 여름. 해당 좌표에 양분이 나이만큼 없었으면 나무 나이/2 만큼 기존 양분에 추가.
			for (int idx = 0; idx < deadTree.size(); idx++) {
                Tree tr = deadTree.get(idx);
                // 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다
                map[tr.r][tr.c] += tr.tree_age / 2;
            }
            deadTree.clear();	//다음 해 작업을 위해 죽은 나무 List 초기화.
            
			// 가을. 나무 나이가 5의 배수면 8방위로 나이가 1인 나무 번식 배열을 벗어나는 경우 빼곤 다 가능
			for (int idx = 0; idx < liveTree.size(); idx++) {
				Tree tr = liveTree.get(idx);

				if (tr.tree_age % 5 == 0) { // 양분을 먹고 나이가 증가한 나무의 번식 가능여부 체크.
					tree.offer(tr);	// 베이스 나무 오퍼 해둔 뒤.
					for (int d = 0; d < 8; d++) {
						int nr = tr.r + dr[d];
						int nc = tr.c + dc[d];

						if (nr < 1 || nc < 1 || nr > N || nc > N)
							continue;

						tree.offer(new Tree(nr, nc, 1)); // 1살 짜리 나무들 번식.
					}
				} 
				else // 나무 나이가 5가 안되는 경우는 있는 그대로 오퍼.
					tree.offer(tr);
			}
			liveTree.clear();
			
			// 겨울. 맵 전체에 energy 만큼 양분 추가.

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					map[r][c] += energy[r][c];
				}
			}
			// 위 4가지 K 만큼 반복.
		}
		System.out.println(tree.size());
	}

	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int tree_age;

		public Tree(int r, int c, int tree_age) {
			this.r = r;
			this.c = c;
			this.tree_age = tree_age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.tree_age - o.tree_age;
		}
	}
}
