import java.util.Scanner;

public class 동전1 {
	static int N,K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		int coin[] = new int[N+1];
		int DP[] = new int[K+1];
		for(int i=1; i<=N; i++) 
			coin[i] = sc.nextInt();
		
		DP[0] = 1;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				if(j >=coin[i])
					DP[j] += DP[j-coin[i]];
			}
		}
		System.out.println(DP[K]);
	}
}