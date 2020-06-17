package kr.ac.green;

public class ThreadJoinTestB {

	public static void main(String[] args) {
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					
					System.out.println("마이 스레드 종료");
					
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
			}
			
		};
		
		t.start();
		try {
			// join() t 스레드가 종료될 때 까지 메인 스레드가 기다림
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("메인 종료");
			
	}

}
