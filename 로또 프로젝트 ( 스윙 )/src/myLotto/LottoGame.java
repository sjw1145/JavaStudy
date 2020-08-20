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
	
	public static final String PRICE = "1,000��";
	public static final int LOTTO_MAXIMUM = 45;
	
	//TOP
	private JLabel lblTopLeft;
	private JLabel lblTopCenter;
	
	//CENTER (üũ ��ư�� �� ��)
	private ArrayList<JCheckBox> numberList;
	
	//SOUTH (�ڵ����� / ���) üũ�ڽ�
	private JCheckBox cbAuto;
	
	//����� �ζ� ��ȣ ���� ����
	private int count;
	
	//�ζǹ�ȣ�� ���� Ŭ����
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
		
		cbAuto = new JCheckBox("�ڵ�����");
		
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

	
	
	//üũ�ڽ� 45�� ����
	private ArrayList<JCheckBox> addNumber() {
		ArrayList<JCheckBox> numbers = new ArrayList<JCheckBox>();
		
		for(int i = 0; i < LOTTO_MAXIMUM; i++) {
			JCheckBox temp = new JCheckBox();
			temp.setText(String.valueOf(i+1));
			
			numbers.add(temp);
		}
		return numbers;
	}
	

	//���� ��Ȱ��ȭ
	public void setEnabledFalse() {
		cbAuto.setEnabled(false);
		checkBoxOnOff(false);
		lotto = null;
	}
	
	//���ǹ�ȣ ����
	public void reset() {
		if (lotto != null && !c)
	}
	
	// üũ�ڽ� on/off
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