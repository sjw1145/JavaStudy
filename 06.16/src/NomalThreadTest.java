
public class NomalThreadTest {
	/*
	 *  모든 스레드가 종료 -> 프로그램 종료
	 */
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("MyThread 종료");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		t.start();
		
		System.out.println("main() 종료");
		
	}
}
