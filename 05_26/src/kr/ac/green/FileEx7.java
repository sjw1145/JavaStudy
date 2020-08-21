package kr.ac.green;

import java.io.File;
import java.io.IOException;

public class FileEx7 {

	public static void main(String[] args) {
		// root ���� ����Ѱ� �ƴ϶� ����η� ...
		File f = new File("..\\..\\a.txt");


		/*
		 * ��� ��� : (���� ��ġ�� �����) .. (��������) , . (������ġ) ���� ��� : c:\study\a\b\a.txt
		 * (������ ��Ʈ ���� ���(��ġ ������ ���� ������ ����))
		 */

		System.out.println(f.getParent());
		System.out.println(f.getName());
		System.out.println(f.getPath());
		System.out.println();
		
		// ù ��° ��� ( ���� ��� ) ���ڿ��� ��������
		// System.out.println(f.getAbsolutePath());

		// 2. ���� ������ ��ü�� ���� ���� �� �ִ�.
		// ��Ű���� ���� �ȵ�
		File other = f.getAbsoluteFile();
		System.out.println(other.getParent());
		System.out.println(other.getName());
		System.out.println(other.getPath());
		System.out.println();
		try {
			// �̻���
			// System.out.println(f.getCanonicalPath());
			File another = f.getCanonicalFile();
			System.out.println(another.getParent());
			System.out.println(another.getName());
			System.out.println(another.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File another = f.getCanonicalFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}