import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Summation {
	static int max,min;
	static int[][] df;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		df = new int[T][10];
		for(int tc=1; tc<=T; tc++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<10; i++) {
				df[tc-1][i] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=0; j<10; j++) {
				int tmp = search(df[tc-1][j]);
				if(max < tmp) max = tmp;
				if(min > tmp) min = tmp;
			}
			System.out.println("#" + tc + " " + max + " " + min);
		}
	}
	private static int search(int num) {
		int sum=0;
		for(int i=1000000; i>=1; i/=10) {
			sum+=(num / i);
			num -= i*(num/i);
		}
		return sum;
	}
}