package kr.ac.green;

public class ThreadEx13 {
	static long startTime = 0;

	public static void main(String[] args) {
		ThreadEx13_1 th1 = new ThreadEx13_1();
		ThreadEx13_2 th2 = new ThreadEx13_2();

		th1.start();
		th2.start();

		startTime = System.currentTimeMillis();

		try {
			// 1���� ��ٸ��� ���� 2���� ���ᰡ �Ǿ����� �ƹ� �ǹ� ����
			// 1���� ��ٸ��ٰ� 1���� ������ 2���� ���ϰ� ���� �� 2���� ��ٸ���.
			// 2���� �۾��� ������ �ִ� ����
			// �����δ� �̷��� ���� �� ��
			th1.join();
			// th2.join();
		} catch (InterruptedException e) {
			System.out.println("������1 ����Ұ�����");
		}

		try {
			th2.join();
		} catch (InterruptedException e) {
			System.out.println("������2 ����Ұ�����");
		}

		System.out.println("�ҿ�ð� : " + (System.currentTimeMillis() - ThreadEx13.startTime));
		
		System.out.println("���� ������ ����");
	}
}
