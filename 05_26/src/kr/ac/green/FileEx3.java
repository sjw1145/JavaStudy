package kr.ac.green;

import java.io.File;

public class FileEx3 {

	public static void main(String[] args) {
		File f = new File("c:\\a\\b\\c\\d");
		
		File dir = new File("c:\\a");
		//���� ���丮�� ��Ÿ�� �� �� ����~
		//���� ��� �ѹ��� ����
		f.mkdirs();
		
		// ���� ��ΰ� ������ �������� �ʴ´�.
		dir.delete();
	}

}