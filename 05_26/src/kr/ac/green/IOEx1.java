package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOEx1 {

	//���Ͽ� ����Ʈ ������ ����
	public static void utilJDK6() {
		// ~ JDK 1.6

		// �ܺ� �ڿ��� �����ϴ� ��
		FileOutputStream fos = null;

		try {
			//appendable
//			fos = new FileOutputStream("c:\\study\\a.txt", true);
			fos = new FileOutputStream("c:\\study\\a.txt");
			// ������ ������ ���� byte ( byte �� ���ԵǸ�... )
			// byte < char < int
			try {
				fos.write('A');
				fos.write('B');
				fos.write('C');
				fos.write('D');
				fos.write('E');
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// if(fos != null) {
				// fos.close();
				// }
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//���� �б�
	public static void utilJDK7() {
		// AutoCloseable ������ �ֵ鸸 �� �� �ִ�.
		try (FileInputStream fis = new FileInputStream("c:\\study\\a.txt")) {
			int data = -1;
			
			while((data = fis.read()) != -1) {
				System.out.println((char)data);
			}
			
//			boolean flag = true;
//			while(flag) {
//				data = fos.read();
//				//read() : EOF(End Of File)�� ������ -1����
//				if(data == -1) {
//					flag = false;
//				} else {
//					System.out.println((char)data);
//				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	// ���Ͽ� ����(byte)
	public static void main(String[] args) {
		utilJDK6();
		utilJDK7();
	}

}
