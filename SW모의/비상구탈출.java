import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 비상구탈출 {
	static int N,T,res;
	static int[] manSelect;
	static int[][] map;
	static List<Position> man,exit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			for(int i=0; i<10; i++)
				man = new ArrayList<>();
			for(int i=0; i<2; i++)
				exit = new ArrayList<>();
			
			for(int r=1; r<=N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=1; c<=N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] ==1) man.add(new Position(r,c));	//사람의 위치 좌표를 man 리스트에 저장
					else if(map[r][c] ==2) exit.add(new Position(r,c));	//비상구의 위치 좌표를 exit 리스트에 저장
					
				}
			}
			res = Integer.MAX_VALUE;
			manSelect = new int[man.size()];	//각 사람들의 비상구 번호 선택 배열.
			selectEXIT(0);
			System.out.println("#" + tc + " " + res);
		}
		
	}
	private static void selectEXIT(int cnt) {
		if(cnt==man.size()) {
			goExit();
			
			return;
		}
		for(int i=0; i<2; i++) {
			manSelect[cnt] = i;
			selectEXIT(cnt+1);
		}
	}
	
	private static void goExit() {
		int time = 0;
		int escape = 0;
		boolean used[] = new boolean[2];
		Queue<Status> queue = new LinkedList<>();
		
		
		int arriveTime = 0;
		for(int i=0; i<man.size(); i++) {
			int er = exit.get(manSelect[i]).r;
			int ec = exit.get(manSelect[i]).c;
			//도착시간을 저장 할 땐 내려가는 시간을 고려해 +1 로 저장!
			arriveTime = Math.abs(man.get(i).r - er) + Math.abs(man.get(i).c - ec) + 1;
			queue.offer(new Status(arriveTime, manSelect[i]));
		}
		
		while(escape<man.size()) {
			time++;
			int size = queue.size();
			for(int i=0; i<size; i++) {
				Status now = queue.poll();
				
				//비상구에 도착한 시간과 현재 시간이 일치하면
				if(now.arrive==time) {
					//현재 비상구가 사용중이지 않으면
					if(!used[now.exitNum]) {
						used[now.exitNum] = true;
						escape++;
					}
					//비상구가 이미 사용중이면, 자신의 도착시간에 +1 해주고 다시 대기.
					else queue.offer(new Status(now.arrive+1, now.exitNum));
				}
				//아직 비상구에 도착하지 않은 경우
				else if(now.arrive> time)
					queue.offer(new Status(now.arrive, now.exitNum));
			}
			//해당 시간대에 처리가 끝난 뒤엔 다시 비상구를 비워주기.
			if(used[0]) used[0] = false;
			if(used[1]) used[1] = false;
		}
		if(res>time)
			res = time;
	}

	static class Position{
		int r,c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Status  {
		int arrive;
		int exitNum;
		public Status(int arrive, int exitNum) {
			this.arrive = arrive;
			this.exitNum = exitNum;
		}
	}
}
