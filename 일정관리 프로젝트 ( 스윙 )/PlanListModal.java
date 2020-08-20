import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class PlanListModal extends JDialog {
	private MainScreen ms;
	private JLabel lblMonth;
	private JLabel lblDate;

	private JPanel pnlCenter;
	private JPanel pnlCenterItem;

	private JButton btnCancel;

	private ArrayList<PlanDTO> planList;

	private TodoListPanel[] todoListPanel;

	public PlanListModal(int year, int month, int day, MainScreen ms, ArrayList<PlanDTO> dtoList) {
		super(ms, true);
		this.ms = ms;
		planList = dtoList;
		init();
		setDisplay();
		setLabelText(month, day);
		showDialog();

	}

	private void init() {
		lblMonth = new JLabel();
		lblDate = new JLabel();

	}

	public MainScreen getMs() {
		return ms;
	}

	private void setDisplay() {
		// North ( ex 6월 10일 )
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblMonth);
		pnlNorth.add(lblDate);

		pnlCenter = new JPanel(new BorderLayout());

		pnlCenterItem = new JPanel(new GridLayout(0, 1, 1, 1));

		pnlCenter.setBorder(new TitledBorder("목록"));

		JScrollPane scroll = new JScrollPane(pnlCenterItem);

		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCenter.add(scroll, BorderLayout.CENTER);

		setDTOInfo();

		btnCancel = new JButton("닫기");

		// ★닫기 버튼 흰색
		Decoration.setBtnWhite(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnCancel);

		add(pnlNorth, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// ★패널 색상 흰색으로
		Decoration.setPnlWhite(pnlNorth);
		Decoration.setPnlWhite(pnlSouth);
	}

	private void showDialog() {
		setTitle("일정 목록");
		setSize(400, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setLabelText(int month, int day) {
		lblMonth.setText((month + 1) + "월");
		lblDate.setText(day + "일 일정 List");

//		Font font = new Font(Font.DIALOG, Font.BOLD, 30);

		lblMonth.setFont(Decoration.f2);
		lblDate.setFont(Decoration.f2);
	}

	public void setDTOInfo() {
		pnlCenterItem.removeAll();
		pnlCenterItem.revalidate();
		if (planList.size() != 0) {
			todoListPanel = new TodoListPanel[planList.size()];
			for (int i = 0; i < planList.size(); i++) {
				todoListPanel[i] = new TodoListPanel(this, planList.get(i));
				pnlCenterItem.add(todoListPanel[i]);
			}
		} else {
			dispose();
		}
	}

	public void deletePlan(PlanDTO plan) {
		// 현재 플랜리스트 모달의 planList 사이즈가 0이 아닐 경우
		if (planList.size() != 0) {
			
			int check = planList.indexOf(plan);
			planList.remove(check);
			
			pnlCenterItem.removeAll();
			pnlCenterItem.revalidate();

			if (planList.size() != 0) {
				setDTOInfo();
			} else {
				ms.removeYearBackground(plan);
			}
			ms.removeMonthPanel(plan);
			ms.removePlan(plan);
			ms.addDdayPlan();
			dispose();
		}

		if (planList.size() == 0) {
			pnlCenterItem.removeAll();
			pnlCenterItem.repaint();
		}
	}

	public void deletePlanList(PlanDTO temp) {
		// 현재 리스트에서만 삭제
		planList.remove(temp);
	}

	// 날짜가 변경이 되면 해당 리스트에서 삭제
	public boolean checkDate(int year, int month, int day, PlanDTO temp) {
		Calendar cal = temp.getStart();

		int newYear = cal.get(Calendar.YEAR);
		int newMonth = cal.get(Calendar.MONTH);
		int newDate = cal.get(Calendar.DATE);

		boolean result = (year == newYear) && (month == newMonth) && day == newDate;
		// 날짜가 변경이 되었을 때

		return result;
	}

	public void deleteBackground(int year, int month, int day, PlanDTO temp) {
		System.out.println(planList.size());
		if (planList.size() == 0) {
			ms.removeYearBackground(year, month, day);
			ms.removeDayInMonth(year, month, day);
		}

		ms.yearPanelList(temp);
		ms.monthPanelList(temp);
	}

}
