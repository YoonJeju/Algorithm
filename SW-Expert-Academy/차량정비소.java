import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 차량정비소 {
	static int N,M,K,A,B;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			int[] RecTime = new int[N+1];
			st = new StringTokenizer(br.readLine());
			//접수 창구 별 처리시간 입력.
			for(int a=1; a<=N; a++) {
				RecTime[a] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			int[] RepTime = new int[M+1];
			//수리 창구 별 처리시간 입력.
			for(int b=1; b<=M; b++) {
				RepTime[b] = Integer.parseInt(st.nextToken());
			}
			
			int[] person = new int[K+1];
			st = new StringTokenizer(br.readLine());
			//고객들의 도착 시간 입력.
			for(int k=1; k<=K; k++) {
				person[k] = Integer.parseInt(st.nextToken());
			}
			
			Queue<Integer> waitRec = new LinkedList<>();
			Queue<Integer> waitRep = new LinkedList<>();
			
			Pair Rec[] = new Pair[N+1];
			Pair Rep[] = new Pair[M+1];
			Select Num[] = new Select[K+1];
			
			boolean[] useRec = new boolean[N+1];
			boolean[] useRep = new boolean[M+1];
			
			// 객체 생성을 해줘라 바보야
			for (int i = 1; i <= N; i++) 
				Rec[i] = new Pair();
			
			for (int i = 1; i <= M; i++) 
				Rep[i] = new Pair();
			
			for (int i = 1; i <= K; i++) 
				Num[i] = new Select();
			
			int time = 0;
			int pass = 0;
			int res =0;
			while(pass!=K) {
				//해당 시간에 방문한 고객 모두 접수 대기
				for(int i=1; i<=K; i++) {
					if(time==person[i]) {
						waitRec.add(i);
					}
				}
				//접수처에서 시간이 모두 경과한 고객 정비 대기
				for(int i=1; i<=N; i++) {
					if(Rec[i].num !=0 && Rec[i].time == time) {
						waitRep.add(Rec[i].num);
						Rec[i].num = 0;
						useRec[i] = false;
					}
				}
				//정비처에서 시간이 모두 경과한 고객 종료 후 pass 카운트 증가
				for(int i=1; i<=M; i++) {
					if(Rep[i].num !=0 && Rep[i].time == time) {
						Rep[i].num = 0;
						useRep[i] = false;
						pass++;
					}
				}
				
				for(int i=1; i<=N; i++) {
					//접수 대기자가 없으면 접수처에 들어갈 일 없음.
					if(waitRec.size()==0)break;
					
					if(!useRec[i]) {
						Rec[i].num = waitRec.poll();
						Rec[i].time = time + RecTime[i];
						useRec[i] = true;
						Num[Rec[i].num].RecNum = i;
					}
				}
				
				for(int i=1; i<=M; i++) {
					//수리 대기자가 없으면 들어갈 일 없다!!
					if(waitRep.size()==0) break;
					
					if(!useRep[i]) {
						Rep[i].num = waitRep.poll();
						Rep[i].time = time + RepTime[i];
						useRep[i] = true;
						Num[Rep[i].num].RepNum = i;
					}
				}
				time++;
			}
			
			for(int k=1; k<=K; k++) {
				if(Num[k].RecNum==A && Num[k].RepNum==B) {
					res+=k;
				}
			}
			System.out.println("#"+tc+" "+(res==0?-1:res));
			
			
		}
	}
	static class Pair{
		int num;
		int time;
		
		public Pair() {
			this.num = 0;
			this.time = 0;
		}
	}
	static class Select{
		int RecNum;
		int RepNum;
		public Select() {
			this.RecNum = 0;
			this.RepNum = 0;
		}
	}
}
