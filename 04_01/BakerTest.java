/*
	������(Baker)�� �ֹ��� �޾� ����(Pizza)�� �����.

	Baker
	======================
	======================
	+ order() : Pizza

	
	Pizza
	======================
	- topping : String
	======================
	+ toString() : String

*/
import java.util.Scanner;
class Pizza {
	private String topping;
	public String getTopping() {
		return topping;
	}
	public void setTopping(String newTopping) {
		topping = newTopping;
	}
	public String toString() {
		return "�̰Ž� " + topping + " �����Դϴ�.";
	}
}
// Baker use-a Pizza
class Baker {
	public Pizza order(String topping) {
		Pizza food = new Pizza();
		food.setTopping(topping);
		return food;
	}
	public Pizza order() {
		Scanner scan = new Scanner(System.in);
		String menu = "1. �Ұ�������\n";
		menu += "2. ���۷δ�����\n";
		menu += "3. ������������\n";
		menu += "- ���ڸ� �����Ͻÿ�";
		menu += "(1~3, �׿� �Է��� �Ұ���� ó���մϴ�.) : ";		
		System.out.print(menu);

		int choice = scan.nextInt();
		String topping;
		switch(choice) {			
			default:
			case 1:
				topping = "�Ұ���";
				break;
			case 2:
				topping = "���۷δ�";
				break;
			case 3:
				topping = "��������";
		}	
		Pizza myPizza = order(topping);
		return myPizza;
		// return order(topping);
	}
}
class BakerTest {
	public static void main(String[] args) {
		Baker b = new Baker();
		
		Pizza p = b.order();
		System.out.println(p);

		System.out.println(b.order("������"));
	}
}