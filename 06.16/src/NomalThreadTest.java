
public class NomalThreadTest {
	/*
	 *  ��� �����尡 ���� -> ���α׷� ����
	 */
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("MyThread ����");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		t.start();
		
		System.out.println("main() ����");
		
	}
}
