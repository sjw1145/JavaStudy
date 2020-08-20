
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DdayPanel extends JPanel {
	public static final Font font = new Font(Font.DIALOG, Font.BOLD, 20);

	private JLabel lblTitle;
	private JLabel lblNumber;

	private long num;

	public DdayPanel(String title, long num) {
		this.num = num;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 30));

		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlEast = new JPanel(new BorderLayout());

		// # 회색 배경
		Color grey = new Color(234, 234, 234);
		pnlCenter.setBackground(grey);
		pnlEast.setBackground(grey);

		lblTitle = new JLabel(title);
		lblTitle.setFont(font);
		pnlCenter.add(lblTitle, BorderLayout.CENTER);

		lblNumber = new JLabel("-" + num + " 일");
		lblNumber.setFont(font);
		pnlEast.add(lblNumber, BorderLayout.EAST);
		pnlEast.setPreferredSize(new Dimension(80, 0));

		// #폰트 바꾸기
		lblTitle.setFont(Decoration.f1);
		lblNumber.setFont(Decoration.f1);

		add(pnlCenter, BorderLayout.CENTER);
		add(pnlEast, BorderLayout.EAST);

	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

}