class BankAccount {
	private String owner;
	private int balance;

	public void deposit(int amount) {
		balance += amount;
	}
	public boolean withdraw(int amount) {
		if(amount <= balance) {
			balance -= amount;
			return true;
		} else {
			System.out.println("�ܰ� �����մϴ�.");
			return false;
		}
	}
	public int getBalance() {
		return balance;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String newOwner) {
		owner = newOwner;
	}
	/*
		transaction : �κм����� �������� �ʴ� �������� ���� ����
		do : A -> B -> C -> D
		rollback : �κн��з� ���� ��� ���� �� ���·� �����ϴ� ����
		commit : ��ü ������ �����ؼ� ������ �ݿ�

		�۱� : ���(��������) -> �Ա�(�޴���)

	*/
	public void transfer(int amount, BankAccount otherAccount) {
		System.out.println(
			owner + "�� ���¿��� " + otherAccount.getOwner() + 
			"�� ���·� " + amount + "�� �۱��մϴ�."
		);
		// �Ա���
		if( withdraw(amount) ) {
			// �޴���
			otherAccount.deposit(amount);
			System.out.println("�۱� �Ϸ�");
		}
	}
	public String toString() {
		return owner + "���� �ܰ� : " + balance + "��";
	}
}


class Page195_6 {
	public static void main(String[] args) {
		BankAccount myAccount = new BankAccount();
		myAccount.deposit(10000);
		myAccount.setOwner("������");

		BankAccount yourAccount = new BankAccount();
		yourAccount.deposit(3000);
		yourAccount.setOwner("���ϸ�");

		// �������� ���ϸ����� 2000�� �۱�
		myAccount.transfer(2000, yourAccount);

		System.out.println(myAccount);
		System.out.println(yourAccount);
	}
}
