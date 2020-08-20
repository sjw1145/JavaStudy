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
 * �� Ŭ������ �ζ� �� ������ ��Ÿ���� Ŭ���� �Դϴ�.
 * 1 ���� 45 ������ ���ڸ� ������ �� �ִ� üũ�ڽ���
 * �ڵ����ð� ���������� �ִµ� �ڵ� ���� ������ ���ڸ� �����ϴ� ȭ���� ��Ȱ��ȭ
 * 
 */
public class LottoGame extends JPanel {
	public static final int NUMBER_IS_ZERO = 0;
	public static final int LOTTO_NUMBER_MAX_COUNT = 5;
	public static final int CHECKBOX_COUNT_MAX = 6;

	public static final String PRICE = "1,000��";
	public static final int LOTTO_MAXNUM = 45;

	// TOP
	private JLabel lblTopLeft;
	private JLabel lblTopCenter;

	// CENTER ( üũ ��ư�� �� �� )
	private ArrayList<JCheckBox> numberList;

	// SOUTH (�ڵ����� ���) üũ�ڽ�
	private JCheckBox cbAuto;

	// ����� �ζ� ��ȣ ���� ����
	private int count;

	// �ζ� ��ȣ�� ���� Ŭ����
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

		cbAuto = new JCheckBox("�ڵ�����");

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
				// �ڵ������̸� true
				isCheck = true;

				// �ڵ� ������ ��� ��ȣ ����
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
					// �ζ� ��ȣ�� ���� ��
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
					// ī��Ʈ�� 6�̸鼭 üũ�ڽ��� true �� ���
					JOptionPane.showMessageDialog(this, "������ ���ڴ� 6���� �Ѿ� �� �� �����ϴ�.");
					ch.setSelected(false);
				}

			});
		}
	}

	// üũ�ڽ� ����
	private ArrayList<JCheckBox> addNumber() {
		ArrayList<JCheckBox> numbers = new ArrayList<JCheckBox>();

		for (int i = 0; i < LOTTO_MAXNUM; i++) {
			JCheckBox temp = new JCheckBox();
			temp.setText(String.valueOf(i + 1));

			numbers.add(temp);
		}

		return numbers;
	}

	// ���� ��Ȱ��ȭ
	public void setEnabledFalse() {
		cbAuto.setEnabled(false);
		checkBoxOnOff(false);
		lotto = null;
	}

	// ���ǹ�ȣ ����
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

	// üũ�ڽ� ON/OFF
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