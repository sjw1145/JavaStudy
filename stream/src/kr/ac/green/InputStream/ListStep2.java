package kr.ac.green.InputStream;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListStep2 extends JFrame {
	private JList<String> list;
	private JLabel lbl;

	public ListStep2() {
		list = new JList<String>(new String[] { "apple", "banana", "kiwi", "grape" });

		lbl = new JLabel("Start");

		list.setPrototypeCellValue("XXXXXXXXXXXXXX");
		list.setVisibleRowCount(3);

		list.setCellRenderer(new MyListCellRenderer());

		add(new JScrollPane(list), BorderLayout.CENTER);
		add(lbl, BorderLayout.SOUTH);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				lbl.setText(list.getSelectedValue());

			}
		});

		setTitle("JList step2");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class MyListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel lblItem = new JLabel(value.toString());

			if (isSelected) {
				lblItem.setText(lblItem.getText() + "<<< Select");
			}

			return lblItem;
		}

	}

	public static void main(String[] args) {
		new ListStep2();
	}

}
