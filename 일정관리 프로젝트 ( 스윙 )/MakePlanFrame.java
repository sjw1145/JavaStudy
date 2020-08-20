
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MakePlanFrame extends JFrame {
	private MainScreen ms;
	private MakePlanPanel pnlMakePlan;
	private JButton btnSubmit;

	private PlanDTO planList;

	public MakePlanFrame(MainScreen ms) {
		this();
		this.ms = ms;
	}

	public MakePlanFrame() {
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {
		pnlMakePlan = new MakePlanPanel();
		btnSubmit = new JButton("���");
		// �� ��Ϲ�ư �������
		Decoration.setBtnWhite(btnSubmit);
	}

	private void setDisplay() {
		add(pnlMakePlan, BorderLayout.CENTER);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSubmit);
		add(pnlSouth, BorderLayout.SOUTH);

		// ���ϴ� �г� ��� ���
		Decoration.setPnlWhite(pnlSouth);
	}

	private void addListener() {
		btnSubmit.addActionListener((e) -> {
			if (pnlMakePlan.checkTitle()) {
				int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "�����ҷ�?", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					planList = pnlMakePlan.getPlanDTO();
					ms.addPlan(planList);
					dispose();
				}
			}
		});
	}

	private void showFrame() {
		setTitle("���� ���â �Դϴ�.");
		setSize(350, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
}
