package kr.ac.green;

public class ThreadJoinTestA {

	public static void main(String[] args) {
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					// 2�� ����
					Thread.sleep(2000);
					
					// ������ ���� �޼���
					System.out.println("MyThread ����");
					
					// 3�ʰ� ����
					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}

		};
		
		t.start();
		System.out.println("���� ����");
	}

}
