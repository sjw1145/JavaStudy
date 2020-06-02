package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/*
 * �޴��� ��ġ�� ����� �ƴ� . �⺻ ��ġ�� ��ӵǾ� ���� 
 * 
 * �޴� �������
 * 1. JMenuBar : JMenu�� ������ �� �ִ� Component
 * 2. JMenu : ���(�ڽ��� �����ϴ� �޴� �������� �����ش�)
 * 3. JMenuItem , JRadioButtonMenuItem, JCheckBoxMenuItem : ����� ����
 */

public class MenuEx extends JFrame {

	private JMenuBar mBar;
	
	private JMenu mFile;
	private JMenuItem miOpen;
	private JMenuItem miSave;
	private JMenuItem miExit;

	private JMenu mOption;
	private JRadioButtonMenuItem rbmiOptionA;
	private JRadioButtonMenuItem rbmiOptionB;

	private JLabel lblMain;
	private JButton btnExit;

	public MenuEx() {
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {
		// //������Ʈ �����ϱ� ���� �ؾ���......... �� ���� ����
		// try {
		// String lookNFeelName = UIManager.getSystemLookAndFeelClassName();
		// UIManager.setLookAndFeel(lookNFeelName);
		//
		// } catch (Exception e) {
		//
		// }

		lblMain = new JLabel("Start", JLabel.CENTER);
		lblMain.setFont(new Font(Font.DIALOG, Font.BOLD, 40));

		btnExit = new JButton("Exit");
		// alt
		btnExit.setMnemonic('X');
		btnExit.setToolTipText("alt + x");

		mBar = new JMenuBar();

		mFile = new JMenu("File");
		miOpen = new JMenuItem("Open", 'O');
		// miOpen.setIcon(new ImageIcon("open.png"));

		// ctrl + s
		// Ű ������ ��Ÿ���� ��ü
		KeyStroke keys = KeyStroke.getKeyStroke(
				// keyChar , modifiers(����Ű)
				KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);

		miSave = new JMenuItem("Save");
		miSave.setMnemonic('S');
		miSave.setAccelerator(keys);

		// miSave.setIcon(new ImageIcon("save.png"));
		miExit = new JMenuItem("Exit");
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));

		mOption = new JMenu("Option");
		rbmiOptionA = new JRadioButtonMenuItem("Option A", true);
		rbmiOptionB = new JRadioButtonMenuItem("Option B");

		ButtonGroup group = new ButtonGroup();
		group.add(rbmiOptionA);
		group.add(rbmiOptionB);

	}

	private void setDisplay() {
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnExit);

		add(lblMain, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// �޴� ����
		mOption.add(rbmiOptionA);
		mOption.add(rbmiOptionB);

		mFile.add(miOpen);
		mFile.add(miSave);

		mFile.addSeparator();
		mFile.add(mOption);
		// ��輱
		mFile.addSeparator();
		mFile.add(miExit);

		mBar.add(mFile);

		JMenu mEtc = new JMenu("ETC");

		JMenuItem miInfo = mEtc.add("about swing");
		JMenuItem miHelp = mEtc.add("help");

		mBar.add(mEtc);

		// �޴��� ��� ����� �ƴ�
		setJMenuBar(mBar);
	}

	private void addListener() {
		ActionListener listener = (e) -> {
			String command = e.getActionCommand();
			// lblMain.setText(e.getActionCommand());
			if (command.equalsIgnoreCase("Exit")) {
				closeWindow();
			} else {
				lblMain.setText(command);
			}

		};

		miOpen.addActionListener(listener);
		miSave.addActionListener(listener);
		miExit.addActionListener(listener);
		btnExit.addActionListener(listener);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				closeWindow();
			}
		});
	}

	private void closeWindow() {
		JOptionPane.showMessageDialog(this, "����", "Exit", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	private void showFrame() {
		setTitle("�޴�");
		setSize(300, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MenuEx();
	}
}