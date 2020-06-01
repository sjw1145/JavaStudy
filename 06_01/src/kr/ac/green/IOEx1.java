package kr.ac.green;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class IOEx1 {
	
	public static void save() {
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream("data.dat");
			dos = new DataOutputStream(fos);
			Random r = new Random();
			int num = r.nextInt(50) + 3;
			for(int i=0; i<num; i++) {
				dos.writeInt(3);
				dos.writeDouble(3.14);
				dos.writeBoolean(false);
				dos.writeUTF("abc\ndef");
				dos.writeUTF("��\n��");
			}
			// ���ڿ� ����
			dos.flush();
			System.out.println(num + "�� ����");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(dos, fos);
		}
	}
	public static void load() {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream("data.dat");
			dis = new DataInputStream(fis);
			/*
			 * �ݵ�� write �� ������� �о�;��Ѵ�.
			 */
			int count = 1;
			// available() : ���� ���� ���� ����Ʈ ���� ����			
			while(dis.available() > 0) {
				System.out.println("===========" + count + "===========");
				int n = dis.readInt();
				double d = dis.readDouble();
				boolean b = dis.readBoolean();
				String str1 = dis.readUTF();
				String str2 = dis.readUTF();
				System.out.println(n);
				System.out.println(d);
				System.out.println(b);
				System.out.println(str1);
				System.out.println(str2);
				count++;
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(dis, fis);
		}
	} 
	public static void main(String[] args) {
		save();
		load();
	}
}










