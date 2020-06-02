package kr.ac.green;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyFrame3 extends JFrame{
	
	public MyFrame3() {
		
		//*******�߿��ϴ�*******//
		//Anonymous Inner Class
		/*
		 * 1. ��ü ������ �ѹ� �� �� �� �ִ�( �̸��� ����.. )
		 */
		addWindowListener(new WindowAdapter() {
			//�߰�ȣ ������ Ŭ����
			//WindowAdapter �� ����ϰų� ������ ����
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		
		setTitle("MyFrame3");
		setSize(300,400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyFrame3();
	}

}
