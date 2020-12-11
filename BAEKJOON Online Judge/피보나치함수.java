import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 피보나치함수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		int[][] DP = new int[2][41];
		
		DP[0][0] = 1;
		DP[1][1] = 1;
		
		for(int i=2; i<=40; i++) {
			DP[0][i] = DP[0][i-1] + DP[0][i-2];
			DP[1][i] = DP[1][i-1] + DP[1][i-2];
		}
		
		for(int tc=0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(String.format("%d %d\n", DP[0][N],DP[1][N]));
		}
		System.out.println(sb);
		br.close();
	}

}
