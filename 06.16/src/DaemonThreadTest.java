
public class DaemonThreadTest {
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("���̽����� ����");
				} catch (Exception e) {
				}
			}
		};
		
		/*
		 * ���� ������� ����
		 * ������ ��ü�� ����� start �ϱ� ���� �ݵ�� ���� �����带 ������ �Ѵ�.
		 * 
		 */
		t.setDaemon(true);
		t.start();
		
		System.out.println("���� ����");
	}
}
