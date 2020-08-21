/*
	java.awt
	- BorderLayout
		1. 5개의 영역으로 분할해서 배치(North, Center, East, West, South)
		2. 각 영역에는 1개의 Component만 표시된다.
		3. 영역별로 사이즈 변화에 따라 영향을 다르게 받는다.
			a. 횡 : North, Center, South
			b. 종 : East, Center, West
	- FlowLayout
		1. 영역으로 분할하지 않는다.
		2. Component는 구성물을 표현할 수 있는 최적의 크기(PreferredSize)로 결정이 난다.
		3. 왼쪽 -> 오른쪽, 위 -> 아래
		4. 정렬방식 결정(LEFT, RIGHT, CENTER)	
		5. Container의 크기변화에 구성요소는 영향을 받지 않는다.
	- GridLayout
		1. 줄, 칸으로 영역을 분할한다.
		2. 각영역의 크기는 모두 동일
		3. 영역별로 1개 Component만 부착가능하다.
		4. 부착된 요소는 해당영역을 가득 채우는 크기로 변경된다.
		5. Container의 크기변화에 모든 요소가 영향을 받는다.
		6. 각 영역별로 간격을 줄 수 있다.

	JFrame의 기본 layout-manager : BorderLayout
*/
import java.awt.*;
import javax.swing.*;
class LayoutEx1 extends JFrame {
	public static final int BORDER = 0;
	public static final int FLOW = 1;
	public static final int GRID = 2;
	public static final int NONE = 3;
	public LayoutEx1(int layout) {
		String title = "BorderLayoutEx";
		switch(layout) {
			case FLOW : 
				flowLayout();
				title = "FlowLayoutEx";
				break;
			case GRID :
				gridLayout();
				title = "GridLayoutEx";
				break;
			case NONE :
				noLayout();
				title = "no layout";
			default :
				borderLayout();
		}

		setTitle(title);
		// Component
		setSize(400, 400);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void noLayout() {
		// 레이아웃 메니저 제거
		setLayout(null);

		JButton btn1 = new JButton("first");
		btn1.setSize(100, 100);
		btn1.setLocation(0, 0);

		JButton btn2 = new JButton("second");
		btn2.setSize(200, 200);
		btn2.setLocation(100, 100);

		add(btn1);
		add(btn2);
	}
	private void borderLayout() {
		JButton btnNorth = new JButton("North");
		// 부착되는 순간 layout-manager에 의해 크기와 위치정보가 변한다.
		// 의미가 없다.
		btnNorth.setSize(100, 100);
		btnNorth.setLocation(200, 200);
		JButton btnCenter = new JButton("Center");
		JButton btnWest = new JButton("West");
		JButton btnEast = new JButton("East");
		JButton btnSouth = new JButton("South");

		add(btnNorth, BorderLayout.NORTH);
		add(btnCenter, BorderLayout.CENTER);
		add(btnWest, BorderLayout.WEST);
		add(btnEast, BorderLayout.EAST);
		add(btnSouth, BorderLayout.SOUTH);
	}
	private void flowLayout() {
		// 기본레이아웃메니저 : BorderLayout
		// 레이아웃메니저 변경
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		// BorderLayout -> FlowLayout
		setLayout(flowLayout);

		for(int i=0; i<20; i++) {
			JButton btn = new JButton(String.valueOf(i + 1));
			add(btn);
		}
	}
	private void gridLayout() {
		// 격자(줄, 칸)
		setLayout(new GridLayout(4, 5, 10, 5));

		for(int i=0; i<20; i++) {
			add(new JButton(String.valueOf(i + 1)));
		}
		//add(new JButton(String.valueOf("extra")));
	}
	public static void main(String[] args)	{
		new LayoutEx1(FLOW);
	}
}
