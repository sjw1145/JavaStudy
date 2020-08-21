package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Counter extends JFrame implements ActionListener {

	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnDefault;

	public JLabel getLblNum() {
		return lblNum;
	}

	public void setLblNum(JLabel lblNum) {
		this.lblNum = lblNum;
	}

	public Counter() {
		init();
		setDisplay();
		addListeners();
		shomFrame();
	}

	private void init() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

		btnPlus = new JButton("Plus");
		btnMinus = new JButton("Minus");
		btnDefault = new JButton("btnDefault");
	}

	private void setDisplay() {

		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		pnlSouth.add(btnDefault);

		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListeners() {
		btnPlus.addActionListener(this);
		btnMinus.addActionListener(this);
		btnDefault.addActionListener(this);
		
		WindowListener listener = new MyWindowListener2(this);
		addWindowListener(listener);
	}

	private void shomFrame() {
		setTitle("ī����");
		setSize(400, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		/*
		 * ������ �̺�Ʈ�� ���� �̺�Ʈ �ҽ��� �������� �����Ѵ�.
		 */

		// �̺�Ʈ �ҽ��� ����(return Object)
		// � �̺�Ʈ ��ü�� �� �ȴ�..
		// Object src = ae.getSource();
		// if(src == btnPlus) {
		// System.out.println("plus");
		// } else {
		// System.out.println("minus");
		// }

		/*
		 * ��ư�� ������ ���ڿ��� ������ setActionCommand(); �� �̸��� ���� �� �ִ�. 
		 * Action ���� �����Ѵ�.
		 */
		/*
		 * String cmd = ae.getActionCommand(); System.out.println(cmd);
		 */

		int num = 0;
		Object src = ae.getSource();
		if (src != btnDefault) {
			num = Integer.parseInt(getLblNum().getText());

			if (src == btnPlus) {
				num += 1;
				getLblNum().setText(String.valueOf(num));

			} else if (src == btnMinus) {
				num -= 1;
				getLblNum().setText(String.valueOf(num));
			}
		}

		getLblNum().setText(String.valueOf(num));

	}

	public static void main(String[] args) {
		new Counter();
	}

	public void closeWindow() {
		int result = JOptionPane.showConfirmDialog(this, "�����Ͻ�?", "����", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}