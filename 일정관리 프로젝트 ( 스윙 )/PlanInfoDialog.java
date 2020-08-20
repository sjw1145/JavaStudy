import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlanInfoDialog extends JDialog {
	private PlanListModal planListModal;
	private MakePlanPanel pnlMakePlan;

	private JButton btnSave; // 저장
	private JButton btnDelete; // 삭제

	private PlanDTO plan;

	public PlanInfoDialog(PlanListModal planListModal, PlanDTO plan) {
		super(planListModal, true);
		this.planListModal = planListModal;
		this.plan = plan;
		init();
		setDisPlay();
		addListener();
		showFrame();
	}

	private void init() {
		pnlMakePlan = new MakePlanPanel(plan);
		btnSave = new JButton("저장");
		btnDelete = new JButton("삭제");
	}

	private void setDisPlay() {
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSave);
		pnlSouth.add(btnDelete);

		add(pnlMakePlan, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// ★하단 패널 흰 색으로 설정
		Decoration.setPnlWhite(pnlSouth);

		// ★밑에 버튼 세 개 흰색으로
		Decoration.setBtnWhite(btnSave);
		Decoration.setBtnWhite(btnDelete);
	}

	private void addListener() {
		// 저장 버튼 눌렀을 때
		btnSave.addActionListener((e) -> {
			int result = JOptionPane.showConfirmDialog(PlanInfoDialog.this, "저장 하시겠습니까?");
			if (result == JOptionPane.YES_OPTION) {
				Calendar cal = plan.getStart();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);

				// YES 옵션인 경우
				plan.setPlanTitle(pnlMakePlan.getPlanTitle());
				plan.setInfo(pnlMakePlan.getInfo());
				plan.setImportance(pnlMakePlan.getImportance());
				plan.setPlace(pnlMakePlan.getPlace());
				pnlMakePlan.setCal();
				plan.setStart(pnlMakePlan.getStart());
				boolean checkDate = planListModal.checkDate(year, month, day, plan);
				if (!checkDate) {
					// 날짜가 바뀌었으면
					planListModal.deletePlanList(plan);
					planListModal.deleteBackground(year, month, day, plan);
				}
//				plan.setFinish(pnlMakePlan.getFinish());
				// 아이콘 세팅
				Icon temp = pnlMakePlan.getIcon();
				if (plan.getLblIcon() != null && temp != null) {
					plan.getLblIcon().setIcon(temp);
				} else if (plan.getLblIcon() == null && temp != null) {
					plan.setLblIcon(new JLabel(temp));
				}

				JOptionPane.showMessageDialog(PlanInfoDialog.this, "수정 완료");

				planListModal.setDTOInfo();

				dispose();

			}
		});

		// 삭제 버튼 눌렀을 때
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal = plan.getStart();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);
				
				
				// 메인에 삭제 요청 해야한다.
				planListModal.deletePlan(plan);
				dispose();
			}
		});
	}

	private void showFrame() {
		setTitle("일정 확인 창 입니다");
		setSize(350, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
}
