import java.io.BufferedReader;
import java.io.InputStreamReader;

// W 와 B 의 비교 대상의 개수가 같다면 배치만 바꾸면 된다
// W 와 B 의 비교 대상의 개수가 다르다면 무조건 개수를 맞추는 최소값이 필요로 한다.
// 바꿔야 하는 배치 값값의 최소 값과 개수를 추가해야 하는 W 혹은 B의 최소 값의 합이 결과값.
public class 오셀로재배치 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			int A=0, B=0;
			
			int N = Integer.parseInt(br.readLine());
			int res=0;
			
			String input = br.readLine();
			String targetInput = br.readLine();
			
			for(int i=0; i<N; i++) {
				if(input.charAt(i)=='B' && targetInput.charAt(i)=='W')
					A++;
				if(input.charAt(i)=='W' && targetInput.charAt(i)=='B')
					B++;
			}
			res = Math.max(A, B);
			System.out.println(res);
		}
		
	}

}
