package kr.ac.green;

import java.io.File;

public class FileEx4 {
	
	// ��θ� ��Ÿ������ ������ ��Ÿ������ �˾ƾ� ....
	public static void whatIsThis(File f) {
		// ������ ���� �� ���� �ùٸ� ����� �����ش�.
		if(!f.isFile()) {
			System.out.println("������ �ƴ�");
		}
		
		if(!f.isDirectory()) {
			System.out.println("���丮�� �ƴ�");
		}
	}
	
	public static void main(String[] args) {
		File dir = new File("c:\\study");
		dir.mkdir();
		whatIsThis(dir);
	}

}
