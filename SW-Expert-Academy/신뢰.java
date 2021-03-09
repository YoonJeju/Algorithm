import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 신뢰 {
	static Queue<Integer> B,O;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			String[] order = new String[N];
			B = new LinkedList<>();
			O = new LinkedList<>();
			int idx = 0;
			
			while(st.hasMoreTokens()) {
				String tmp = st.nextToken();
				int tmpN = Integer.parseInt(st.nextToken());
				order[idx++] = tmp;
				if(tmp.equals("B")) B.add(tmpN);
				else O.add(tmpN);
				
			}
			idx = 0;
			int time = 0;
			int posB=1, posO=1;
			int desB=0, desO=0;
			String cur;
			while(idx<N) {
				time++;
				cur = order[idx];
				desB = B.peek()==null?0:B.peek();
				desO = O.peek()==null?0:O.peek();
				
				if(cur.equals("B")){
					if(desB > posB) posB++;
					else if(desB < posB) posB--;
					else {
						B.poll();
						idx++;
					}
					//O 단순 이동
					if(desO > posO) posO++;
					else if(desO < posO) posO--;
					
				}
				if(cur.equals("O")){
					if(desO > posO) posO++;
					else if(desO < posO) posO--;
					else {
						O.poll();
						idx++;
					}
					//B 단순 이동
					if(desB > posB) posB++;
					else if(desB < posB) posB--;
				}
			}
			System.out.println("#" + tc + " " + time);
		}
	}
}
