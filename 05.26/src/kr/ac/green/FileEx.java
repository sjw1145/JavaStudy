package kr.ac.green;

import java.io.File;

/*
 * java.io.File
 * 		============================================
 * 		����
 * 
 * 		(2���� �� Ʋ� �����̶�� ��)
 * 		1. ����
 * 		2. ���丮(���� , ���) -> �ĺ���
 * 		============================================
 * 		��ũ ���� ���� ����
 * 
 * 		1. ���� ����
 * 			- �ڵ� �󿡸� �����ϴ� ����
 * 		2. ������ ����
 * 			- ���� ��ũ�� �����ϴ� �͵�
 */
public class FileEx {

	public static void main(String[] args) {
		// target ( c:\study\a.txt )

		// f1, f2, f3 �� ���� ������ ��Ÿ��
		File f1 = new File("c:\\study\\a.txt");
		File f2 = new File("c:\\study", "a.txt");
		// File �� ��θ� ��Ÿ�� �� �ִ�.
				File dir = new File("c:\\study");
		File f3 = new File(dir, "a.txt");
		

		
	}
}