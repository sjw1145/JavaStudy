/*
	- �������̵�
		What�� �����ϴ�. How�� �޶����� ��� ����Ŭ��������
		�޼��带 �ٽ� ���� �ϴ� ���
	- ��Ģ
		�޼��� �̸�, �Ķ���� ����, ���� ����
		���������ڴ� �� ������ �� ����.
		(private < ���� < protected < public)

		�����ε�						�������̵�
		1. ���� Ŭ����					1. �������
		2. ���������� �ٸ��޼��� ����	2. ���������� ������ �޼��� ����
*/
class Father {
	// what : ���� �Դ´�. 
	void eat() {
		// how : ������ ���̴�.
		System.out.println("���������� �ȳ�");
	}
	public void todo(int n, String str, char c, long l, boolean flag, double d) {
	}
}

class Son extends Father {
	@Override
	protected void eat() {
		System.out.println("�޼����� �ȳ�");
	}
	// Annotation(JDK1.5~)
	// �Ʒ� �޼��尡 ����� �������̵� �Ǿ����� ������ Ÿ�ӿ� üũ�϶�.
	/*
	@Override
	public void todo(int n, String str, char c, long l,  double d, boolean flag) {
	}
	*/
}
class Ex2 {
	public static void main(String[] args) {
		Son s = new Son();
		s.eat();
	}
}
