package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LottoResultFrame extends JFrame {
	public static final Color color = new Color(0, 84, 255);

	private LottoProgram lp;

	// ���� ���� ��÷���
	private JLabel lblTopText;

	// �ϴ� ��ư
	private JButton btnClose;
	private JButton btnChance;

	// ��� �г�
	private ResultNumberPanel resultNumberpnl;
	private UserNumberPanel resultUserPnl;

	public LottoResultFrame(ResultNumberPanel resultNumberpnl) {
		lp = new LottoProgram();
		this.resultNumberpnl = resultNumberpnl;

		init();
		setDisplay();
		addEvent();
		showFrame();

	}

	private void init() {

		lblTopText = new JLabel("���ź��� ��÷ ���", JLabel.CENTER);

		lblTopText.setOpaque(true);
		lblTopText.setBackground(color);
		lblTopText.setForeground(Color.WHITE);
		lblTopText.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

		btnClose = new JButton("CLOSE");
		btnChance = new JButton("2���� �� Ȯ�� ?");

	}

	private void addEvent() {
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnChance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = lp.rankChance(resultUserPnl.getItemList());
				JOptionPane.showMessageDialog(LottoResultFrame.this, result, "�˸�", JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

	private void setDisplay() {

		JPanel pnlNorth = new JPanel(new BorderLayout());
		JPanel pnlNorthN = new JPanel();
		JPanel pnlNorthS = new JPanel();

		pnlNorthN.add(lblTopText);
		pnlNorthS.add(resultNumberpnl);

		pnlNorth.add(pnlNorthN, BorderLayout.NORTH);
		pnlNorth.add(pnlNorthS, BorderLayout.SOUTH);

		//===================================
		JPanel pnlCenter = new JPanel();

		resultUserPnl = new UserNumberPanel(resultNumberpnl.getUserLotto());
		UserNumberPanel pnlCenterN = resultUserPnl;

		pnlCenter.add(pnlCenterN);
//===========================================
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnClose);
		pnlSouth.add(btnChance);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setTitle("�ζ� ��÷ ���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
