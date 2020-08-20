
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MakePlanPanel extends JPanel {
	public static final Font font = new Font(Font.DIALOG, Font.BOLD, 12);

	private JButton btnEmoji;
	private JLabel lblIcon;
	private JTextField tfPlanTitle;

	private JTextArea taInput;

	private JLabel lblImportance;
	private JComboBox<String> cbImportance;
	private JLabel lblPlace;
	private JTextField tfPlace;
	private JLabel lblStart;
//	private JLabel lblFinish;

	private PlanDTO planDTO;

	private Calendar start = Calendar.getInstance();
//	private Calendar finish = Calendar.getInstance();

	// 일정선택에 쓰일 변수
	private String[] arrYears;
	private String[] arrMonths;
	private String[] arrDays;

	private JComboBox<String> cbStartYear;
	private JComboBox<String> cbStartMonth;
	private JComboBox<String> cbStartDay;
	private JLabel lblStartYear;
	private JLabel lblStartMonth;
	private JLabel lblStartDay;

//	private JComboBox<String> cbFinishYear;
//	private JComboBox<String> cbFinishMonth;
//	private JComboBox<String> cbFinishDay;
	private JLabel lblFinishYear;
	private JLabel lblFinishMonth;
	private JLabel lblFinishDay;

	// 생성자
	public MakePlanPanel(PlanDTO plan) {
		this();
		setInfo(plan);
	}

	public MakePlanPanel() {

		init();
		setDisplay();
		addListener();
		showFrame();
	}

	// getter, setter
	public String getPlanTitle() {
		return tfPlanTitle.getText();
	}

	public String getInfo() {
		return taInput.getText();
	}

	public String getImportance() {
		return (String) cbImportance.getSelectedItem();
	}

	public String getPlace() {
		return tfPlace.getText();
	}

	public Calendar getStart() {

		return start;
	}

//	public Calendar getFinish() {
//		return finish;
//	}
	
	public Icon getIcon() {
		return lblIcon.getIcon();
	}
	
	public void setIcon(Icon iCon) {
		lblIcon.setIcon(iCon);
		lblIcon.setVisible(true);
	}

	// ===================================================
	// 메서드

	// EmojiDialog에서 선택한 아이콘을 lblIcon자리에 넣는다.




	private void init() {

		// NORHT에 들어갈 컴포넌트: 아이콘 선택 버튼, 선택된 아이콘 레이블, 일정 제목입력
		lblIcon = new JLabel();
		lblIcon.setPreferredSize(new Dimension(32, 32));

		btnEmoji = new JButton(new ImageIcon("default_smile.png"));
		btnEmoji.setBackground(Color.WHITE);
		btnEmoji.setBorder(null);

		// CENTER에 들어갈 컴포넌트: 내용입력
		tfPlanTitle = new JTextField(22);
		taInput = new JTextArea(10,20);
		taInput.setLineWrap(true);
		//taInput.setWrapStyleWord(true);

		// SOUTH에 들어갈 컴포넌트: 중요도, 장소, 일정, 할일/기념일 선택
		lblImportance = new JLabel("중요도");
		lblPlace = new JLabel("장소");
		tfPlace = new JTextField(21);
		lblStart = new JLabel("일정");
//		lblFinish = new JLabel("종료일자");

		lblImportance.setFont(font);
		lblPlace.setFont(font);
		lblStart.setFont(font);
//		lblFinish.setFont(font);

		// <일정(날짜선택) 콤보박스 만들기>
		// 1. String 배열에 숫자들(날짜)을 담은for문
		arrYears = new String[31];
		int idx = 0;
		for (int i = 2020; i <= 2050; i++) {
			arrYears[idx] = Integer.toString(i);
			idx++;
		}

		idx = 0;
		arrMonths = new String[12];
		for (int i = 1; i <= 12; i++) {
			arrMonths[idx] = Integer.toString(i);
			idx++;
		}

		idx = 0;
		arrDays = new String[31];
		for (int i = 1; i <= 31; i++) {
			arrDays[idx] = Integer.toString(i);
			idx++;
		}

		// 2. 콤보박스에 담기
		cbStartYear = new JComboBox<String>(arrYears);
		cbStartMonth = new JComboBox<String>(arrMonths);
		cbStartDay = new JComboBox<String>(arrDays);

//		cbFinishYear = new JComboBox<String>(arrYears);
//		cbFinishMonth = new JComboBox<String>(arrMonths);
//		cbFinishDay = new JComboBox<String>(arrDays);

		// 3. 콤보박스 배경 흰색으로 설정
		cbStartYear.setBackground(Color.WHITE);
		cbStartMonth.setBackground(Color.WHITE);
		cbStartDay.setBackground(Color.WHITE);

//		cbFinishYear.setBackground(Color.WHITE);
//		cbFinishMonth.setBackground(Color.WHITE);
//		cbFinishDay.setBackground(Color.WHITE);

		lblStartYear = new JLabel("년");
		lblStartMonth = new JLabel("월");
		lblStartDay = new JLabel("일");
		lblFinishYear = new JLabel("년");
		lblFinishMonth = new JLabel("월");
		lblFinishDay = new JLabel("일");

	}

	private void setDisplay() {
		setLayout(new BorderLayout());

		// North
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(btnEmoji);
		pnlNorth.add(lblIcon);

		pnlNorth.add(tfPlanTitle);

		add(pnlNorth, BorderLayout.NORTH);

		Decoration.setPnlWhite(pnlNorth);

		// ==================================
		// CENTER
		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(taInput, BorderLayout.CENTER);
		add(pnlCenter, BorderLayout.CENTER);

		Color whiteblue = new Color(178, 204, 255);

		JScrollPane scroll = new JScrollPane(taInput);
		scroll.setBorder(new LineBorder(whiteblue, 3));
		pnlCenter.add(scroll);

		// ==================================
		// SOUTH
		cbImportance = new JComboBox<String>();

		cbImportance.addItem("낮음");
		cbImportance.addItem("중간");
		cbImportance.addItem("높음");

		cbImportance.setBackground(Color.WHITE);

		JPanel pnlSouth = new JPanel(new BorderLayout());

		JPanel pnlSouthW = new JPanel(new GridLayout(0, 1));
		JPanel pnlSouthW1 = new JPanel(); // lbl 중요도
		JPanel pnlSouthW2 = new JPanel(); // lbl 장소
		JPanel pnlSouthW3 = new JPanel(); // lbl 시작일정
		JPanel pnlSouthW4 = new JPanel(); // lbl 종료일정

		JPanel pnlSouthC = new JPanel(new GridLayout(0, 1));
		JPanel pnlSouthC1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnlSouthC2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 장소tf을 담을 패널
		JPanel pnlSouthC3_start = new JPanel(); // 시작일자 콤보박스 부착 패널
		JPanel pnlSouthC4_finish = new JPanel(); // 종료일자 콤보박스 부착 패널

		// 패널 배경을 흰색으로 설정
		Decoration.setPnlWhite(pnlSouthW);
		Decoration.setPnlWhite(pnlSouthW1);
		Decoration.setPnlWhite(pnlSouthW2);
		Decoration.setPnlWhite(pnlSouthW3);
		Decoration.setPnlWhite(pnlSouthW4);
		Decoration.setPnlWhite(pnlSouthC);
		Decoration.setPnlWhite(pnlSouthC1);
		Decoration.setPnlWhite(pnlSouthC2);
		Decoration.setPnlWhite(pnlSouthC3_start);
		Decoration.setPnlWhite(pnlSouthC4_finish);

		// 텍스트 레이블 -WEST
		pnlSouthW1.add(lblImportance);
		pnlSouthW2.add(lblPlace);
		pnlSouthW3.add(lblStart);
//		pnlSouthW4.add(lblFinish);

		pnlSouthW.add(pnlSouthW1);
		pnlSouthW.add(pnlSouthW2);
		pnlSouthW.add(pnlSouthW3);
		pnlSouthW.add(pnlSouthW4);

		// 정보 입력 Panel -CENTER
		pnlSouthC1.add(cbImportance); // 중요도 comboBox

		pnlSouthC2.add(tfPlace); // 장소 textField

		pnlSouthC3_start.add(cbStartYear);
		pnlSouthC3_start.add(lblStartYear);
		pnlSouthC3_start.add(cbStartMonth);
		pnlSouthC3_start.add(lblStartMonth);
		pnlSouthC3_start.add(cbStartDay);
		pnlSouthC3_start.add(lblStartDay);

//		pnlSouthC4_finish.add(cbFinishYear);
//		pnlSouthC4_finish.add(lblFinishYear);
//		pnlSouthC4_finish.add(cbFinishMonth);
//		pnlSouthC4_finish.add(lblFinishMonth);
//		pnlSouthC4_finish.add(cbFinishDay);
//		pnlSouthC4_finish.add(lblFinishDay);

		pnlSouthC.add(pnlSouthC1);
		pnlSouthC.add(pnlSouthC2);
		pnlSouthC.add(pnlSouthC3_start);
		pnlSouthC.add(pnlSouthC4_finish);

		pnlSouth.add(pnlSouthC, BorderLayout.CENTER);
		pnlSouth.add(pnlSouthW, BorderLayout.WEST);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	private void addListener() {
		btnEmoji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmojiDialog(MakePlanPanel.this);
			}
		});

	}

	private void showFrame() {
		setSize(350, 400);
		setVisible(true);
	}

	public PlanDTO getPlanDTO() {
		setCal();

		planDTO = new PlanDTO(tfPlanTitle.getText(), taInput.getText(), (String) cbImportance.getSelectedItem(),
				tfPlace.getText(), start, lblIcon);
		return planDTO;
	}

	public boolean checkTitle() {
		String temp = tfPlanTitle.getText();
		temp = temp.trim();
		if (temp.equals("")) {
			JOptionPane.showMessageDialog(this, "제목을 입력하세요");
			return false;
		}
		return true;
	}

	public void setCal() {

		int year = Integer.parseInt((String) cbStartYear.getSelectedItem());
		int month = Integer.parseInt((String) cbStartMonth.getSelectedItem());
		int date = Integer.parseInt((String) cbStartDay.getSelectedItem());

		start.set(year, month, date);

//		year = Integer.parseInt((String) cbFinishYear.getSelectedItem());
//		month = Integer.parseInt((String) cbFinishMonth.getSelectedItem());
//		date = Integer.parseInt((String) cbFinishDay.getSelectedItem());

//		finish.set(year, month, date);

	}

	/**
	 * @MethodName : setInfo
	 * @작성일 : 2020. 6. 11.
	 * @작성자 : 정종호
	 * @수정내역 :
	 * @MethodInformation : 미리 만들어져 있는 일정을 불러올때 DTO를 참조하여 내용을 세팅하는 메서드.
	 * @param plan
	 */
	private void setInfo(PlanDTO plan) {
		JLabel lblIcon2 = plan.getLblIcon();
		if (lblIcon2 != null) {
			lblIcon.setIcon(lblIcon2.getIcon());
		}

		tfPlanTitle.setText(plan.getPlanTitle());
		taInput.setText(plan.getInfo());
		cbImportance.setSelectedItem(plan.getImportance());
		tfPlace.setText(plan.getPlace());

		String year = plan.getStart().get(Calendar.YEAR) + "";
		String month = plan.getStart().get(Calendar.MONTH) + "";
		String day = plan.getStart().get(Calendar.DATE) + "";

		cbStartYear.setSelectedItem(year);
		cbStartMonth.setSelectedItem(month);
		cbStartDay.setSelectedItem(day);

//		year = plan.getFinish().get(Calendar.YEAR) + "";
//		month = plan.getFinish().get(Calendar.MONTH) + "";
//		day = plan.getFinish().get(Calendar.DATE) + "";
//
//		cbFinishYear.setSelectedItem(year);
//		cbFinishMonth.setSelectedItem(month);
//		cbFinishDay.setSelectedItem(day);
	}

}
