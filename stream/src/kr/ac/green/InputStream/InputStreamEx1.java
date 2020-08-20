package kr.ac.green.InputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
 * InputStream : ����Ʈ ��� �Է� ��Ʈ���� �ֻ��� �߻� Ŭ����
 * FileInputStream , BufferedInputStream, DataInputStream
 * 
 * InputStream Ŭ������ �ֿ� �޼ҵ�
 * read() , read(byte[] b) , read(byte[] b, int off, int len) , close()
 * 
 * 
 */
public class InputStreamEx1 {
	public static void main(String[] args) {
		InputStream is = null;

		try {
			is = new FileInputStream("test.txt");

			// read() �Է� ��Ʈ������ 1����Ʈ�� �а� 4����Ʈ int Ÿ������ ����
			// �� �̻� �о� ���� �� ������ -1 �� �����Ѵ�. (END OF FILE)
			int result = -1;
			try {
				while((result = is.read()) != -1) {}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
