package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyMenu extends JFrame {
	private JMenuBar menu;
	private JMenu mFile;

	private JMenu mFeMale;
	private JMenuItem mFeMaleItem1;
	private JMenuItem mFeMaleItem2;
	private JMenuItem mFeMaleItem3;

	private JMenu mMale;
	private JMenuItem mMaleItem1;
	private JMenuItem mMaleItem2;
	private JMenuItem mMaleItem3;

	private JMenuItem mExit;

	private JLabel lblImg;

	public MyMenu() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {
		menu = new JMenuBar();

		mFile = new JMenu("File");

		mFeMale = new JMenu("���ڰ���");
		mFeMaleItem1 = new JMenuItem("������");
		mFeMaleItem2 = new JMenuItem("���ϸ�");
		mFeMaleItem3 = new JMenuItem("û��");

		mMale = new JMenu("���ڰ���");
		mMaleItem1 = new JMenuItem("�ĺ� 1��");
		mMaleItem2 = new JMenuItem("�ĺ� 2��");
		mMaleItem3 = new JMenuItem("�ĺ� 3��");

		mExit = new JMenuItem("Exit");

		lblImg = new JLabel();
	}

	private void setDisplay() {

		mFeMale.add(mFeMaleItem1);
		mFeMale.add(mFeMaleItem2);
		mFeMale.add(mFeMaleItem3);

		mMale.add(mMaleItem1);
		mMale.add(mMaleItem2);
		mMale.add(mMaleItem3);

		mFile.add(mFeMale);
		mFile.add(mMale);
		mFile.add(mExit);

		menu.add(mFile);

		setJMenuBar(menu);

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblImg);

		add(pnlCenter, BorderLayout.CENTER);
	}

	private void addEvent() {
		ActionListener actionListener = (ae) -> {
			String command = ae.getActionCommand();

			if (command.equals("Exit")) {
				System.exit(0);
			}

			showImg(command);

			System.out.println(command);

		};

		mFeMaleItem1.addActionListener(actionListener);
		mFeMaleItem2.addActionListener(actionListener);
		mFeMaleItem3.addActionListener(actionListener);

		mMaleItem1.addActionListener(actionListener);
		mMaleItem2.addActionListener(actionListener);
		mMaleItem3.addActionListener(actionListener);

		mExit.addActionListener(actionListener);

	}

	private void showFrame() {
		setTitle("�޴�");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void showImg(String command) {
		if (command.equals("������")) {
			lblImg.setIcon(new ImageIcon("IU.jpg"));
		} else if (command.equals("���ϸ�")) {
			lblImg.setIcon(new ImageIcon("al.jpg"));
		} else if (command.equals("û��")) {
			lblImg.setIcon(new ImageIcon("ch.jpg"));
		}
	}

	public static void main(String[] args) {
		new MyMenu();
	}
}
