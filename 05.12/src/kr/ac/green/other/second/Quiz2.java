package kr.ac.green.other.second;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Quiz2 extends JFrame{
	public Quiz2() {
		
		JPanel pnlTop = new JPanel();
		JLabel lblTitle = new JLabel("�Ÿ��� ���� ������ �Է��Ͻÿ�.");
		JTextField tfValue = new JTextField(5);
		
		pnlTop.add(lblTitle);
		pnlTop.add(tfValue);
		
		JPanel pnlCenter = new JPanel();
		JButton btnCenter = new JButton("��ȯ");	
		pnlCenter.add(btnCenter);
		
		JPanel pnlSouth = new JPanel();
		JTextField tfSouth = new JTextField(20);
		tfSouth.setEditable(false);
		tfSouth.setBackground(Color.WHITE);
		pnlSouth.add(tfSouth);
		
		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter , BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		setTitle("������ ų�ι��ͷ� ��ȯ");
		pack();
		setSize(350, 150);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void buttonPressed() {
		
	}
	public static void main(String[] args) {
		new Quiz2();
	}

}
