package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx5 {
	public static void main(String[] args) {
		File f = new File("c:\\study\\a.txt");
		try {
			f.createNewFile();

			
			System.out.println(f.getParent());

			System.out.println(f.getName());

			System.out.println(f.getPath());

			// ũ��(Byte) �ѱ� 2����Ʈ ���� 1����Ʈ
			System.out.println(f.length());

			// ������ ���� �ð�
			long n = f.lastModified();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}