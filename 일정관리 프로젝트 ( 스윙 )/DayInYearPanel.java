import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class DayInYearPanel extends JPanel {
	public static final String[] str = { "일", "월", "화", "수", "목", "금", "토" };
	public static final Dimension LBL_SIZE = new Dimension(30, 50);
	private JLabel[] arrDays;
	private MainScreen ms;
	private Calendar cal;

	private int year;
	private int month;

	public DayInYearPanel(int year, int month, MainScreen ms) {
		this.ms = ms;
		this.year = year;
		this.month = month;

		cal = Calendar.getInstance();

		setLayout(new GridLayout(0, 7));

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);

		int startDay = cal.get(Calendar.DAY_OF_WEEK); // 시작요일
		int lastDay = cal.getActualMaximum(Calendar.DATE); // 마지막 날짜

		for (int i = 0; i < str.length; i++) {
			JLabel temp = new JLabel(str[i], JLabel.CENTER);
			//
			// ★일월화수목금토 담은 레이블 배경 흰색
			Decoration.setLblWhite(temp);

			if (i == 0) {
				// ★일요일 색상 연한 빨강으로 변경
				temp.setForeground(new Color(255, 131, 131));
			}
			add(temp);
		}

		for (int i = 0; i < startDay - 1; i++) {
			JLabel t = new JLabel();
			add(t);
		}

		arrDays = new JLabel[lastDay];

		startDay = 1;
		for (int i = 1; i <= lastDay; i++) {
			arrDays[i - 1] = new JLabel(String.valueOf(i), JLabel.CENTER);
			arrDays[i - 1].setPreferredSize(LBL_SIZE);
			arrDays[i - 1].setForeground(Color.BLACK);
			arrDays[i - 1].setBackground(Color.WHITE);
			arrDays[i - 1].setOpaque(true);
			addEvent(arrDays[i - 1]);
			add(arrDays[i - 1]);
			startDay++;

			// ★일 레이블 흰색으로 설정 + 폰트 설정
			Decoration.setLblWhite(arrDays[i - 1]);
			arrDays[i - 1].setFont(Decoration.f4);
		}
		// ★패널 배경 흰색으로 설정
		Decoration.setPnlWhite(this);
	}

	private void addEvent(JLabel temp) {
		temp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				JLabel source = (JLabel) me.getSource();
				source.setBorder(new LineBorder(Decoration.pink, 2));
				source.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				JLabel source = (JLabel) me.getSource();
				source.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel temp = (JLabel) e.getSource();
				// 더블클릭 했을 경우
				if (e.getClickCount() == 2) {

					int day = Integer.parseInt(temp.getText());

					// 메인에서 해당 날짜에 해당하는 PlanDTO 리턴함
					ArrayList<PlanDTO> dtoList = ms.getPlanDto(year, month + 1, day);
					if (dtoList != null) {
						// 플랜리스트 모달 생성
						new PlanListModal(year, month, day, ms, dtoList);
					} else {
						JOptionPane.showMessageDialog(DayInYearPanel.this, "등록된 일정이 없습니다");
					}
				}
			}
		});
	}

	// 백 그라운드 설정
	public void addBackground(int day) {
		arrDays[day].setBackground(Decoration.pink);
	}

	// 백 그라운드 해제
	public void delBackground(int day) {
		arrDays[day - 1].setBackground(Color.WHITE);
	}
}
