
public class DaemonThreadTest {
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("마이스레드 종료");
				} catch (Exception e) {
				}
			}
		};
		
		/*
		 * 데몬 스레드로 설정
		 * 스레드 객체를 만들고 start 하기 전에 반드시 데몬 스레드를 만들어야 한다.
		 * 
		 */
		t.setDaemon(true);
		t.start();
		
		System.out.println("메인 종료");
	}
}
