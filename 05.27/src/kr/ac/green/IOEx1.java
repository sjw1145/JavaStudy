package kr.ac.green;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOEx1 {
	public static void main(String[] args) {
		/*
		 * Ű���� �Է��� �о� ����.
		 */

		InputStream is = System.in;
		// enter = \r + \n
		int data = -1;
		try {
			while ((data = is.read()) != -1) {
				System.out.println("���� ���� : " + (char)data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("EOM");
		// OutputStream os = System.out;
	}
}
