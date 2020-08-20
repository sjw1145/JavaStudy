package myLotto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LottoGame extends JPanel {
	public static final int NUMBER_IS_ZERO = 0;
	public static final int LOTTO_NUMBER_MAX_COUNT = 5;
	public static final int CHECKBOX_COUNT_MAX = 6;
	
	public static final String PRICE = "1,000원";
	public static final int LOTTO_MAXIMUM = 45;
	
	//TOP
	private JLabel lblTopLeft;
	private JLabel lblTopCenter;
	
	//CENTER (체크 버튼이 들어갈 곳)
	private ArrayList<JCheckBox> numberList;
	
	//SOUTH (자동선택 / 취소) 체크박스
	private JCheckBox cbAuto;
	
	//사용자 로또 번호 선택 갯수
	private int count;
	
	//로또번호를 담을 클래스
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
	
	//===========================================
	
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
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,5));
		for (JCheckBox temp)
		
	}

	private void addEvent() {
		// TODO Auto-generated method stub
		
	}


	private void showFrame() {
		// TODO Auto-generated method stub
		
	}

	
	
	//체크박스 45개 생성
	private ArrayList<JCheckBox> addNumber() {
		ArrayList<JCheckBox> numbers = new ArrayList<JCheckBox>();
		
		for(int i = 0; i < LOTTO_MAXIMUM; i++) {
			JCheckBox temp = new JCheckBox();
			temp.setText(String.valueOf(i+1));
			
			numbers.add(temp);
		}
		return numbers;
	}
	

	//복권 비활성화
	public void setEnabledFalse() {
		cbAuto.setEnabled(false);
		checkBoxOnOff(false);
		lotto = null;
	}
	
	//복권번호 리셋
	public void reset() {
		if (lotto != null && !c)
	}
	
	// 체크박스 on/off
	public void checkBoxOnOff(boolean tf) {
		for(JCheckBox ch : numberList) {
			ch.setEnabled(tf);
			if(ch.getBackground() != null) {
				ch.setBackground(null);
				ch.setSelected(false);
			}
		}
	}
	
}