package kr.ac.green;

public class ThreadJoinTestA {

	public static void main(String[] args) {
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					// 2초 멈춤
					Thread.sleep(2000);
					
					// 스레드 종료 메세지
					System.out.println("MyThread 종료");
					
					// 3초간 멈춤
					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}

		};
		
		t.start();
		System.out.println("메인 종료");
	}

}
