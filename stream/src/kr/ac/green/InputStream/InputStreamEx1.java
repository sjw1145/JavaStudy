package kr.ac.green.InputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
 * InputStream : 바이트 기반 입력 스트림의 최상위 추상 클래스
 * FileInputStream , BufferedInputStream, DataInputStream
 * 
 * InputStream 클래스의 주요 메소드
 * read() , read(byte[] b) , read(byte[] b, int off, int len) , close()
 * 
 * 
 */
public class InputStreamEx1 {
	public static void main(String[] args) {
		InputStream is = null;

		try {
			is = new FileInputStream("test.txt");

			// read() 입력 스트림에서 1바이트를 읽고 4바이트 int 타입으로 리턴
			// 더 이상 읽어 들일 수 없으면 -1 을 리턴한다. (END OF FILE)
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
