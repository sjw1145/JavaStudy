package kr.ac.green;

public class NameCheckException extends Exception {
	public NameCheckException() {
		super("�̸��� �ѱ۸� ������");
	}

	public NameCheckException(int num) {
		super("�̸��� " + num + "���� �̻� �ѱ۷� �Է��� ��");
	}
}
