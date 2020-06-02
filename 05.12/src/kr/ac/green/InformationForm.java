package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InformationForm extends JFrame {
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblRe;
	private JLabel lblGender;
	private JLabel lblHobby;
	private JLabel lblInfo;

	private JTextField tfId;
	private JButton btnChkId;
	private JPasswordField pf;
	private JPasswordField re;

	private JRadioButton rbtnMale;
	private JRadioButton rbtnFemale;

	private JCheckBox chRead;
	private JCheckBox chTravel;
	private JCheckBox chNap;
	private JCheckBox chGame;
	private JCheckBox chSurf;
	private JCheckBox chLate;

	private JTextArea taInfo;

	private JButton btnOk;
	private JButton btnCancel;

	public InformationForm() {
		init();
		setDisplay();
		showFrame();
	}

	private void init() {
		lblId = new JLabel("ID");
		lblPw = new JLabel("PW");
		lblRe = new JLabel("Re");

		lblGender = new JLabel("����");
		lblHobby = new JLabel("���");
		lblInfo = new JLabel("�ڱ�Ұ�");

		tfId = new JTextField(8);
		btnChkId = new JButton("�ߺ�üũ");

		btnChkId.setPreferredSize(new Dimension(60, 20));
		Insets insets = btnChkId.getMargin();
		insets.left = 1;
		insets.right = 1;
		btnChkId.setMargin(insets);

		pf = new JPasswordField(14);
		re = new JPasswordField(14);

		rbtnMale = new JRadioButton("����", true);
		rbtnFemale = new JRadioButton("����");
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		chRead = new JCheckBox("����");
		chTravel = new JCheckBox("����");
		chNap = new JCheckBox("����");
		chGame = new JCheckBox("����");
		chSurf = new JCheckBox("����");
		chLate = new JCheckBox("����");

		taInfo = new JTextArea(8, 20);

		btnOk = new JButton("Ȯ��");
		btnCancel = new JButton("���");

	}

	private void setDisplay() {
		JPanel pnlTop = new JPanel(new BorderLayout());
		JPanel pnlBottom = new JPanel();

		JPanel pnlTWest = new JPanel(new BorderLayout());
		JPanel pnlTCenter = new JPanel(new BorderLayout());

		JPanel pnlTWNorth = new JPanel(new BorderLayout());
		JPanel pnlTWSouth = new JPanel(new GridLayout(0, 1));

		JPanel pnlTWNWest = new JPanel(new GridLayout(0, 1));
		JPanel pnlTWNEast = new JPanel(new GridLayout(0, 1));

		pnlTWNWest.add(lblId);
		pnlTWNWest.add(lblPw);
		pnlTWNWest.add(lblRe);

		JPanel pnlId = new JPanel();
		pnlId.add(tfId);
		pnlId.add(btnChkId);

		pnlTWNEast.add(pnlId);
		
		JPanel pnlPw = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlPw.add(pf);
		pnlTWNEast.add(pnlPw);
		
		JPanel pnlRe = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlRe.add(re);
		
		pnlTWNEast.add(pnlRe);
		pnlTWNorth.add(pnlTWNWest, BorderLayout.WEST);
		pnlTWNorth.add(pnlTWNEast, BorderLayout.EAST);

		JPanel pnlGender = new JPanel(new GridLayout(1, 0));
		pnlGender.add(rbtnMale);
		pnlGender.add(rbtnFemale);

		JPanel pnlHobby1 = new JPanel(new GridLayout(1, 0));
		pnlHobby1.add(chRead);
		pnlHobby1.add(chNap);
		pnlHobby1.add(chTravel);

		JPanel pnlHobby2 = new JPanel(new GridLayout(1, 0));
		pnlHobby2.add(chSurf);
		pnlHobby2.add(chGame);
		pnlHobby2.add(chLate);

		pnlTWSouth.add(lblGender);
		pnlTWSouth.add(pnlGender);
		pnlTWSouth.add(lblHobby);
		pnlTWSouth.add(pnlHobby1);
		pnlTWSouth.add(pnlHobby2);

		pnlTWest.add(pnlTWNorth, BorderLayout.NORTH);
		pnlTWest.add(pnlTWSouth, BorderLayout.SOUTH);

		pnlTCenter.add(lblInfo, BorderLayout.NORTH);
		pnlTCenter.add(new JScrollPane(taInfo), BorderLayout.CENTER);

		pnlTop.add(pnlTWest, BorderLayout.WEST);
		pnlTop.add(pnlTCenter, BorderLayout.CENTER);
		pnlTop.setBorder(new EmptyBorder(10, 10, 10, 10));

		pnlBottom.add(btnOk);
		pnlBottom.add(btnCancel);

		add(pnlTop, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setTitle("InformationForm");
		pack();
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new InformationForm();
	}

}
