package kr.ac.green;

public class IdCheckException extends Exception{
	public IdCheckException() {
		super("���̵�� 6~12�ڷ� �Է��ϼ���");
	}
	
	public IdCheckException(String id) {
		super(id+"�� �̹� ��� �� �Դϴ�.");
	}
	
	public IdCheckException(String id, String pw) {
		super("���̵�� ��й�ȣ�� �ٽ� Ȯ�� �ϼ���.");
	}
}
