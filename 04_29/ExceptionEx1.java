/*
	����ó��
	1. try-catch : ���ܻ�Ȳ�� ����ó��
	2. throws : ����ó�� å���� ȣ���� ������ ����
*/
import java.io.*;
class ExceptionEx1 {
	public static void first() throws FileNotFoundException {
		second();
	}
	public static void second() throws FileNotFoundException {
		FileReader fr = new FileReader("a.txt");
	}
	// �̷� �ڵ�� ����.
	public static void main(String[] args) {
		try {
			first();		
		} catch(FileNotFoundException e) {
		
		}
	}
}