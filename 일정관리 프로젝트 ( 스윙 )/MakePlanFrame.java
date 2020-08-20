
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
		btnSubmit = new JButton("등록");
		// ★ 등록버튼 흰색으로
		Decoration.setBtnWhite(btnSubmit);
	}

	private void setDisplay() {
		add(pnlMakePlan, BorderLayout.CENTER);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSubmit);
		add(pnlSouth, BorderLayout.SOUTH);

		// ★하단 패널 배경 희색
		Decoration.setPnlWhite(pnlSouth);
	}

	private void addListener() {
		btnSubmit.addActionListener((e) -> {
			if (pnlMakePlan.checkTitle()) {
				int i = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?", "저장할래?", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					planList = pnlMakePlan.getPlanDTO();
					ms.addPlan(planList);
					dispose();
				}
			}
		});
	}

	private void showFrame() {
		setTitle("일정 등록창 입니다.");
		setSize(350, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
}
