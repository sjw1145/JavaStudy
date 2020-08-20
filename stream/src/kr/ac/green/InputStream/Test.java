package kr.ac.green.InputStream;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test extends JFrame {

	public Test() {

		JPanel pnl = new JPanel(new GridLayout(0, 5));
		JScrollPane scroll = new JScrollPane(pnl);

		for (int i = 0; i < 100; i++) {
			pnl.add(new JLabel(String.valueOf(i)));
		}

		add(scroll, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Test();
	}

}
