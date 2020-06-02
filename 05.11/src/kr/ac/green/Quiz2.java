package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Quiz2 extends JFrame {

	// ���̵�
	private JLabel lbl1;
	private JTextField tf1;
	private JButton btn1;

	// �̸�
	private JLabel lbl2;
	private JTextField tf2;

	// ��й�ȣ
	private JLabel lbl3;
	private JPasswordField tf3;

	// ���Է�
	private JLabel lbl4;
	private JTextField tf4;

	// ����
	private JLabel lbl5;
	private JRadioButton man;
	private JRadioButton girl;

	// �ڱ�Ұ�
	private JLabel lbl6;
	
	// �ؽ�Ʈ Area
	private JTextArea tfArea;
	
	public Quiz2() {
		init();
		setDisplay();
		showFrame();
	}

	private void init() {
		Dimension d = new Dimension(50, 20);
		lbl1 = new JLabel("���̵�");
		tf1 = new JTextField(10);
		tf1.setPreferredSize(d);
		
		lbl2 = new JLabel("�̸�");
		tf2 = new JTextField(10);
		tf2.setPreferredSize(d);

		lbl3 = new JLabel("��й�ȣ");
		tf3 = new JPasswordField(10);
		tf3.setPreferredSize(d);

		lbl4 = new JLabel("���Է�");
		tf4 = new JTextField(10);
		tf4.setPreferredSize(d);

		lbl5 = new JLabel("����");
		man = new JRadioButton("����");
		girl = new JRadioButton("����");

		lbl6 = new JLabel("�ڱ�Ұ�");

		tfArea = new JTextArea(30, 30);
		tfArea.setPreferredSize(d);

		btn1 = new JButton("�ߺ��˻�");
	}

	private void setDisplay() {

		JPanel radioPanel = new JPanel();
		JPanel backGround = new JPanel(new GridLayout(0, 1));
		
		Insets insets = new Insets(20, 20, 20, 20);
		backGround.setBorder(new EmptyBorder(insets));

		JPanel pnlLeft = new JPanel(new GridLayout(0, 2, 5, 5));

		pnlLeft.add(lbl1);
		pnlLeft.add(tf1);
		pnlLeft.add(lbl2);
		pnlLeft.add(tf2);
		pnlLeft.add(lbl3);
		pnlLeft.add(tf3);
		pnlLeft.add(lbl4);
		pnlLeft.add(tf4);
		pnlLeft.add(lbl5);

		ButtonGroup group = new ButtonGroup();

		group.add(man);
		group.add(girl);

		radioPanel.add(man);
		radioPanel.add(girl);

		pnlLeft.add(radioPanel);
		pnlLeft.add(lbl6);

		JPanel pnlSouth = new JPanel();
		JButton sBtn1 = new JButton("����");
		JButton sBtn2 = new JButton("���");

		pnlSouth.add(sBtn1);
		pnlSouth.add(sBtn2);

		backGround.add(pnlLeft);
		
		JPanel areaPanel = new JPanel();
		areaPanel.add(tfArea);

		add(backGround, BorderLayout.NORTH);
		add(areaPanel);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void showFrame() {

		setTitle("ȸ������");
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Quiz2();
	}
}
