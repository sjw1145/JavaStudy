
public class RunningTest {
	
	public static void main(String[] args) {
		/*
		 * �ڽ��� ������ �������� �켱���� ���� �״�� �����޴´�.
		 * ���ν������ �⺻������ 5
		 */
		SomeThread t1 = new SomeThread("A");
		SomeThread t2 = new SomeThread("B");
		SomeThread t3 = new SomeThread("C");
		
		//�������� �켱���� 1 , 5, 10
		//�켱������ ���� �����尡 ���õ� ���ɼ��� ����.
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
}
