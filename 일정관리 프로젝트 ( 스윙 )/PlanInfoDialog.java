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

	private JButton btnSave; // ����
	private JButton btnDelete; // ����

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
		btnSave = new JButton("����");
		btnDelete = new JButton("����");
	}

	private void setDisPlay() {
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSave);
		pnlSouth.add(btnDelete);

		add(pnlMakePlan, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// ���ϴ� �г� �� ������ ����
		Decoration.setPnlWhite(pnlSouth);

		// �ڹؿ� ��ư �� �� �������
		Decoration.setBtnWhite(btnSave);
		Decoration.setBtnWhite(btnDelete);
	}

	private void addListener() {
		// ���� ��ư ������ ��
		btnSave.addActionListener((e) -> {
			int result = JOptionPane.showConfirmDialog(PlanInfoDialog.this, "���� �Ͻðڽ��ϱ�?");
			if (result == JOptionPane.YES_OPTION) {
				Calendar cal = plan.getStart();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);

				// YES �ɼ��� ���
				plan.setPlanTitle(pnlMakePlan.getPlanTitle());
				plan.setInfo(pnlMakePlan.getInfo());
				plan.setImportance(pnlMakePlan.getImportance());
				plan.setPlace(pnlMakePlan.getPlace());
				pnlMakePlan.setCal();
				plan.setStart(pnlMakePlan.getStart());
				boolean checkDate = planListModal.checkDate(year, month, day, plan);
				if (!checkDate) {
					// ��¥�� �ٲ������
					planListModal.deletePlanList(plan);
					planListModal.deleteBackground(year, month, day, plan);
				}
//				plan.setFinish(pnlMakePlan.getFinish());
				// ������ ����
				Icon temp = pnlMakePlan.getIcon();
				if (plan.getLblIcon() != null && temp != null) {
					plan.getLblIcon().setIcon(temp);
				} else if (plan.getLblIcon() == null && temp != null) {
					plan.setLblIcon(new JLabel(temp));
				}

				JOptionPane.showMessageDialog(PlanInfoDialog.this, "���� �Ϸ�");

				planListModal.setDTOInfo();

				dispose();

			}
		});

		// ���� ��ư ������ ��
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal = plan.getStart();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);
				
				
				// ���ο� ���� ��û �ؾ��Ѵ�.
				planListModal.deletePlan(plan);
				dispose();
			}
		});
	}

	private void showFrame() {
		setTitle("���� Ȯ�� â �Դϴ�");
		setSize(350, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
}
