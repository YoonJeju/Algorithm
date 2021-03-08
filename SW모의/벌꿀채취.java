import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벌꿀채취 {
	static int N,M,C,max;
	static int[][] map,profit;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//벌통 크기
			M = Integer.parseInt(st.nextToken());	//벌통 수
			C = Integer.parseInt(st.nextToken());	//최대 채취 양
			
			map = new int[N][N];
			profit = new int[N][N];
			
			max = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//profitMap 에 각 좌표에 대한 수익 최대값을 저장해두자!
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<=N-M; c++) {
					settingMap(r,c,0,0,0);
				}
			}
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<=N-M; c++) {
					int profitSum =0;
					//첫번째 사람 수익 저장.
					profitSum += profit[r][c];
					int nr = r;
					//두번째 사람 같은 라인
					for(int nc=c+M; nc<=N-M; nc++) {
						max = profitSum + profit[nr][nc] > max ? profitSum + profit[nr][nc] : max;
					}
					//두번째 사람 이후 아래 라인
					nr++;
					while(nr<N) {
						for(int nc=0; nc<=N-M; nc++) {
							max = profitSum + profit[nr][nc] > max ? profitSum + profit[nr][nc] : max;
						}
						nr++;
					}
					
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
	private static void settingMap(int r, int c, int idx, int sum, int pf) {
		//꿀통 합이 한계치 보다 크면 안되
		if(sum > C) return;
		
		//M라인만큼 채우면. 수익 정산
		if(idx==M) {
			profit[r][c-M] = pf > profit[r][c-M] ? pf : profit[r][c-M]; 
			return;
		}
		
		settingMap(r, c+1, idx+1, sum + map[r][c], pf + (int)Math.pow(map[r][c], 2));
		settingMap(r,c+1,idx+1,sum,pf);
	}
}