package kr.ac.green.other;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Quiz2 extends JFrame implements IFunction {
	public final static double MILE = 1.6;
	private JTextField tfValue;
	private JTextField tfSouth;
	private JButton btnCenter;

	public void init() {
		JPanel pnlTop = new JPanel();
		JLabel lblTitle = new JLabel("�Ÿ��� ���� ������ �Է��Ͻÿ�.");
		tfValue = new JTextField(5);

		pnlTop.add(lblTitle);
		pnlTop.add(tfValue);

		JPanel pnlCenter = new JPanel();
		btnCenter = new JButton("��ȯ");
		pnlCenter.add(btnCenter);

		JPanel pnlSouth = new JPanel();
		tfSouth = new JTextField(20);
		tfSouth.setEditable(false);
		tfSouth.setBackground(Color.WHITE);
		pnlSouth.add(tfSouth);

		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		setTitle("������ ų�ι��ͷ� ��ȯ");
		pack();
		setSize(350, 150);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

	}

	public Quiz2() {
		init();
		addListeners();

	}

	private void addListeners() {
		MyActionListener4 m = new MyActionListener4(this);
		btnCenter.addActionListener(m);
	}

	public static void main(String[] args) {
		new Quiz2();
	}

	@Override
	public void change() {
		String msg = "";
		try {
			double mile = Double.parseDouble(tfValue.getText());
			double km = mile * 1.6;
			msg = km + "km";

		} catch (NumberFormatException e) {
			msg = "���ڸ� �Է�";

			/*
			 * ��밡���� ������ �÷��� 
			 * JOptionPane.ERROR_MESSAGE
			 * JOptionPane.WARNING_MESSAGE 
			 * JOptionPane.INFORMATION_MESSAGE
			 * JOptionPane.PLAIN_MESSAGE 
			 * JOptionPane.QUESTION_MESSAGE
			 */
			JOptionPane.showMessageDialog(
					null, // �θ�â
					msg // �޽���
//					"���", // Ÿ��Ʋ
//					JOptionPane.QUESTION_MESSAGE // ������
			);
		} finally {
			// Ŀ�� ����
			tfValue.requestFocus();
			// �巡��
			tfValue.selectAll();
		}
		tfSouth.setText(msg);
	}
}