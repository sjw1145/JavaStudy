package kr.ac.green;
/*
 * byte : �Ϲ����� ���� 
 * char : �а� ���� �����Ͱ� ������ �ؽ�Ʈ �̰�, ��� �ƴ� ������ ���
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharIO {

	public static void writeToFile() {
		FileWriter fw = null;

		try {
			fw = new FileWriter("data.txt");
			fw.write("����~");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(fw);
		}
	}
	
	public static void readFromFile() {
		FileReader fr = null;
		
		try {
			fr = new FileReader("data.txt");
			int data = -1; //char
			
			while((data = fr.read()) != -1 ) {
				System.out.println((char)data);
			}
		} catch (IOException e) {
		} finally {
			MyUtils.closeAll(fr);
		}
	}

	public static void main(String[] args) {
		writeToFile();
		readFromFile();
	}
}
