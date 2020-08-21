import java.util.Scanner;
/*
2와 100사이에 있는 모든 소수를 찾는 프로그램 작성.
정수 k를 2부터 k - 1 까지의 숫자로 나누어서 나머지가 0인 것이 하나라도 있으면 소수 아님
 */
public class PrimeQuestion {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		// 소수인지 아닌지 체크 하려고
		boolean isPrime;
		int primeNumber;

		// 2~100 사이 아니면 다시 입력받게 하려고 반복
		do {
			System.out.print("2~100사이의 정수 입력 :");
			primeNumber = kb.nextInt();

		} while (primeNumber >= 2 != primeNumber <= 100);

		for (int i = 2; i <= primeNumber; i++) {
			isPrime = true;
			for (int j = 2; j < i; j++) {

				if (i % j == 0) {
					// 소수가 아님
					isPrime = false;
				}
			}
			if (isPrime == true) {
				System.out.printf("소수 : %d\n", i);
			}
		}

	}

}