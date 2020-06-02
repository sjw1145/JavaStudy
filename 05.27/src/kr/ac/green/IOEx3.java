package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOEx3 {

	public static void consoleToFile(String fileName) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		
		// �� ������ ���°�
		PrintWriter pw = null;
		
		try {
			//ǥ�� �Է�
			is = System.in;
			
			//
			isr = new InputStreamReader(is);
			
			
			br = new BufferedReader(isr);
			
			fw = new FileWriter(fileName);
			
			pw = new PrintWriter(fw);
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				//fw.write(line);
				pw.printf("%s\n", line);
//				pw.write(line + "\r\n");
				
			}
			
			//������ ���� .. (����� ����)
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ڿ� ���� : ������ �������� �ݴ´�.
			MyUtils.closeAll(br, isr, is);
		}
	}
	
	public static void main(String[] args) {
		consoleToFile("console.txt");
	}
}