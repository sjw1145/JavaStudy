package kr.ac.green;

public class ThreadJoinTestB {

	public static void main(String[] args) {
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					
					System.out.println("���� ������ ����");
					
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
			}
			
		};
		
		t.start();
		try {
			// join() t �����尡 ����� �� ���� ���� �����尡 ��ٸ�
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("���� ����");
			
	}

}
