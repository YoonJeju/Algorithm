import java.util.Scanner;

public class 빗물 {
	static int W, H;
	static int[] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		H = sc.nextInt();
		W = sc.nextInt();

		map = new int[W];

		for (int w = 0; w < W; w++) {
			map[w] = sc.nextInt();
		}
		int left = 0;
		int sum = 0;
		//오른쪽으로 먼저 진행하면서 웅덩이가 생기는 곳을 찾아 값을 더해주자.
		//왼쪽 진행을 위해 오른쪽 진행 과정에서 생긴 웅덩이를 매꿔놓고 넘어가기.
		for (int right = 1; right < W; right++) {
			if (map[left] <= map[right]) {
				// 웅덩이가 있는 경우에는.
				if (right - left > 1) {
					int idx = left;
					while (idx != right) {
						sum += map[left] - map[idx];
						map[idx] = map[left];
						idx++;
					}
					left = right;
				}
				//바로 옆칸이 더 긴거면 웅덩이 안생김!
				else
					left = right;
			}
		}
		int right = W - 1;
		for (left = W - 2; left >= 0; left--) {
			if (map[left] >= map[right]) {
				if (right - left > 1) {
					int idx = right;
					while (idx != left) {
						sum += map[right] - map[idx];
						map[idx]=map[right];
						idx--;
					}
					right = left;
				} else
					right = left;
			}
		}
		System.out.println(sum);
	}
}