import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * @author user
 * @ClassName : DayInMonthPanel
 * @Year : 2020. 6. 9.
 * @Information : MonthPanel �ȿ� ���� �г�
 */
public class DayInMonthPanel extends JPanel {
	private JPanel pnlCenterS;

	private JLabel lblDate;

	private JLabel lblIconHIGH;
	private JLabel lblIconMIDDLE;
	private JLabel lblIconLOW;

	private JLabel lblTextHIGH;
	private JLabel lblTextMIDDLE;
	private JLabel lblTextLOW;

	private int year;
	private int month;
	private int day;

	private int high;
	private int middle;
	private int low;

	private MainScreen ms;

	// getter, setter
	public int getDay() {
		return day;
	}

	public DayInMonthPanel(int day) {
		this.day = day;

	}

	public DayInMonthPanel(MainScreen ms, int year, int month, int day) {
		this.ms = ms;
		setLayout(new BorderLayout());
		init();
		setDate(year,month,day);
		setDisplay();
		addListener();
	}

	private void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		lblDate.setText(day + "");
	}

	private void init() {
		lblDate = new JLabel();

		lblIconHIGH = new JLabel("", JLabel.CENTER);
		lblIconMIDDLE = new JLabel("", JLabel.CENTER);
		lblIconLOW = new JLabel("", JLabel.CENTER);

		lblTextHIGH = new JLabel("", JLabel.CENTER);
		lblTextMIDDLE = new JLabel("", JLabel.CENTER);
		lblTextLOW = new JLabel("", JLabel.CENTER);

		// �ڹ�� ������� ����
		Decoration.setLblWhite(lblDate);
		Decoration.setLblWhite(lblIconHIGH);
		Decoration.setLblWhite(lblIconMIDDLE);
		Decoration.setLblWhite(lblIconLOW);
		Decoration.setLblWhite(lblTextHIGH);
		Decoration.setLblWhite(lblTextMIDDLE);
		Decoration.setLblWhite(lblTextLOW);

		// ���� ��Ʈ ����
		lblDate.setFont(Decoration.f1);

	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel(new BorderLayout());

		JPanel pnlCenterN = new JPanel();
		pnlCenterS = new JPanel(new GridLayout(3, 1));

		pnlCenterN.add(lblDate);

		pnlCenterS.add(lblIconHIGH);
		pnlCenterS.add(lblTextHIGH);

		pnlCenterS.add(lblIconMIDDLE);
		pnlCenterS.add(lblTextMIDDLE);

		pnlCenterS.add(lblIconLOW);
		pnlCenterS.add(lblTextLOW);

		pnlCenter.add(pnlCenterS, BorderLayout.CENTER);
		pnlCenter.add(pnlCenterN, BorderLayout.NORTH);

		add(pnlCenter, BorderLayout.CENTER);

		setOpaque(true);
		setBackground(Color.WHITE);

		// ���г� ��� ��� ����
		Decoration.setPnlWhite(pnlCenter);
		Decoration.setPnlWhite(pnlCenterN);
		Decoration.setPnlWhite(pnlCenterS);
	}

	// ���콺 �̺�Ʈ�� �̿��� ȿ���� �߰��ϴ� �޼���
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Object src = e.getSource();
				JPanel pnl = (JPanel) src;
//				pnl.setBorder(new LineBorder(Color.BLUE, 2));
				//�� ���� Ŭ���ϸ� BevelBorder
				pnl.setBorder(new BevelBorder(BevelBorder.LOWERED));

				if (e.getClickCount() == 2) {
				
					// ���ο��� �ش� ��¥�� �ش��ϴ� PlanDTO ������
					ArrayList<PlanDTO> dtoList = ms.getPlanDto(year, month, day);
					
					if (dtoList != null) {
						// �÷�����Ʈ ��� ����
						month--;
						new PlanListModal(year, month, day, ms, dtoList);
						month++;
					} else {
						JOptionPane.showMessageDialog(DayInMonthPanel.this, "��ϵ� ������ �����ϴ�");
					}

				}
			}

			public void mouseEntered(MouseEvent e) {
				Object src = e.getSource();
				JPanel pnl = (JPanel) src;
				//��Ŀ���� �Ͽ� �����ٴ�� EtchedBorder
				pnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			}

			public void mouseExited(MouseEvent e) {
				Object src = e.getSource();
				JPanel pnl = (JPanel) src;
				//��Ŀ���� ������ �ٽ� ��ȸ��
				pnl.setBorder(new LineBorder(Decoration.lightGrey));
			}
		});
	}

	public void addPlanList(PlanDTO temp) {

		if (temp.getImportance().equals(PlanDTO.HIGH)) {
			high++;
		} else if (temp.getImportance().equals(PlanDTO.MIDDLE)) {
			middle++;
		} else if (temp.getImportance().equals(PlanDTO.LOW)) {
			low++;
		}
		setLabelText();
	}

	public void delPlanList(PlanDTO temp) {

		if (temp.getImportance().equals(PlanDTO.HIGH)) {
			high--;
			lblTextHIGH.setText(high + "");
		} else if (temp.getImportance().equals(PlanDTO.MIDDLE)) {
			middle--;
			lblTextMIDDLE.setText(middle + "");
		} else if (temp.getImportance().equals(PlanDTO.LOW)) {
			low--;
			lblTextLOW.setText(low + "");
		}

		if (high == 0 && middle == 0 && low == 0) {
			pnlCenterS.removeAll();
		}
	}

	
	public void setLabelText() {
		lblTextHIGH.setText(high + "");
		lblTextMIDDLE.setText(middle + "");
		lblTextLOW.setText(low + "");

		lblIconHIGH.setText("����");
		lblIconMIDDLE.setText("�߰�");
		lblIconLOW.setText("����");
			
		//��Ʈ����
		lblIconHIGH.setFont(Decoration.f5);
		lblIconMIDDLE.setFont(Decoration.f5);
		lblIconLOW.setFont(Decoration.f5);
		
		lblIconHIGH.setForeground(Color.gray);
		lblIconMIDDLE.setForeground(Color.gray);
		lblIconLOW.setForeground(Color.gray);
		
		lblTextHIGH.setFont(Decoration.f2);
		lblTextMIDDLE.setFont(Decoration.f2);
		lblTextLOW.setFont(Decoration.f2);
		

	}
	public void removeLabelText(){
		lblTextHIGH.setText("");
		lblTextMIDDLE.setText("");
		lblTextLOW.setText("");
		lblIconHIGH.setText("");
		lblIconMIDDLE.setText("");
		lblIconLOW.setText("");
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof DayInMonthPanel)) {
			return false;
		}
		DayInMonthPanel d = (DayInMonthPanel) o;
		return day == d.getDay();
	}

}
