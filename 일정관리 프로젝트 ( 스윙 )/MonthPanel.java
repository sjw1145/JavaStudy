import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @author user
 * @ClassName : MonthPanel
 * @Year : 2020. 6. 9.
 * @Information : JMainScreen pnlCenter(card)�� �� Month�г� class
 */
public class MonthPanel extends JPanel {

	private Calendar cal = Calendar.getInstance();
	static final String[] STR = { "��", "��", "ȭ", "��", "��", "��", "��" };

	private JPanel pnlCardMonthN;

	private JLabel lbl;

	private int year;
	private int month;

	private int idx;

	private ArrayList<DayInMonthPanel> monthdayList;

	private MainScreen ms;

	public MonthPanel(int year, int month, MainScreen ms) {
		this.ms = ms;
		this.year = year;
		this.month = month;

		init();
		setDisplay();
		makeCal(year, month);
	}

	private void init() {
		pnlCardMonthN = new JPanel(new GridLayout(0, 7, 1, 1));

		monthdayList = new ArrayList<DayInMonthPanel>();

		// �ڹ�� ���
		Decoration.setPnlWhite(pnlCardMonthN);
	}

	private void setDisplay() {
		setLayout(new BorderLayout());
		add(pnlCardMonthN, BorderLayout.CENTER);

		// �ڹ�� ���
		Decoration.setPnlWhite(this);
	}

	// �޷��� ����� �޼���
	// �޷��� ����� ��¥�� ��ġ�Ѵ�
	public void makeCal(int year, int month) {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);

		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		// �ڻ�ܿ� ������ ��Ÿ���� �г� (�׸��� ���̾ƿ� ���� ����)
		JPanel pnl = new JPanel(new GridLayout(0, 7, 2, 2));

		for (int i = 0; i < STR.length; i++) {
			lbl = new JLabel(STR[i], JLabel.CENTER);

			// ��lbl: �Ͽ�ȭ������� ���� ���̺� ��Ʈ����
			lbl.setFont(Decoration.f2);

			// ��lbl ��� ��ȸ������ ����
			lbl.setOpaque(true);
			lbl.setBackground(Decoration.lightGrey);

			if (i == 0) {
				// ���Ͽ��� ��ȫ��
				lbl.setOpaque(true);
				lbl.setBackground(Decoration.pink);
			} else if (i == 6) {
				// ������� �ϴû�
				lbl.setOpaque(true);
				lbl.setBackground(Decoration.blue); 
			}
			pnl.add(lbl);
		}

		add(pnl, BorderLayout.NORTH);

		// DayInMonthPanel �� �޸��� ��ġ�ϴ� �޼���
		for (int i = 0; i < startDay - 1; i++) {
			JPanel emptyPnl = new JPanel();
			
			//��emptyPnl ��� ������� ����
			Decoration.setPnlWhite(emptyPnl);
			
			pnlCardMonthN.add(emptyPnl);
		}
		for (int i = 1; i <= lastDay; i++) {
			DayInMonthPanel monthDayPnl = new DayInMonthPanel(ms,year,month,i);
			
			//�� �� ���̾ƿ��� ��ȸ������ ����
			monthDayPnl.setBorder(new LineBorder(Decoration.lightGrey));
			
//			monthDayPnl.setBorder(new LineBorder(Color.BLACK, 1));
			pnlCardMonthN.add(monthDayPnl);
			monthdayList.add(monthDayPnl);
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	// MonthPanel�� ���ҽ� year�� month�� ������ ���� MonthPanel�̴�.
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PlanDTO)) {
			return false;
		}
		PlanDTO p = (PlanDTO) obj;

		Calendar cal = p.getStart();

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);

		if (year == y && month == m) {
			// DayInMonthPanel �� day �� ���ϱ�
			for (int i = 0; i < monthdayList.size(); i++) {
				DayInMonthPanel dayInMonthpnl = monthdayList.get(i);
				if (dayInMonthpnl.equals(new DayInMonthPanel(d))) {
					idx = i;
					return true;
				}
			}
		}
		return false;
	}

	public void useMonthPanel(PlanDTO temp) {
		// DayInMonthPanel �� PlanDTO �� �ѱ��.
		DayInMonthPanel mp = monthdayList.get(idx);
		mp.addPlanList(temp);
	}
	public void removeMonthPanel(PlanDTO temp) {
		// DayInMonthPanel �� PlanDTO �� �ѱ��.
		DayInMonthPanel mp = monthdayList.get(idx);
		mp.delPlanList(temp);
	}
	public void getDay(int month, int day){
		monthdayList.get(day-1).removeLabelText();
	}
	
}
