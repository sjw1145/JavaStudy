package myLotto;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LottoTicketFrame extends JFrame{
	
	public static final char[] str = {'A', 'B', 'C', 'E'};
	public static final int CHECKBOX_COUNT_MAX = 6;
	
	//�ζ� �г� ����Ʈ
	private ArrayList<LottoGame> list;
	
	//�ζ� �̹���
	private JLabel lblImg;
	
	//�ϴܹ�ư
	private JButton btnStart;
	private JButton btnReset;
	private JButton btnExit;
	
	public LottoTicketFrame(int result) {
		
		addLottoPanel(result);
		
		init();
		setDisplay();
		setEvent();
		showFrame();
	}

	//�ζ��г��߰�
	private void addLottoPanel(int result) {
		
		list = new ArrayList<LottoGame>();
		
		LottoGame temp;
		
		for(int i = 0; i < 5; i++) {
			temp = new LottoGame(str[i]);
			
			if(i>= result) {
				temp.setEnabledFalse();
			}
			list.add(temp);
		}
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
	}

	private void setDisplay() {
		// TODO Auto-generated method stub
		
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		
	}

	private void showFrame() {
		// TODO Auto-generated method stub
		
	}
	
}