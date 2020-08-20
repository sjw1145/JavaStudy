package kr.ac.green.InputStream;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListStep1 extends JFrame {

	private JList<String> list;
	private JLabel lbl;

	public ListStep1() {
		list = new JList<String>(new String[] { "apple", "banana", "kiwi", "grape" });

		lbl = new JLabel("start");

		list.setPrototypeCellValue("XXXXXXXXXXXXXXXXX");

		// 처음 보여질 수
		list.setVisibleRowCount(3);

		add(new JScrollPane(list), BorderLayout.CENTER);
		add(lbl, BorderLayout.SOUTH);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				lbl.setText(list.getSelectedValue());
			}
		});

		setTitle("JList step1");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ListStep1();
	}

}
