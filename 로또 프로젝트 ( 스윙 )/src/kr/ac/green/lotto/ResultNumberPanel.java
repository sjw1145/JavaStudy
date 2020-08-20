package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ResultNumberPanel extends JPanel {
	public static final Font LBL_FONT = new Font(Font.DIALOG, Font.BOLD, 40);
	public static final Color color = new Color(0, 84, 255);

	public static final int LOTTO_NUMBER_MAX = 5;
	public static final int BONUS_NUMBER_INDEX = 6;

	private JLabel lblText;

	private LottoProgram program;

	// ��÷��ȣ�� �޾� ���̺��� ����
	private ArrayList<JLabel> resultNumber;

	// ������� �ζ� ��ȣ�� ���� �÷���
	private ArrayList<Lotto> userLotto;

	public ArrayList<Lotto> getUserLotto() {
		return userLotto;
	}

	public void setUserLotto(ArrayList<Lotto> userLotto) {
		this.userLotto = userLotto;
	}

	public ResultNumberPanel(ArrayList<LottoGame> list) {
		this();

		userLotto = new ArrayList<Lotto>();
		for (LottoGame temp : list) {
			if (temp.getLotto() != null) {
				userLotto.add(temp.getLotto());
			}
		}

		// ������� �ζǸ� �޾Ƽ� ����� ó���ϴ� �κ�
		program.lottoResult(userLotto);
	}

	public ResultNumberPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(580, 180));
		setBorder(new LineBorder(color, 2));
		setBackground(Color.WHITE);

		init();

		setDisplay();

	}

	private void init() {
		program = new LottoProgram();
		lblText = new JLabel("��÷��ȣ", JLabel.CENTER);
		lblText.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

		// ��÷��ȣ ����
		program.createResultNumber();

		// ��÷��ȣ ���̺� ���� ó��
		resultNumber = createLabel(program.getResultNumber());

	}

	private void setDisplay() {
		// ��÷��ȣ�� ���� �г� (FlowLayout)
		JPanel resultNumberPanel = new JPanel();
		resultNumberPanel.setBackground(Color.WHITE);

		for (JLabel temp : resultNumber) {
			resultNumberPanel.add(temp);
		}

		add(lblText, BorderLayout.NORTH);

		add(resultNumberPanel, BorderLayout.CENTER);
	}

	// �ζ� ��ȣ�� ��Ÿ���� ���̺� �����ϴ� �޼ҵ�
	private ArrayList<JLabel> createLabel(Lotto resultLotto) {

		ArrayList<JLabel> temp = new ArrayList<JLabel>();
		ArrayList<Integer> number = resultLotto.getLotto();

		int i = 0;
		while (i < number.size()) {
			JLabel lblTemp = new JLabel();
			lblTemp.setText(String.valueOf(number.get(i)));
			lblTemp.setFont(LBL_FONT);
			lblTemp.setOpaque(true);
			lblTemp.setBackground(Color.YELLOW);

			if (i == 6) {
				lblTemp.setBackground(Color.ORANGE);
			}
			lblTemp.setBorder(new LineBorder(Color.BLACK, 1));

			temp.add(lblTemp);
			i++;
		}

		return temp;
	}

}
