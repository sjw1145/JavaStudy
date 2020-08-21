package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx2 {

	public static void main(String[] args) {
		// exists : ��ũ ���� ���� ���� Ȯ��
		// true : ������ ����
		// false : ���� ����

		File dir = new File("c:\\study");
		if (!dir.exists()) {
			// ���͸��� �������� ������ ��θ� ����
			dir.mkdir();
		}

		// ���� ����
		File f = new File(dir, "a.txt");

		if (f.exists()) {
			f.delete();
			dir.delete();

		} else {
			try {
				// ������ ���� ����
				// ������ ����� ���ؼ� ���͸� ��ΰ� �����ϴ��� Ȯ��
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// ���� ���� ���� Ȯ�� return boolean
		// true ������ �������� ���� , false �� ��������
		// System.out.println(f.exists());

		// ���� ���� -> ������ ���Ϸ� ����

	}
}