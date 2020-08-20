package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LottoTicketFrame extends JFrame {

	public static final char[] str = { 'A', 'B', 'C', 'D', 'E' };
	public static final int CHECKBOX_COUNT_MAX = 6;

	// 로또(패널) 리스트
	private ArrayList<LottoGame> list;

	// 로또 이미지
	private JLabel lblImg;

	// 하단 버튼
	private JButton btnStart;
	private JButton btnReset;
	private JButton btnExit;

	private Loading load;

	public LottoTicketFrame(int result) {

		addLottoPanel(result);

		init();
		setDisplay();
		addEvent();
		showFrame();

	}

	// 로또 패널 추가
	private void addLottoPanel(int result) {

		list = new ArrayList<LottoGame>();

		LottoGame temp;

		for (int i = 0; i < 5; i++) {
			temp = new LottoGame(str[i]);

			if (i >= result) {
				temp.setEnabledFalse();
			}

			list.add(temp);
		}
	}

	private void init() {

		ImageIcon img = new ImageIcon("looto.png");
		Image temp = img.getImage();
		Image newTmp = temp.getScaledInstance(250, 300, Image.SCALE_SMOOTH);
		ImageIcon newImg = new ImageIcon(newTmp);

		lblImg = new JLabel(newImg);
		lblImg.setPreferredSize(new Dimension(250, 300));

		btnStart = new JButton("START");
		btnReset = new JButton("RESET");
		btnExit = new JButton("EXIT");

	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel();

		for (LottoGame temp : list) {
			pnlCenter.add(temp);
		}

		JPanel pnlWest = new JPanel();
		pnlWest.add(lblImg, BorderLayout.CENTER);
		JPanel pnlSouth = new JPanel();

		pnlSouth.add(btnStart);
		pnlSouth.add(btnReset);
		pnlSouth.add(btnExit);

		add(pnlWest, BorderLayout.WEST);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	// 결과값을 ResultNumberPanel에 전달
	private void pushLotto() {
		new LottoResultFrame(new ResultNumberPanel(list));
	}

	// 체크 되었는지 검사하는 메소드
	private boolean lottoCheck() {
		boolean isCheck = true;
		for (LottoGame temp : list) {
			if (temp.getCount() < CHECKBOX_COUNT_MAX) {
				return isCheck;
			}
		}

		return false;
	}

	private void addEvent() {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				try {
					while (i < list.size()) {
						if (list.get(i).getLotto() != null && list.get(i).getIsCheck() != true) {
							throw new LottoCheckError();
						}
						i++;
					}
					lodingmethod();
					pushLotto();
					dispose();

				} catch (LottoCheckError ee) {
					JOptionPane.showMessageDialog(LottoTicketFrame.this, ee.getMessage(), "경고",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		};

		btnStart.addActionListener(actionListener);

		btnReset.addActionListener((e) -> {
			resetbtn();
		});
		btnExit.addActionListener((e) -> {
			System.exit(1);
		});

	}

	public void resetbtn() {
		for (LottoGame lot : list) {
			lot.reset();
		}
	}

	private void showFrame() {
		setTitle("로또 프로그램");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void lodingmethod() {
		load = new Loading(this);
	}
}
