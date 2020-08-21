/*
	���� �� ���� -> ����(Exception)

	- ����ó��
		-> ���ܰ� �߻��ϸ� �� �ؾ��ϴ��� �˷��ش�.
*/
class ExceptionEx1 {

	public static void divide(int num1, int num2) {
		try {
			// ���ܹ߻� ���ɼ��� �ִ� ����
			int result = num1 / num2;
			System.out.println(result);
		} catch(ArithmeticException e) {
			// ArithmeticException ���ܰ� �߻��ϸ� �� ��
			System.out.println("0���� ������ �����ϴ�.");
		}		
	}

	public static void main(String[] args)	{
		divide(8, 2);
		divide(8, 0);
		System.out.println("end of main");
	}
}




















