/*
	배치(layout) : 크기 + 위치

	1. Component : UI구성요소 
		-> base container에 부착되야 가시성을 가질 수 있다.
	2. Container : Component를 포함 할 수 있는 Component
		- base container : JFrame, JDialog ...
		- container : JPanel, JScrollPane...
		- 자체모양을 구성하기 위해 상속받은 Container : JButton
	3. layout manager : 배치관리자
		- BorderLayout
		- FlowLayout
		- GridLayout
		- ...
*/
import javax.swing.*;
class MyFrame2 extends JFrame {

	public MyFrame2() {

		JButton btn = new JButton("Click");
		// 부착
		add(btn);
		add(new JButton("Other"));

		setTitle("MyFrame2");
		setLocation(100, 0);
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)	{
		new MyFrame2();
	}
}
