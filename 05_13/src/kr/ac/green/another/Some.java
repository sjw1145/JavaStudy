package kr.ac.green.another;

/*
 * 1. �ܺ�Ŭ���� ��ü ������ ������� ���� ����
 */
public class Some {
	//static ������ static Ŭ�������� ��� ������.
	private int num = 100;
	
	public void call() {
		new Other().print();
		
		Other.todo();
	}
	
	// 3 ��° ����
	// �ܺ� Ŭ������ ��������� �̳� Ŭ������ ����� �� �ִٴ� ��Ģ�� ����...
	// �� �Ⱦ� ... �ֵ� .....
	
	static class Other {
		// �ܺ� Ŭ���� ��ü ������ ������� ��ü ������ ����.
		public void print() {
			//System.out.println(num);
		}
		
		public static void todo() {
			System.out.println();
		}
		

	}
	
	public static void main(String[] args) {
		Some.Other o = new Some.Other();
	}
}
