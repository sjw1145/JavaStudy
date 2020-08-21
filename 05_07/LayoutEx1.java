/*
	java.awt
	- BorderLayout
		1. 5���� �������� �����ؼ� ��ġ(North, Center, East, West, South)
		2. �� �������� 1���� Component�� ǥ�õȴ�.
		3. �������� ������ ��ȭ�� ���� ������ �ٸ��� �޴´�.
			a. Ⱦ : North, Center, South
			b. �� : East, Center, West
	- FlowLayout
		1. �������� �������� �ʴ´�.
		2. Component�� �������� ǥ���� �� �ִ� ������ ũ��(PreferredSize)�� ������ ����.
		3. ���� -> ������, �� -> �Ʒ�
		4. ���Ĺ�� ����(LEFT, RIGHT, CENTER)	
		5. Container�� ũ�⺯ȭ�� ������Ҵ� ������ ���� �ʴ´�.
	- GridLayout
		1. ��, ĭ���� ������ �����Ѵ�.
		2. �������� ũ��� ��� ����
		3. �������� 1�� Component�� ���������ϴ�.
		4. ������ ��Ҵ� �ش翵���� ���� ä��� ũ��� ����ȴ�.
		5. Container�� ũ�⺯ȭ�� ��� ��Ұ� ������ �޴´�.
		6. �� �������� ������ �� �� �ִ�.

	JFrame�� �⺻ layout-manager : BorderLayout
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
		// ���̾ƿ� �޴��� ����
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
		// �����Ǵ� ���� layout-manager�� ���� ũ��� ��ġ������ ���Ѵ�.
		// �ǹ̰� ����.
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
		// �⺻���̾ƿ��޴��� : BorderLayout
		// ���̾ƿ��޴��� ����
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		// BorderLayout -> FlowLayout
		setLayout(flowLayout);

		for(int i=0; i<20; i++) {
			JButton btn = new JButton(String.valueOf(i + 1));
			add(btn);
		}
	}
	private void gridLayout() {
		// ����(��, ĭ)
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
