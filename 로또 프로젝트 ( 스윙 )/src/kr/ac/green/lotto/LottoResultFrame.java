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

	// 구매 복권 당첨결과
	private JLabel lblTopText;

	// 하단 버튼
	private JButton btnClose;
	private JButton btnChance;

	// 결과 패널
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

		lblTopText = new JLabel("구매복권 당첨 결과", JLabel.CENTER);

		lblTopText.setOpaque(true);
		lblTopText.setBackground(color);
		lblTopText.setForeground(Color.WHITE);
		lblTopText.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

		btnClose = new JButton("CLOSE");
		btnChance = new JButton("2등이 될 확률 ?");

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
				JOptionPane.showMessageDialog(LottoResultFrame.this, result, "알림", JOptionPane.INFORMATION_MESSAGE);
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
		setTitle("로또 당첨 결과");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
