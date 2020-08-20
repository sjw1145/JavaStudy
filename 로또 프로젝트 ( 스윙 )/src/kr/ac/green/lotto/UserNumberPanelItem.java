package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * 유저의 로또 번호와 로또 결과를 표현하는 클래스
 * 
 */

public class UserNumberPanelItem extends JPanel {
	public static final int NUMBER_IS_ZERO = 0;
	public static final Font textFont = new Font(Font.SERIF, Font.BOLD, 20);

	// 유저의 로또
	private Lotto userLotto;

	private JLabel lblInfo;
	// 로또 번호 출력
	private JLabel lottoNumber;

	// 일치하는 번호 출력
	private JLabel[] sameNumber;

	// 등수 출력
	private JLabel rank;

	public Lotto getUserLotto() {
		return userLotto;
	}

	public UserNumberPanelItem(Lotto userLotto) {
		this.userLotto = userLotto;

		init();
		setDisplay();
		showFrame();
	}

	private void init() {
		lblInfo = new JLabel("로또 번호 : ");
		lblInfo.setFont(textFont);

		lottoNumber = new JLabel(userLotto.getLotto().toString());
		lottoNumber.setFont(textFont);

		addSameNumber();

	}

	private void setDisplay() {
		setLayout(new FlowLayout());

		rank.setFont(textFont);

		Insets insets = new Insets(0, 0, 10, 0);
		setBorder(new EmptyBorder(insets));

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(new LineBorder(Color.BLACK, 1));
		pnl.setSize(new Dimension(200, 200));

		JPanel pnlItem = new JPanel();
		pnlItem.add(lottoNumber);

		if (sameNumber != null) {
			for (JLabel temp : sameNumber) {
				pnlItem.add(temp);
			}
		} else {
			JLabel temp = new JLabel("일치하는 번호 없음");
			temp.setFont(textFont);
			pnlItem.add(temp);
		}

		pnl.add(lblInfo, BorderLayout.WEST);
		pnl.add(pnlItem, BorderLayout.CENTER);
		pnl.add(rank, BorderLayout.EAST);

		add(pnl);
	}

	private void addSameNumber() {
		Integer[] sameNumberTemp = userLotto.getSameNumber();
		if (sameNumberTemp.length != NUMBER_IS_ZERO) {
			sameNumber = new JLabel[sameNumberTemp.length];

			for (int i = 0; i < sameNumber.length; i++) {
				sameNumber[i] = new JLabel(String.valueOf(sameNumberTemp[i]));
				sameNumber[i].setFont(textFont);
				sameNumber[i].setForeground(Color.RED);
			}

			if (userLotto.getResult() == NUMBER_IS_ZERO) {
				rank = new JLabel(String.valueOf("꽝"));
			} else {
				rank = new JLabel(String.valueOf(userLotto.getResult() + " 등"));
			}

		} else {
			sameNumber = null;
			rank = new JLabel("꽝");
		}
	}

	private void showFrame() {
		setVisible(true);
	}

}
