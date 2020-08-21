class Car {
	private int speed;
	private int mileage;
	private String color;
	// �ڵ����� �ø��� ��ȣ
	private int id;
	// ��üȭ�� Car ��ü�� ������ ���� ���� ����
	private static int numberOfCars = 0;
	public Car(int s, int m, String c) {
		speed = s;
		mileage = m;
		color = c;
		// �ڵ����� ������ �����ϰ� id ��ȣ�� �Ҵ��Ѵ�.
		id = ++numberOfCars;
	}
	// ���� �޼ҵ� 
	public static int getNumberOfCars() {
		return numberOfCars; // OK!
	}
}
public class CarTest3 {
	public static void main(String args[]) {
		Car c1 = new Car(100, 0, "blue"); 	// ù ��° ������ ȣ��
		Car c2 = new Car(0, 0, "white"); 	// ù ��° ������ ȣ��
		int n = Car.getNumberOfCars();	// ���� �޼ҵ� ȣ��
		System.out.println("���ݱ��� ������ �ڵ��� �� = " + n);
	}
}
