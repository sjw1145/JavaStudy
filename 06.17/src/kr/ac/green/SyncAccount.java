package kr.ac.green;

public class SyncAccount {
	private int balance;

	public synchronized int getBalance() {
		return balance;
	}

	public synchronized void deposit(int val) {
		balance += val;
	}

	public synchronized void withdraw(int val) {
		if (balance >= val) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			balance -= val;
		}
		System.out.println("name : " + Thread.currentThread().getName() + ", balance=" + this.getBalance());

	}
}
