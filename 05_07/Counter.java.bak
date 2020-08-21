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
	// Component 초기화
	private void init() {
		// 글자, 그림을 나타내기 위해 사용
		// lblNum = new JLabel("0", JLabel.CENTER);
		lblNum = new JLabel();
		lblNum.setText("0");
		// 횡정렬
		lblNum.setHorizontalAlignment(JLabel.CENTER);
		
		// 폰트 : 글꼴, 스타일, 크기
		Font font = new Font(
			Font.SERIF, Font.BOLD, 80	
		);
		lblNum.setFont(font);
		
		// 불투명 설정
		lblNum.setOpaque(true);
		// RGB
		Color color = new Color(0xFFD8D8);
		color = new Color(0, 0, 0);
		//color = new Color(255, 255, 255);
		// 배경색
		lblNum.setBackground(color);
		// 전경색(글자색)
		lblNum.setForeground(Color.RED);



		btnPlus = new JButton();
		btnPlus.setText("Plus");
		btnMinus = new JButton("Minus");
		btnDefault = new JButton("Default");
	}
	// 배치
	private	void setDisplay() {
		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		
		add(btnDefault, BorderLayout.NORTH);
		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}	
	// 프레임 설정
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
