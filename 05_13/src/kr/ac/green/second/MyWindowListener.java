package kr.ac.green.second;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowListener extends WindowAdapter {
	public static final int NORMAL = 0;

	private MyFrame myframe;
	public MyWindowListener(MyFrame myframe) {
		this.myframe = myframe;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		myframe.closeWindow();
		
		// ���α׷� ����(��� ���� ����, ���� �� ���� ����)
//				System.exit(NORMAL);
	}

}