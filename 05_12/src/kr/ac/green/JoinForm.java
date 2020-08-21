package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class JoinForm extends JFrame {
	public static final int INPUT_SIZE = 10;
	public static final int LABEL_WIDTH = 50;

	private JButton btnCheck;
	private JRadioButton rbtnMale;
	private JRadioButton rbtnFemale;
	private JTextArea taIntoduce;
	private JButton btnJoin;
	private JButton btnCancel;

	private Dimension sizeOfLabel = new Dimension(100, 15);

	public JoinForm() {
		init();
		setDisplay();
		showFrame();
	}

	public void init() {
		btnCheck = new JButton("�ߺ��˻�");
		rbtnMale = new JRadioButton("����");
		rbtnFemale = new JRadioButton("����", true);

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		taIntoduce = new JTextArea(4, 30);
		btnJoin = new JButton("����");
		btnCancel = new JButton("���");
	}

	public void setDisplay() {

		JPanel pnlMain = new JPanel(new BorderLayout());

		JPanel pnlNorth = new JPanel(new GridLayout(0, 1));
		JPanel pnlId = getPanel("���̵�");
		pnlId.add(btnCheck);
		JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGender = new JLabel("����", JLabel.LEFT);
		lblGender.setPreferredSize(sizeOfLabel);
		pnlGender.add(lblGender);
		pnlGender.add(rbtnMale);
		pnlGender.add(rbtnFemale);

		JPanel pnlIntroduce = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblIntroduce = new JLabel("�ڱ�Ұ�", JLabel.LEFT);

		lblIntroduce.setPreferredSize(sizeOfLabel);
		pnlIntroduce.add(lblIntroduce);

		pnlNorth.add(pnlId);
		pnlNorth.add(getPanel("�̸�"));
		pnlNorth.add(getPanel("��й�ȣ", true));
		pnlNorth.add(getPanel("���Է�", true));
		pnlNorth.add(pnlGender);
		pnlNorth.add(pnlIntroduce);

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(new JScrollPane(taIntoduce));

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnJoin);
		pnlSouth.add(btnCancel);

		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(pnlMain, BorderLayout.CENTER);
	}

	public void showFrame() {
		setTitle("ȸ������");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private JPanel getPanel(String text) {
		return getPanel(text, false);
	}

	private JPanel getPanel(String text, boolean isPassword) {
		JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lbl = new JLabel(text, JLabel.LEFT);
		
		JTextComponent input;
		if (isPassword) {
			input = new JPasswordField(INPUT_SIZE);
		} else {
			input = new JTextField(INPUT_SIZE);
		}

		lbl.setPreferredSize(sizeOfLabel);
		pnl.add(lbl);
		pnl.add(input);
		return pnl;
	}

	public static void main(String[] args) {
		new JoinForm();
	}

}
