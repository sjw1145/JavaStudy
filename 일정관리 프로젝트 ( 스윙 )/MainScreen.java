import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainScreen extends JFrame {

	private JButton btnMonth;
	private JButton btnYear;

	private JButton btnWeekPrevious;
	private JButton btnWeekNext;

	private JButton btnMonthPrevious;
	private JButton btnMonthNext;
	private JButton btnMonthYearP;
	private JButton btnMonthYearN;

	private JButton btnYearPrevious;
	private JButton btnYearNext;

	private CardLayout cardC;
	private JPanel pnlCenterC;

	private JButton btnTodoList;
	private JButton btnDday;

	private JButton btnMakeTodoList;

	private CardLayout cardE;
	private JPanel pnlEastC;

	private JComboBox<String> cbYear;
	private JComboBox<String> cbMonth;

	private ArrayList<MonthPanel> monthList;
	private ArrayList<YearInMainPanel> yearList;

	private JPanel pnlCardMonthC;
	private JPanel pnlCardYearC;

	private CardLayout cardM;
	private CardLayout cardY;

	private String year;
	private String month;

	private JLabel lblDateM;
	private JLabel lblDateY;

	private Vector<PlanDTO> planList;

	private JPanel pnlDday;

	public MainScreen() {
		addWindowsListener();
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	// ������� ����
	private void init() {
		btnMonth = new JButton("Month");
		btnYear = new JButton("Year");

		btnWeekPrevious = new JButton("<");
		btnWeekNext = new JButton(">");

		btnMonthPrevious = new JButton("<");
		btnMonthNext = new JButton(">");
		btnMonthYearP = new JButton("<<");
		btnMonthYearN = new JButton(">>");

		btnYearPrevious = new JButton("<");
		btnYearNext = new JButton(">");

		btnTodoList = new JButton("TodoList");
		btnTodoList.setEnabled(false);
		btnDday = new JButton("Dday");

		btnMakeTodoList = new JButton("���� �����");

		monthList = new ArrayList<MonthPanel>();
		yearList = new ArrayList<YearInMainPanel>();

		// ����� ���糯¥�������� MonthPanel ����
		Calendar cal = Calendar.getInstance();

		cbYear = new JComboBox<String>();
		for (int i = 2020; i <= 2050; i++) {
			cbYear.addItem(String.valueOf(i));

		}

		cbMonth = new JComboBox<String>();
		for (int i = 1; i <= 12; i++) {
			cbMonth.addItem(String.valueOf(i));
		}
		cbMonth.setSelectedIndex((cal.get(Calendar.MONTH)));

		year = String.valueOf((cal.get(Calendar.YEAR)));
		month = String.valueOf((cal.get(Calendar.MONTH)) + 1);

		// ������/���� ��ư ���̿� �� ��/�� ���� ���̺�
		Dimension lblSize = new Dimension(200, 50);

		lblDateM = new JLabel(year + "��" + month + "��", JLabel.CENTER);
		lblDateM.setFont(Decoration.f3);
		lblDateM.setPreferredSize(lblSize);

		lblDateY = new JLabel(year + "��", JLabel.CENTER);
		lblDateY.setFont(Decoration.f3);
		lblDateY.setPreferredSize(lblSize);

		// �ڹ�ư ȸ������ ����
//		Decoration.setBtnGrey(btnWeek);
		Decoration.setBtnGrey(btnMonth);
		Decoration.setBtnGrey(btnYear);

		// �ڳ�¥ �ѱ� ��ư �̹��� ����
		btnWeekPrevious = new JButton(Decoration.left);
		btnWeekNext = new JButton(Decoration.right);

		btnMonthPrevious = new JButton(Decoration.left);
		btnMonthNext = new JButton(Decoration.right);
		btnMonthYearP = new JButton(Decoration.leftDouble);
		btnMonthYearN = new JButton(Decoration.rightDouble);

		btnYearPrevious = new JButton(Decoration.left);
		btnYearNext = new JButton(Decoration.right);

		Decoration.setBtn(btnWeekPrevious); // ��setBtn(): ��ư �׵θ����ֱ�, ���� �����
		Decoration.setBtn(btnWeekNext);
		Decoration.setBtn(btnMonthPrevious);
		Decoration.setBtn(btnMonthNext);
		Decoration.setBtn(btnMonthYearP);
		Decoration.setBtn(btnMonthYearN);
		Decoration.setBtn(btnYearPrevious);
		Decoration.setBtn(btnYearNext);

		// ����������� ��ư �̹��� ����
		btnMakeTodoList = new JButton(new ImageIcon("add (1).png"));
		Decoration.setBtn(btnMakeTodoList);
		// btnMakeTodoList.setBackground(Color.WHITE);

		// �ڳ�¥���� �޺��ڽ� ������� ����
		Decoration.setCbWhite(cbMonth);
		Decoration.setCbWhite(cbYear);
	}

	// ���̾ƿ� ��ġ
	private void setDisplay() {
		setDisplayCenter();
		setDisplayEast();

	}

	private void addListener() {
		ActionListener AListener = ((e) -> {
			// ī�带 �ҷ��� week, month, year ȭ����ȯ�� ȿ���� �ش�.
			if (e.getSource() == btnMonth) {
				cardC.show(pnlCenterC, "Month");
			} else if (e.getSource() == btnYear) {
				cardC.show(pnlCenterC, "Year");
			} else if (e.getSource() == btnTodoList) {
				cardE.show(pnlEastC, "test1");
				btnTodoList.setEnabled(false);
				btnDday.setEnabled(true);
			} else if (e.getSource() == btnDday) {
				cardE.show(pnlEastC, "test2");
				btnTodoList.setEnabled(true);
				btnDday.setEnabled(false);
			}
		});

		btnMonth.addActionListener(AListener);
		btnYear.addActionListener(AListener);
		btnTodoList.addActionListener(AListener);
		btnDday.addActionListener(AListener);

		btnMakeTodoList.addActionListener((e) -> {
			new MakePlanFrame(this);
		});

		// �޺��ڽ��� ���� MonthPanel/YearPanel ȭ�� ��ȯ Listener
		ActionListener cardListener = ((e) -> {
			month = cbMonth.getItemAt(cbMonth.getSelectedIndex());
			year = cbYear.getItemAt(cbYear.getSelectedIndex());

			cardM.show(pnlCardMonthC, year + "" + month);
			lblDateM.setText(year + "��" + month + "��");

			cardY.show(pnlCardYearC, year + "");
			lblDateY.setText(year + "��");
		});

		cbYear.addActionListener(cardListener);
		cbMonth.addActionListener(cardListener);

		// MonthPanel ��ư ��ȯ Listener
		ActionListener MonthListener = ((e) -> {
			int m = Integer.parseInt(month);
			int y = Integer.parseInt(year);

			if (e.getSource() == btnMonthNext) {
				if (m < 12) {
					m++;
					month = String.valueOf(m);
					cardMShowAndLblTextset();
				} else if (m >= 12) {
					if (y < 2050) {
						y++;
						m = 1;
						month = String.valueOf(m);
						year = String.valueOf(y);
						cardMShowAndLblTextset();
					} else {
						JOptionPane.showMessageDialog(MainScreen.this, "2050�� ������ ����� ����.");
					}
				}
			} else if (e.getSource() == btnMonthPrevious) {
				if (m > 1) {
					m--;
					month = String.valueOf(m);
					cardMShowAndLblTextset();

				} else if (m <= 1) {
					if (y > 2020) {
						y--;
						m = 12;
						month = String.valueOf(m);
						year = String.valueOf(y);
						cardMShowAndLblTextset();
					} else {
						JOptionPane.showMessageDialog(MainScreen.this, "2020 �������� �� �� ����");
					}
				}
			} else if (e.getSource() == btnMonthYearP) {
				if (y > 2020) {
					y--;
					year = String.valueOf(y);
					cardMShowAndLblTextset();
				} else {
					JOptionPane.showMessageDialog(MainScreen.this, "2020 �������� �� �� ����");
				}
			} else if (e.getSource() == btnMonthYearN) {
				if (y < 2050) {
					y++;
					year = String.valueOf(y);
					cardMShowAndLblTextset();
				} else {
					JOptionPane.showMessageDialog(MainScreen.this, "2050�� ������ ����� ����.");
				}
				// cbYear.setSelectedItem(year);
				// cbMonth.setSelectedItem(month);
			}
		});

		btnMonthNext.addActionListener(MonthListener);
		btnMonthPrevious.addActionListener(MonthListener);
		btnMonthYearP.addActionListener(MonthListener);
		btnMonthYearN.addActionListener(MonthListener);

		// Year �г� ��ư ��ȯ
		ActionListener YearBTNListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int y = Integer.parseInt(year);
				if (e.getSource() == btnYearNext) {
					if (y < 2050) {
						y++;
						year = String.valueOf(y);
						cardY.show(pnlCardYearC, year);
						cardYShowAndLblTextset();
					} else {
						JOptionPane.showMessageDialog(MainScreen.this, "2050�� ������ ����� ����.");
					}

				} else if (e.getSource() == btnYearPrevious) {
					if (y > 2020) {
						y--;
						year = String.valueOf(y);
						cardY.show(pnlCardYearC, year);
						cardYShowAndLblTextset();
					} else {
						JOptionPane.showMessageDialog(MainScreen.this, "2020 �������� �� �� �����ϴ�. ");
					}
				}

			}
		};

		btnYearNext.addActionListener(YearBTNListener);
		btnYearPrevious.addActionListener(YearBTNListener);
	}

	// ȭ�� �߾� ��ġ ����
	// ȭ�� ����� ��,��,�� ��ư�� ���� ȭ����ȯ
	private void setDisplayCenter() {

		JPanel pnlCenter = new JPanel(new BorderLayout());

		JPanel pnlCenterN = new JPanel(new BorderLayout());

		JPanel pnlCenterNW = new JPanel();
//		pnlCenterNW.add(btnWeek);
		pnlCenterNW.add(btnMonth);
		pnlCenterNW.add(btnYear);
		pnlCenterNW.setBorder(new EmptyBorder(10, 5, 5, 5));

		JPanel pnlCenterNC = new JPanel();
		pnlCenterNC.add(cbYear);
		pnlCenterNC.add(cbMonth);
		Decoration.setPnlWhite(pnlCenterNC);
		pnlCenterNC.setBorder(new EmptyBorder(10, 15, 5, 5));

		JPanel pnlCenterNE = new JPanel();
		pnlCenterNE.add(btnMakeTodoList);

		pnlCenterN.add(pnlCenterNW, BorderLayout.WEST);
		pnlCenterN.add(pnlCenterNC, BorderLayout.CENTER);
		pnlCenterN.add(pnlCenterNE, BorderLayout.EAST);

		cardC = new CardLayout();
		pnlCenterC = new JPanel(cardC);

		// �г� ��� ���
		// Decoration.setPnlWhite(pnlCenter);
		// Decoration.setPnlWhite(pnlCenterN);
		Decoration.setPnlWhite(pnlCenterNW);
		Decoration.setPnlWhite(pnlCenterNC);
		Decoration.setPnlWhite(pnlCenterNE);

		// �� ==================================================
		JPanel pnlCardWeek = new JPanel(new BorderLayout());

		// JPanel pnlCardWeekN = addWeekPanel();
//		JPanel pnlCardWeekN = new WeekPanel();
		JPanel pnlCardWeekS = new JPanel();
		pnlCardWeekS.add(btnWeekPrevious);
		pnlCardWeekS.add(btnWeekNext);

//		pnlCardWeek.add(pnlCardWeekN, BorderLayout.CENTER);
		pnlCardWeek.add(pnlCardWeekS, BorderLayout.SOUTH);


		//#�г� ��� ��� (����)
		Decoration.setPnlWhite(pnlCenter);
		Decoration.setPnlWhite(pnlCenterN);
		Decoration.setPnlWhite(pnlCenterNW);
		Decoration.setPnlWhite(pnlCenterNC);
		Decoration.setPnlWhite(pnlCenterNE);

		//#���ʿ� ���� (�߰�)
		pnlCenter.setBorder(new EmptyBorder(10, 5, 5, 5));

		// �� =====================================================
		JPanel pnlCardMonth = new JPanel(new BorderLayout());
		JPanel pnlCardMonthS = new JPanel();

		cardM = new CardLayout();
		pnlCardMonthC = new JPanel(cardM);

		pnlCardMonthS.add(btnMonthYearP);

		pnlCardMonthS.add(btnMonthPrevious);
		pnlCardMonthS.add(lblDateM);

		pnlCardMonthS.add(btnMonthNext);
		pnlCardMonthS.add(btnMonthYearN);

		addMonthList();

		// ����� ���糯¥�������� MonthPanel ����
		cardM.show(pnlCardMonthC, year + "" + month);

		pnlCardMonth.add(pnlCardMonthC, BorderLayout.CENTER);
		pnlCardMonth.add(pnlCardMonthS, BorderLayout.SOUTH);

		// ��� ���
		Decoration.setPnlWhite(pnlCardMonthS);
		Decoration.setPnlWhite(pnlCardMonthC);
		Decoration.setPnlWhite(pnlCardMonth);

		// �� =======================================================
		JPanel pnlCardYear = new JPanel(new BorderLayout());

		cardY = new CardLayout();

		pnlCardYearC = new JPanel(cardY);

		// �ؿ� ��ư+���̺� ����
		JPanel pnlCardYearS = new JPanel();
		pnlCardYearS.add(btnYearPrevious);
		pnlCardYearS.add(lblDateY);
		pnlCardYearS.add(btnYearNext);

		addYearList();

		// ����� ���糯¥ �������� YearPanel ����
		cardY.show(pnlCardYearC, year);

		// Ķ����, ��ư ����
		pnlCardYear.add(pnlCardYearC, BorderLayout.CENTER);
		pnlCardYear.add(pnlCardYearS, BorderLayout.SOUTH);

		// weekPanel, MonthPanel, YearPanel ī�� ����
		pnlCenterC.add(pnlCardWeek, "Week");
		pnlCenterC.add(pnlCardMonth, "Month");
		pnlCenterC.add(pnlCardYear, "Year");

		pnlCenter.add(pnlCenterN, BorderLayout.NORTH);
		pnlCenter.add(pnlCenterC, BorderLayout.CENTER);

		add(pnlCenter, BorderLayout.CENTER);

		cardC.show(pnlCenterC, "Month");

		// ������
		Decoration.setPnlWhite(pnlCenterC);
		Decoration.setPnlWhite(pnlCardYearS);

		JPanel pnlWest = new JPanel();
		add(pnlWest, BorderLayout.WEST);
		Decoration.setPnlWhite(pnlWest);
	}

	// ȭ�� ������ ��ġ ����
	// ȭ�� ������ todoList, Dday ��ư�� ���� ȭ����ȯ �� ��������� ��ư
	private void setDisplayEast() {
//		JPanel pnlEast = new JPanel(new BorderLayout());
////		pnlEast.setPreferredSize(new Dimension(250, 0));
//
//		// ���� ��� ���� ����, ����, ��������� ��ư
//		JPanel pnlEastN = new JPanel();
//		pnlEastN.add(btnMakeTodoList);
//
//		// ���� �߰� ���� ����/���� ���
//		pnlEastC = new JPanel();
//
//		pnlDday = new JPanel();
//		pnlDday.setPreferredSize(new Dimension(300, 550));
//		pnlDday.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "�ٰ����� ����"));
//
//		// pnlEastC.add(pnlTodo, "test1");
//		pnlEastC.add(pnlDday);
//
//		pnlEast.add(pnlEastN, BorderLayout.NORTH);
//		pnlEast.add(pnlEastC, BorderLayout.CENTER);
//
//		add(pnlEast, BorderLayout.EAST);

		JPanel pnlEast = new JPanel(new BorderLayout());
		pnlEast.setPreferredSize(new Dimension(350, 0));

		// ���� ��� ���� ����, ����, ��������� ��ư
		JPanel pnlEastN = new JPanel();
		pnlEastN.add(btnMakeTodoList);

		// ���� �߰� ���� ����/���� ���
		pnlEastC = new JPanel();

		pnlDday = new JPanel();
		pnlDday.setPreferredSize(new Dimension(300, 660));

		// ��Ÿ��Ʋ���� ����
		TitledBorder tb = new TitledBorder(new LineBorder(Decoration.lightGrey, 2), "D-Day");
		tb.setTitleFont(Decoration.f1);
		tb.setTitleColor(Color.gray);
		pnlDday.setBorder(new TitledBorder(tb));

		// pnlEastC.add(pnlTodo, "test1");
		pnlEastC.add(pnlDday);

		pnlEast.add(pnlEastN, BorderLayout.NORTH);
		pnlEast.add(pnlEastC, BorderLayout.CENTER);

		add(pnlEast, BorderLayout.EAST);

		// ���г� ��� ������� ����
		Decoration.setPnlWhite(pnlEastN);
		Decoration.setPnlWhite(pnlEastC);
		Decoration.setPnlWhite(pnlDday);

		// ����Ʈ ����
		pnlDday.setFont(Decoration.f1);
//		lblTest2.setFont(Decoration.f1);
	}

	private void showFrame() {
		Decoration.setIcon(this);
		setTitle("TodoList");
		setSize(1250, 850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void removeDayInMonth(int year, int month, int day) {
		for (int i = 0; i < monthList.size(); i++) {
			int y = monthList.get(i).getYear();
			int m = monthList.get(i).getMonth();
			if (year == y && month == m) {
				monthList.get(i).getDay(month, day);
			}
		}
	}

	/**
	 * @MethodName : addMonthList
	 * @�ۼ��� : 2020. 6. 8.
	 * @�ۼ��� : ����ȣ
	 * @�������� :
	 * @MethodInformation : 2020~2030�� 1~12�� MonthPanel ��(��) �����ϴ� �޼���
	 */
	private void addMonthList() {
		MonthPanel temp = null;
		for (int year = 2010; year <= 2050; year++) {
			for (int month = 0; month < 12; month++) {
				temp = new MonthPanel(year, month, this);
				pnlCardMonthC.add(temp, year + "" + month);
				monthList.add(temp);
			}
		}
	}

	private void addYearList() {
		YearInMainPanel temp = null;
		for (int years = 2020; years <= 2050; years++) {
			temp = new YearInMainPanel(years, this);
			pnlCardYearC.add(temp, years + "");
			yearList.add(temp);
		}
	}

	// CardM�� �ҷ��Ȱ� ���ÿ� lblDateM�� setText�ϴ� �޼���
	public void cardMShowAndLblTextset() {
		cardM.show(pnlCardMonthC, year + "" + month);
		lblDateM.setText(year + "��" + month + "��");
	}

	// CardY�� �ҷ��� ���ÿ� lblDateY�� setText�ϴ� �޼���
	public void cardYShowAndLblTextset() {
		cardY.show(pnlCardYearC, year + "");
		lblDateY.setText(year + "��");
	}

	public void addPlan(PlanDTO temp) {
		planList.add(temp);
		monthPanelList(temp);
		yearPanelList(temp);
		addDdayPlan();
	}

	/**
	 * @MethodName : removePlan
	 * @�ۼ��� : 2020. 6. 11.
	 * @�ۼ��� : ������
	 * @�������� :
	 * @MethodInformation :
	 * @param PlanDTO
	 */
	
	public void removePlan(PlanDTO temp) {
		int n = planList.indexOf(temp);
		planList.remove(n);
	}

	public void removeYearBackground(PlanDTO temp) {
		Calendar cal = temp.getStart();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);

		for (int i = 0; i < yearList.size(); i++) {
			int n = yearList.get(i).getYear();
			if (year == n) {
				yearList.get(i).getYearDay(month, day);
			}
		}
	}

	public void removeYearBackground(int year, int month, int day) {
		for (int i = 0; i < yearList.size(); i++) {
			int n = yearList.get(i).getYear();
			if (year == n) {
				yearList.get(i).getYearDay(month, day);
			}
		}
	}
	public void removeMonthPanel(PlanDTO temp) {
		for (int i = 0; i < monthList.size(); i++) {
			MonthPanel mp = monthList.get(i);
			if (mp.equals(temp)) {
				mp.removeMonthPanel(temp);
			}
		}
	}
	
	
	// Month
	public void monthPanelList(PlanDTO temp) {
		for (int i = 0; i < monthList.size(); i++) {
			MonthPanel mp = monthList.get(i);
			if (mp.equals(temp)) {
				mp.useMonthPanel(temp);
			}
		}
	}

	// Year
	public void yearPanelList(PlanDTO temp) {
		for (YearInMainPanel yp : yearList) {
			if (yp.equals(temp.getStart())) {
				// �⵵�� ���� ���
				yp.useYearPanel(temp);
			}
		}
	}

	// (�� == ��) == �� ������ ���� ���ش�.
	public ArrayList<PlanDTO> getPlanDto(int year, int month, int day) {

		// PlanDTO �� �ӽ÷� ��Ƽ� �ѱ� ArrayList
		ArrayList<PlanDTO> dtoList = new ArrayList<PlanDTO>();

		for (PlanDTO plan : planList) {
			if (plan.checkDate(year, month, day)) {
				dtoList.add(plan);
			}
		}

		if (dtoList.size() == 0) {
			return null;
		} else {
			return dtoList;
		}
	}

	// ��ϵ� �÷��� ���̸� ����Ͽ� �гο� �߰��Ѵ� (30�� �� ������ �߰�)
	// �������� ����
	public void addDdayPlan() {
		pnlDday.removeAll();
		pnlDday.repaint();
		if (planList.size() != 0) {
			ArrayList<DdayPanel> ddayList = new ArrayList<DdayPanel>();
			for (PlanDTO temp : planList) {
				long n = PlanUtils.dayCalculator(temp);
				if (n != -1) {
					DdayPanel ddayTemp = new DdayPanel(temp.getPlanTitle(), n);
					ddayList.add(ddayTemp);
				}
			}

			Collections.sort(ddayList, new Comparator<DdayPanel>() {

				@Override
				public int compare(DdayPanel dp1, DdayPanel dp2) {
					long a = dp1.getNum();
					long b = dp2.getNum();

					if (a > b) {
						return 1;
					} else if (a < b) {
						return -1;
					}
					return 0;

				}

			});

			
//			pnlDday.revalidate();
			for (DdayPanel temp : ddayList) {
				pnlDday.add(temp);
			}
		}
	}

	// ����ȭ
	private void addWindowsListener() {
		MainScreen.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// ����� 0�� ��� ������ ������.
				if (planList.size() == 0) {
					PlanUtils.deleteToDataFile();
				}

				// �����찡 ���� �Ǿ��� �� ����ȭ �߻� ( �� ����� 0�� �ƴ� ��쿡�� �߻� )
				if (planList != null && planList.size() != 0) {
					PlanUtils.saveToObject(planList);
				}

				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent we) {
				// �����찡 ���� �Ǿ��� �� �� ����ȭ �߻� (null �� ���ϵ� ���)
				if ((planList = PlanUtils.loadToObject()) != null) {
					for (PlanDTO temp : planList) {
						monthPanelList(temp);
						yearPanelList(temp);

					}
				} else {
					planList = new Vector<PlanDTO>();
				}

				addDdayPlan();
			}
		});
	}

	public static void main(String[] args) {
		new MainScreen();
	}
}