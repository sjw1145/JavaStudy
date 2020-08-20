package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/*
 * 이 클래스는 로또 한 게임을 나타내는 클래스 입니다.
 * 1 부터 45 까지의 숫자를 선택할 수 있는 체크박스와
 * 자동선택과 수동선택이 있는데 자동 선택 누르면 숫자를 선택하는 화면이 비활성화
 * 
 */
public class LottoGame extends JPanel {
	public static final int NUMBER_IS_ZERO = 0;
	public static final int LOTTO_NUMBER_MAX_COUNT = 5;
	public static final int CHECKBOX_COUNT_MAX = 6;

	public static final String PRICE = "1,000원";
	public static final int LOTTO_MAXNUM = 45;

	// TOP
	private JLabel lblTopLeft;
	private JLabel lblTopCenter;

	// CENTER ( 체크 버튼이 들어갈 곳 )
	private ArrayList<JCheckBox> numberList;

	// SOUTH (자동선택 취소) 체크박스
	private JCheckBox cbAuto;

	// 사용자 로또 번호 선택 갯수
	private int count;

	// 로또 번호를 담을 클래스
	private Lotto lotto;

	private boolean isCheck;

	public LottoGame(char str) {
		lotto = new Lotto(str);

		init(str);
		setDisplay();
		addEvent();
		showFrame();

	}

	public Lotto getLotto() {
		return lotto;
	}

	public int getCount() {
		return count;
	}

	public boolean getIsCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	private void init(char str) {
		setLayout(new BorderLayout());

		isCheck = false;

		numberList = addNumber();

		lblTopLeft = new JLabel(String.valueOf(str), JLabel.LEFT);
		lblTopCenter = new JLabel(PRICE, JLabel.RIGHT);

		cbAuto = new JCheckBox("자동선택");

	}

	private void setDisplay() {
		JPanel pnlTop = new JPanel();
		pnlTop.add(lblTopLeft);
		pnlTop.add(lblTopCenter);

		JPanel pnlCenter = new JPanel(new GridLayout(0, 5));
		for (JCheckBox temp : numberList) {
			pnlCenter.add(temp);
		}

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(cbAuto);

		add(pnlTop, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setBorder(new LineBorder(Color.BLACK, 1));

		setSize(200, 300);
		setVisible(true);
	}

	private void addEvent() {
		LottoProgram lp = new LottoProgram();

		cbAuto.addActionListener((e) -> {

			if (cbAuto.isSelected()) {
				checkBoxOnOff(false);
				if (count != NUMBER_IS_ZERO) {
					lotto.getLotto().clear();
				}
				// 자동선택이면 true
				isCheck = true;

				// 자동 선택인 경우 번호 생성
				lotto.setLotto(lp.numberCreate());
			} else {
				isCheck = false;
				count = NUMBER_IS_ZERO;
				lotto.getLotto().clear();
				checkBoxOnOff(true);
			}
		});

		for (JCheckBox ch : numberList) {
			ch.addActionListener((e) -> {

				JCheckBox temp = (JCheckBox) e.getSource();

				Integer pick = Integer.parseInt(temp.getText());

				if (ch.isSelected() && count <= LOTTO_NUMBER_MAX_COUNT) {

					if (count == LOTTO_NUMBER_MAX_COUNT) {
						isCheck = true;
					}

					ch.setBackground(Color.pink);
					// 로또 번호를 담을 곳
					lotto.addNumber(pick);
					count++;
					System.out.println(isCheck);

				} else if (!ch.isSelected() && count <= CHECKBOX_COUNT_MAX) {
					if (isCheck == true) {
						isCheck = false;
					}

					int n = lotto.indexOf(pick);
					ch.setBackground(null);
					lotto.remove(n);
					count--;
					System.out.println(isCheck);

				} else {
					// 카운트가 6이면서 체크박스가 true 일 경우
					JOptionPane.showMessageDialog(this, "선택한 숫자는 6개를 넘어 설 수 없습니다.");
					ch.setSelected(false);
				}

			});
		}
	}

	// 체크박스 생성
	private ArrayList<JCheckBox> addNumber() {
		ArrayList<JCheckBox> numbers = new ArrayList<JCheckBox>();

		for (int i = 0; i < LOTTO_MAXNUM; i++) {
			JCheckBox temp = new JCheckBox();
			temp.setText(String.valueOf(i + 1));

			numbers.add(temp);
		}

		return numbers;
	}

	// 복권 비활성화
	public void setEnabledFalse() {
		cbAuto.setEnabled(false);
		checkBoxOnOff(false);
		lotto = null;
	}

	// 복권번호 리셋
	public void reset() {
		if (lotto != null && !cbAuto.isSelected()) {
			for (JCheckBox ch : numberList) {
				ch.setSelected(false);
				ch.setBackground(null);
			}
			lotto.getLotto().clear();
			count = NUMBER_IS_ZERO;
		}
	}

	// 체크박스 ON/OFF
	public void checkBoxOnOff(boolean tf) {
		for (JCheckBox ch : numberList) {
			ch.setEnabled(tf);
			if (ch.getBackground() != null) {
				ch.setBackground(null);
				ch.setSelected(false);
			}
		}

	}

}