package kr.ac.green;

import java.awt.*;
import javax.swing.*;

public class ComponentEx1 extends JFrame {

	private JLabel lbl;

	public ComponentEx1() {
		lbl = new JLabel(new ImageIcon("img.jpeg"));
		lbl.setText("��Ŀ���� �ͽ����� ���Ŀ V2");
		
		lbl.setHorizontalTextPosition(JLabel.CENTER);
		lbl.setVerticalTextPosition(JLabel.TOP);
		
		lbl.setToolTipText("�̰��� �Ź�~~");
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.BLACK);
		JButton btn1 = new JButton(new ImageIcon("icon.png"));
		
		btn1.setRolloverIcon(new ImageIcon("icon2.png"));

		btn1.setPressedIcon((new ImageIcon("img.jpeg")));
		
		btn1.setText("���߱�");
		btn1.setHorizontalTextPosition(JLabel.CENTER);
		btn1.setVerticalTextPosition(JLabel.BOTTOM);
		
		pnlSouth.add(btn1);
		

		add(lbl, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		setTitle("ComponentEx1");
		pack();
		setSize(400, 300);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComponentEx1();
	}
}