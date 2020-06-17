package kr.ac.green;

public class UnsafeAccountSample {
	public static void main(String[] args) {
		// 1.7 까지는 final 붙여야 됨
		final UnsafeAccount account = new UnsafeAccount();
		
		account.deposit(5000);
		
		Runnable withdrawRun = () -> {
			for(int i = 0; i<10; i++) {
				account.withdraw(500);
			}
		};
		
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		
		t1.start();
		t2.start();
	}
}