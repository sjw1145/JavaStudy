/*
	static
		- ��ü�� �ƹ� ������ ����.(�޼���, ����)
		- �޸𸮿� ���۰� ���ÿ� �ö󰣴�.(����Ȯ��...��ư �̷��� �˰��־��~)
		- ���۰� ���ÿ� �ٷ� ����� �� �ִ�.
		- � Ŭ������ �����ص� �ƹ��� ������ ����.(ex: main)
		- ��ü���� �������� ���� �� �� ����.
		- ��������, �Լ��� ǥ�� ������ ���ȴ�.
*/



class Stone {
	static void think() {
	}
}
class StaticEx1 {
	static int other = 100;
	int num = 3;

	static void todo() {
		System.out.println("todo");
	}

	public static void main(String[] args) {
		System.out.println(other);
		todo();
	}
}
