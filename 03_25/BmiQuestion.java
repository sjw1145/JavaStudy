import java.util.Scanner;

/*
사용자로부터 키를 입력받아서, 표준 체중을 계산한 후에, 사용자의 체중과 비교하여 저체중인지,
표준인지, 과체중인지를 판단하는 프로그램을 작성해라.

표준체중 = (키 - 100) * 0.9
비만도(%)=(현 자신의 체중*100/표준체중*100)*100

100~110% 정상
110~120% 과체중
120% 이상 비만
 */
public class BmiQuestion {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);

		// 표준체중
		System.out.print("키를 입력하시오 : ");
		double stature = kb.nextDouble();
		stature = (stature - 100) * 0.9;

		// 사용자 입력
		System.out.print("당신의 몸무게를 입력하시오 : ");
		double weight = kb.nextDouble();
		weight = (stature * 100) / (weight * 100) * 100;

		if (weight >= 100 && weight <= 109) {
			System.out.println("정상 체중.");
		} else if (weight >= 110 && weight <= 119) {
			System.out.println("과체중.");
		} else if (weight <= 120) {
			System.out.println("비만");
		} else {
			System.out.println("저체중");
		}

	}

}