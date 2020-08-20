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
 * @Information : JMainScreen pnlCenter(card)에 들어갈 Month패널 class
 */
public class MonthPanel extends JPanel {

	private Calendar cal = Calendar.getInstance();
	static final String[] STR = { "일", "월", "화", "수", "목", "금", "토" };

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

		// ★배경 흰색
		Decoration.setPnlWhite(pnlCardMonthN);
	}

	private void setDisplay() {
		setLayout(new BorderLayout());
		add(pnlCardMonthN, BorderLayout.CENTER);

		// ★배경 흰색
		Decoration.setPnlWhite(this);
	}

	// 달력을 만드는 메서드
	// 달력을 만든뒤 날짜를 배치한다
	public void makeCal(int year, int month) {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);

		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		// ★상단에 요일을 나타내는 패널 (그리드 레이아웃 간격 수정)
		JPanel pnl = new JPanel(new GridLayout(0, 7, 2, 2));

		for (int i = 0; i < STR.length; i++) {
			lbl = new JLabel(STR[i], JLabel.CENTER);

			// ★lbl: 일월화수목금을 담은 레이블 폰트설정
			lbl.setFont(Decoration.f2);

			// ★lbl 배경 연회색으로 설정
			lbl.setOpaque(true);
			lbl.setBackground(Decoration.lightGrey);

			if (i == 0) {
				// ★일요일 분홍색
				lbl.setOpaque(true);
				lbl.setBackground(Decoration.pink);
			} else if (i == 6) {
				// ★토요일 하늘색
				lbl.setOpaque(true);
				lbl.setBackground(Decoration.blue); 
			}
			pnl.add(lbl);
		}

		add(pnl, BorderLayout.NORTH);

		// DayInMonthPanel 을 달마다 배치하는 메서드
		for (int i = 0; i < startDay - 1; i++) {
			JPanel emptyPnl = new JPanel();
			
			//★emptyPnl 배경 흰색으로 설정
			Decoration.setPnlWhite(emptyPnl);
			
			pnlCardMonthN.add(emptyPnl);
		}
		for (int i = 1; i <= lastDay; i++) {
			DayInMonthPanel monthDayPnl = new DayInMonthPanel(ms,year,month,i);
			
			//★ 날 레이아웃을 연회색으로 변경
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

	// MonthPanel을 비교할시 year와 month가 같으면 같은 MonthPanel이다.
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
			// DayInMonthPanel 의 day 를 비교하기
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
		// DayInMonthPanel 에 PlanDTO 를 넘긴다.
		DayInMonthPanel mp = monthdayList.get(idx);
		mp.addPlanList(temp);
	}
	public void removeMonthPanel(PlanDTO temp) {
		// DayInMonthPanel 에 PlanDTO 를 넘긴다.
		DayInMonthPanel mp = monthdayList.get(idx);
		mp.delPlanList(temp);
	}
	public void getDay(int month, int day){
		monthdayList.get(day-1).removeLabelText();
	}
	
}
