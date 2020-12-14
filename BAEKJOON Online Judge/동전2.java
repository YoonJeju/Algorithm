import java.util.Scanner;

public class 동전2 {
	static int N,K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		int coin[] = new int[N+1];
		int res[] = new int[K+1];
		for(int i=1; i<=N; i++) 
			coin[i] = sc.nextInt();
		
		for(int i=1; i<=K; i++)
			res[i] = Integer.MAX_VALUE;
		
		for(int i=1; i<=N; i++) {
			for(int j=coin[i]; j<=K; j++) {
				if(res[j] > res[j-coin[i]])
					res[j] = res[j-coin[i]] + 1;
			}
		}
		//	  0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
		//1   0  1  2  3  4  0  1  2  3  4   0   1   0   1   2   0
		//5   0  0  0  0  0  1  1  1  1  1   2   2   0   0   0   3
		//12  0  0  0  0  0  0  0  0  0  0   0   0   1   1   1   0
		//합	  0  1  2  3  4  1  2  3  4  5   2   3   1   2   3   3
		if(res[K] == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(res[K]);
	}
}