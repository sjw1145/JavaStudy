package kr.ac.green;

public class UnsafeAccount {
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void deposit(int val) {
		balance += val;
	}

	public void withdraw(int val) {
		if (balance >= val) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			balance -= val;
		}
		System.out.println("³×ÀÓ : " + Thread.currentThread().getName() + ", balance=" + this.getBalance()

		);
	}
}
