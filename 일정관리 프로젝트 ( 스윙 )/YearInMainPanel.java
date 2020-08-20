import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;

public class YearInMainPanel extends JPanel {
	private MainScreen ms;

	private ArrayList<MonthInYearPanel> arMonths;
	private int year;

	public YearInMainPanel(int year, MainScreen ms) {
		this.ms = ms;
		this.year = year;
		setLayout(new GridLayout(0, 3, 5, 5));
		setOpaque(true);
		setBackground(Color.WHITE);
		init();
	}

	private void init() {
		arMonths = new ArrayList<MonthInYearPanel>();
		MonthInYearPanel temp = null;
		for (int month = 1; month <= 12; month++) {
			temp = new MonthInYearPanel(year, month, ms);
			
			//★상단 ~월 배경 흰색
			Decoration.setPnlWhite(temp);
			
			arMonths.add(temp);
			add(temp);
		}
	}

	public int getYear() {
		return year;
	}


	// 년도가 같으면 true
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Calendar)) {
			return false;
		}
		Calendar cal = (Calendar) obj;

		int y = cal.get(Calendar.YEAR);

		return year == y;
	}

	public void useYearPanel(PlanDTO temp) {
		Calendar cal = temp.getStart();

		int month = cal.get(Calendar.MONTH);
		int day = temp.getStart().get(Calendar.DATE);

		arMonths.get(month - 1).addBackground(day - 1);
	}

	public void getYearDay(int month, int day) {
		// 0 1 2 3 4 5 6 7 8 9 10 11
		arMonths.get(month-1).delBackground(day);		
	}

}