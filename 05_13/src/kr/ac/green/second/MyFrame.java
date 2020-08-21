package kr.ac.green.second;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * 1. setDefaultCloseOperation(; �ݵ�� ó��
 * 2. �����츮���͸� ������� ���� ������ ����͸� ����� ��
 */
public class MyFrame extends JFrame {

	public MyFrame() {
		WindowListener listener = new MyWindowListener(this);
		addWindowListener(listener);
		
		setTitle("MyFrame");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);

	}

	public void closeWindow() {
		// OK_CANCEL_OPTION -> ��, �ƴϿ�
		// YES_NO_OPTION -> Ȯ��, ���
		// YES_NO_CANCEL_OPTION -> ��, �ƴϿ�, ���
		int result = JOptionPane.showConfirmDialog(this, "�����Ͻ�?", "����", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MyFrame();
	}

}