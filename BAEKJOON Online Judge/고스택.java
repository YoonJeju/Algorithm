import java.util.Scanner;
import java.util.Stack;

public class 고스택 {
	static Stack<Long> stack;
	static String[] cmd;
	static Long[] num;
	/* NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109)
	POP: 스택 가장 위의 숫자를 제거한다.
	INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
	DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
	SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
	ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
	SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
	MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
	DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
	MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다. */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = null;
		stack = new Stack<>();
		
		//Quit 전까지 반복
		out : while(true) {
			cmd = new String[100001];
			num = new Long[100001];
			int idxCmd=0;
			int idxNum=0;
			
			//명령어 입력 받는 반복문.
			while(true) {
				s = sc.next();
				cmd[idxCmd++] = s;
				if(s.equals("QUIT")) break out;
				else if(s.equals("NUM")) num[idxNum++] = sc.nextLong();
				else if(s.equals("END")) break;
			}
			//프로그램 수행 횟수
			int N = sc.nextInt();
			
			for(int i=0; i<N; i++) {
				boolean Error = false;
				int numIdx=0;
				stack.push(sc.nextLong());
				for(int idx=0; idx<idxCmd; idx++) {
					if(cmd[idx].equals("NUM")) {
						stack.push(num[numIdx++]);
					}
					else if(cmd[idx].equals("POP")) {
						if(stack.isEmpty()) {
							Error = true;
							break;
						}
						else {
							stack.pop();
						}
					}
					else if(cmd[idx].equals("INV")) {
						if(stack.isEmpty()) {
							Error = true;
							break;
						}
						else {
							long temp = stack.pop();
							stack.push(-temp);
						}
					}
					else if(cmd[idx].equals("DUP")) {
						if(stack.isEmpty()) {
							Error = true;
							break;
						}
						else
							stack.push(stack.peek());
					}
					else if(cmd[idx].equals("SWP")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.pop();
							long B = stack.pop();
							stack.push(A);
							stack.push(B);
						}
					}
					else if(cmd[idx].equals("ADD")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.peek();
							stack.pop();
							long B = stack.peek();
							stack.pop();
							A = B+A;
							if(Math.abs(A) > 1000000000) {
								Error = true;
								break;
							}
							stack.push(A);
						}
					}
					else if(cmd[idx].equals("SUB")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.pop();
							long B = stack.pop();
							
							A = B-A;
							if(Math.abs(A) > 1000000000) {
								Error = true;
								break;
							}
							stack.push(A);
						}
					}
					else if(cmd[idx].equals("MUL")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.pop();
							long B = stack.pop();
							
							A = B*A;
							if(Math.abs(A) > 1000000000) {
								Error = true;
								break;
							}
							stack.push(A);
						}
					}
					else if(cmd[idx].equals("DIV")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.pop();
							long B = stack.pop();
							if(A==0) {
								Error = true;
								break;
							}
							long div = Math.abs(B) / Math.abs(A);
							
							if((A > 0 && B < 0) || (A < 0 && B > 0)) div = -div;
							stack.push(div);
						}
					}
					else if(cmd[idx].equals("MOD")) {
						if(stack.size()<2) {
							Error = true;
							break;
						}
						else {
							long A = stack.pop();
							long B = stack.pop();
							
							if(A==0) {
								Error = true;
								break;
							}
							long mod = Math.abs(B) % Math.abs(A);
							if(B < 0) mod = -mod;
							stack.push(mod);
						}
					}
				} //end for cmd
				if(stack.size()==1 && !Error) 
					System.out.println(stack.peek());
				else
					System.out.println("ERROR");
					
				
				stack.clear();
			}
			System.out.println();
		}
	}
}
