/*
	JPanel - Container - FlowLayout 
*/
import java.awt.*;
import javax.swing.*;
class LayoutEx2 extends JFrame {
	
	public LayoutEx2() {
		setLayout(new GridLayout(1, 2));
		
		JPanel pnlLeft = new JPanel();
		for(int i=0; i<20; i++) {
			pnlLeft.add(new JButton(String.valueOf(i + 1)));
		}

		JPanel pnlRight = new JPanel(new BorderLayout());
		// pnlRight.setLayout(new BorderLayout());
		pnlRight.add(new JButton("North"), BorderLayout.NORTH);
		pnlRight.add(new JButton("Center"), BorderLayout.CENTER);
		pnlRight.add(new JButton("South"), BorderLayout.SOUTH);

		add(pnlLeft);
		add(pnlRight);

		setTitle("JPanel Ex");
		setLocation(100, 0);
		setSize(600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)	{
		new LayoutEx2();
	}
}






















