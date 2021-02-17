import java.util.Scanner;

public class 타겟넘버 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] numbers = new int[5];
		int target = 3;
		for(int i=0; i<5; i++) {
			numbers[i] = 1;
		}
		int answer = DFS(0,0, target, numbers);
		System.out.println(answer);
	}
	static int DFS(int idx, int num, int target, int[] numbers){
        if(numbers.length == idx){
            if(num==target){
                return 1;
            }
            return 0 ;
        }
        int minusNum = num - numbers[idx];
        int plusNum = num + numbers[idx];
        return DFS(idx + 1, minusNum, target, numbers) + DFS(idx +1, plusNum, target, numbers);
    } 
}
