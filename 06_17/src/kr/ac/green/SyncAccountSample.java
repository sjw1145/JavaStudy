package kr.ac.green;

public class SyncAccountSample {

	public static void main(String[] args) {
		final SyncAccount account = new SyncAccount();
		account.deposit(5000);
		
		Runnable withdrawRun = () -> {
			for(int i = 0; i < 10; i++) {
				account.withdraw(500);
			}
		};
		
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		
		t2.start();
		
		
	}

}
