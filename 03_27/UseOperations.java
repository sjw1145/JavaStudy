/*
	�Է°� : parameter(���� ���� ����)
	��°� : return
			���� :	0(void)
					or 
					1(type)
*/
class Operations {
	// �Ķ���ͷ� ����1���� �ް� 2�� ���� �� ����� ����
	// header
	int plus2(int num) {
		// body			
		int result = num + 2;
		// result�� �����ش�.
		// return�� �޼��尡 ����(��)�ȴٴ� �ǹ̸� ������.
		return result;
	}

	// �������� �Է¹޾� �� ���� �ֿܼ� ����ϴ� �޼��带 �ۼ��Ͻÿ�
	void printSum(int num1, int num2) {
		int result = num1 + num2;
		System.out.println("�� :" + result);
		
	}
	
}
class UseOperations {
	public static void main(String[] args) {
		// ��ü���� -> �������, �޼��带 �޸𸮿� �ø���.
		Operations o = new Operations();
		// int plus2(int num1) { ... }
		o.plus2(100);
		int opResult = o.plus2(10);
		System.out.println(opResult);
		System.out.println(o.plus2(8));

		o.printSum(100, 20);
		o.printSum(5, 4);
		o.printSum(14, 7);

	}
}