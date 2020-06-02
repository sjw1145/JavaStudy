package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Game extends JFrame {
	public static final int MUK = 0;
	public static final int ZZI = 1;
	public static final int BBA = 2;

	private String[] str = { "��", "��", "��" };

	private JButton btnStart;
	private JTextArea gameState;

	private JRadioButton muk;
	private JRadioButton zzi;
	private JRadioButton bba;

	private String userChoice;
	private String comChoice;

	public Game() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {

		btnStart = new JButton("����");

		muk = new JRadioButton("��");
		zzi = new JRadioButton("��");
		bba = new JRadioButton("��");

		ButtonGroup radioGroup = new ButtonGroup();

		radioGroup.add(muk);
		radioGroup.add(zzi);
		radioGroup.add(bba);

		gameState = new JTextArea(4, 20);

		gameState.setBorder(new LineBorder(Color.black, 1));
		gameState.setEnabled(false);
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel();

		pnlNorth.add(muk);
		pnlNorth.add(zzi);
		pnlNorth.add(bba);

		JPanel pnlCenter = new JPanel();

		pnlCenter.add(btnStart);

		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(gameState);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addEvent() {

		// ������ư ����
		ActionListener actionListener = (e) -> {
			randomChoice();

			JRadioButton temp = (JRadioButton) e.getSource();
			userChoice = temp.getText();
		};

		muk.addActionListener(actionListener);
		zzi.addActionListener(actionListener);
		bba.addActionListener(actionListener);

		// ���� ����
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (gameState != null) {
					gameState.setText("");
				}

				if (userChoice != null) {
					String result = gameStart();

					// ���� �����ϰ� �ؽ�ƮArea �� ���
					gameState.append("��ǻ�� : " + comChoice + "\n");
					gameState.append("����� : " + userChoice + "\n");
					gameState.append("�� �� : " + result);
				} else {
					JOptionPane.showMessageDialog(Game.this, "��ư�� �����ϼ�");
				}
			}
		});
	}

	private void showFrame() {
		setTitle("�����");
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void randomChoice() {
		int num = (int) (Math.random() * 3);
		comChoice = str[num];
	}

	private String gameStart() {
		
		String result = "";
		
		if (userChoice.equals(str[BBA])) {

			if (comChoice.equals(str[MUK])) {
				result = "�̱�";
			} else if (comChoice.equals(str[ZZI])) {
				result = "����";
			} else {
				result = "���";
			}

		} else if (userChoice.equals(str[MUK])) {

			if (comChoice.equals(str[ZZI])) {
				result = "�̱�";
			} else if (comChoice.equals(str[BBA])) {
				result = "����";
			} else {
				result = "���";
			}

		} else {
			if (comChoice.equals(str[BBA])) {
				result = "�̱�";
			} else if (comChoice.equals(str[MUK])) {
				result = "����";
			} else {
				result = "���";
			}
		}

		return result;
	}

	public static void main(String[] args) {
		new Game();
	}
}