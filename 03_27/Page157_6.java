class Stock {
	String no;		// ��ǰ��ȣ
	int amount;		// ��ǰ����
	/*
		������ ����
		count : ������ų ����
	*/
	void increase(int count) {
		if(count > 0) {
			amount += count;
			printAmount();
		} else {
			System.out.println("���������� 0 �Ǵ� ������ �� �����ϴ�.");
		}
	}
	/*
		������ ����
		count : ���ҽ�ų ����
	*/
	void decrease(int count) {
		if(count<=amount) {
			// amount = amount - count;
			amount -= count;
			printAmount();
		} else {
			System.out.println("�������� �����մϴ�.");
		}		
	}
	// ��������� ���
	void printAmount() {
		System.out.println(no + "�� ������ : " + amount);
	}
}
class Page157_6 {
	public static void main(String[] args) {
		Stock s = new Stock();
		// ��ǰ��ȣ ����		
		s.no = "p00001";
		// ������ ����
		s.amount = 100;

		s.increase(-30);
		
		s.decrease(150);
	}
}
