package kr.ac.green;

public class PasswordCheckException extends Exception {
	public PasswordCheckException() {
		super("��й�ȣ�� ���� �޶��");
	}
	
	public PasswordCheckException(int num) {
		super("��й�ȣ �Է��ϼ���");
	}
}
