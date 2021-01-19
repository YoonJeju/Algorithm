import java.util.Scanner;

public class A와B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String S = sc.next(); // 시작문자열
		String T = sc.next(); // 끝 문자열

		StringBuilder sb = new StringBuilder(T); // T를 저장해둘 sb

		/*
		 * 1. 문자열의 뒤에 A를 추가한다. -> T의 끝자리가 A면 A를 제거. 2. 문자열을 뒤집고 뒤에 B를 추가한다. -> T의 끝자리가
		 * B면 B를 제거하고, 뒤집는다.
		 */
		while (S.length() < sb.length()) {
			if (sb.charAt(sb.length() - 1) == 'A') { // 만약 끝자리가 A라면
				sb = new StringBuilder(sb.substring(0, sb.length() - 1));
			}
			else if (sb.charAt(sb.length() - 1) == 'B') { // B라면
				sb = new StringBuilder(sb.substring(0, sb.length() - 1));
				sb.reverse(); // 역순으로 뒤집기
			}
		}
		if(sb.toString().equals(S)) 
			System.out.println(1);
		else
			System.out.println(0);
		
		
	} // main
}// class
