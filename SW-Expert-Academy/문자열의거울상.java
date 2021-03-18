import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문자열의거울상 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String str = br.readLine();
			char[] input = new char[str.length()];
			int idx = str.length()-1;
			
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)=='b') input[idx] = 'd';
				else if(str.charAt(i)=='d') input[idx] = 'b';
				else if(str.charAt(i)=='p') input[idx] = 'q';
				else input[idx] = 'p';
				idx--;
			}
			System.out.print("#" + tc+ " ");
			for(int i=0; i<str.length(); i++) {
				System.out.print(input[i]);
			}
			System.out.println();
		}
	}

}
