import java.util.Scanner;

/*
다음과 같이 정의되는 함수의 함수값을 계산
(사용자 입력 x)

f(x) = x^3 - 9x + 2 	x <= 0 //조건
f(x) = 7x + 2 			x > 0 //조건
*/
public class FuncQuestion {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("input x :");
		int x = kb.nextInt();
		
		if(x <= 0) {
			System.out.println(x^3 -9*x +2);
		} else {
			System.out.println(7*x + 2);
		}
	}
}