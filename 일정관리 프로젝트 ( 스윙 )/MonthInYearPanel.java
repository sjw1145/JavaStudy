
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MonthInYearPanel extends JPanel {
	public static final String[] str = { "일", "월", "화", "수", "목", "금", "토" };
	public static final Dimension LBL_SIZE = new Dimension(30, 50);

	public Calendar cal = Calendar.getInstance();

	private JLabel monthName;

	private DayInYearPanel dayInYearPanel;

	private int month;

	// 생성자
	public MonthInYearPanel(int year, int month, MainScreen ms) {
		this.month = month;
		monthName = new JLabel(month + "월", JLabel.CENTER);
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Decoration.lightGrey));


		dayInYearPanel = new DayInYearPanel(year, month - 1, ms);

		// ★~월 레이블 폰트 설정
		monthName.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		add(monthName, BorderLayout.NORTH);
		add(dayInYearPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public int getYearMonth() {
		return this.month;
	}

	public void setYearMonth(int yearMonth) {
		this.month = yearMonth;
	}

	// 백 그라운드 설정
	public void addBackground(int day) {
		dayInYearPanel.addBackground(day);
	}

	// 백 그라운드 해제
	public void delBackground(int day) {
		dayInYearPanel.delBackground(day);
	}

}