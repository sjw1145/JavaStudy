import java.awt.*;
import javax.swing.*;
class Counter extends JFrame {
	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnDefault;

	public Counter() {
		init();
		setDisplay();
		showFrame();
	}
	// Component �ʱ�ȭ
	private void init() {
		// ����, �׸��� ��Ÿ���� ���� ���
		// lblNum = new JLabel("0", JLabel.CENTER);
		lblNum = new JLabel();
		lblNum.setText("0");
		// Ⱦ����
		lblNum.setHorizontalAlignment(JLabel.CENTER);
		
		// ��Ʈ : �۲�, ��Ÿ��, ũ��
		Font font = new Font(
			Font.SERIF, Font.BOLD, 80	
		);
		lblNum.setFont(font);
		
		// ������ ����
		lblNum.setOpaque(true);
		// RGB
		Color color = new Color(0xFFD8D8);
		color = new Color(0, 0, 0);
		//color = new Color(255, 255, 255);
		// ����
		lblNum.setBackground(color);
		// �����(���ڻ�)
		lblNum.setForeground(Color.RED);



		btnPlus = new JButton();
		btnPlus.setText("Plus");
		btnMinus = new JButton("Minus");
		btnDefault = new JButton("Default");
	}
	// ��ġ
	private	void setDisplay() {
		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		
		add(btnDefault, BorderLayout.NORTH);
		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}	
	// ������ ����
	private void showFrame() {
		setTitle("Counter");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args)	{
		new Counter();
	}
}
