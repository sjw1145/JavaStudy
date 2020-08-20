
import java.awt.Image;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

/**
 * @author user
 * @ClassName : PlanDTO
 * @Year : 2020. 6. 9.
 * @Information : TodoList의 내용을 담고있는 Class
 */
public class PlanDTO implements Serializable {
	public static final String LOW = "낮음";
	public static final String MIDDLE = "중간";
	public static final String HIGH = "높음";

	private String planTitle;
	private String info;
	private String importance;
	private String place;

	private Calendar start;

//	private Calendar finish;

	private JLabel lblIcon;
	private boolean specialDayOrTodo;

	private boolean Sucessed;

	public boolean isSucessed() {
		return Sucessed;
	}

	public void setSucessed(boolean sucessed) {
		Sucessed = sucessed;
	}

	public PlanDTO(String planTitle, String info, String importance, String place, Calendar start, JLabel lblIcon) {
		super();
		this.planTitle = planTitle;
		this.info = info;
		this.importance = importance;
		this.place = place;
		this.start = start;
//		this.finish = finish;
		this.lblIcon = lblIcon;
//		this.specialDayOrTodo = specialDayOrTodo;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean getSpecialDayOrTodo() {
		return specialDayOrTodo;
	}

	public void setSpecialDayOrTodo(boolean specialDayOrTodo) {
		this.specialDayOrTodo = specialDayOrTodo;
	}

	public JLabel getLblIcon() {
		return lblIcon;
	}

	public void setLblIcon(JLabel lblIcon) {
		this.lblIcon = lblIcon;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

//	public Calendar getFinish() {
//		return finish;
//	}

//	public void setFinish(Calendar finish) {
//		this.finish = finish;
//	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		info = "planTitle : " + planTitle + "\n";
		info += "info : " + info + "\n";
		info += "importance : " + importance + "\n";
		info += "Place : " + place + "\n";
		info += "start : " + start + "\n";
//		info += "finish : " + finish + "\n";
		info += "specialDayOrTodo : " + specialDayOrTodo + "\n";
		return info;
	}

	// 년 + 월 + 일 같으면 같은 날임
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof Calendar) {
			// 비교 할 객체임
			Calendar cal = (Calendar) obj;

			int oYear = cal.get(Calendar.YEAR);
			int oMonth = cal.get(Calendar.MONTH);
			oMonth++;
			int oDay = cal.get(Calendar.DATE);

			// Plan의 시작 날짜임
			int year = start.get(Calendar.YEAR);
			int month = start.get(Calendar.MONTH);
			int day = start.get(Calendar.DATE);

			boolean result = ((year == oYear) && (month == oMonth)) && day == oDay;

			return result;

		}

		if (obj == null || obj instanceof PlanDTO) {
			PlanDTO temp = (PlanDTO) obj;

			return this == temp;
		}

		return false;
	}

	public boolean checkDate(int year, int month, int day) {
		int oldYear = start.get(Calendar.YEAR);
		int oldMonth = start.get(Calendar.MONTH);
		int oldDay = start.get(Calendar.DATE);

		System.out.println("테스트 : 년" + oldYear + "월" + oldMonth + "일" + oldDay);
		return ((oldYear == year) && (oldMonth == month)) && oldDay == day;
	}

}
