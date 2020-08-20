package kr.ac.green.InputStream;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListStep3 extends JFrame {
	private JList<String> list;
	private JLabel lbl;
	private JTextField tfNewItem;
	private DefaultListModel<String> model;

	private JPopupMenu pMenu;
	private JMenuItem miRemove;

	public ListStep3() {
		pMenu = new JPopupMenu();
		miRemove = new JMenuItem("Remove");
		pMenu.add(miRemove);

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		lbl = new JLabel("추가항목");
		tfNewItem = new JTextField(5);

		list.setPrototypeCellValue("XXXXXXXXXXXXXX");
		list.setVisibleRowCount(3);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(lbl);
		pnlSouth.add(tfNewItem);

		add(new JScrollPane(list), BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		tfNewItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.addElement(tfNewItem.getText());
				tfNewItem.setText("");
				list.ensureIndexIsVisible(model.getSize() - 1);
			}
		});

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				showPopup(me);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				// TODO Auto-generated method stub
				showPopup(me);
			}

			private void showPopup(MouseEvent me) {
				if (me.isPopupTrigger()) {
					int x = me.getX();
					int y = me.getY();
					int idx = list.locationToIndex(new Point(x, y));

					if (idx >= 0 && list.getSelectedIndex() >= 0) {
						pMenu.show(list, x, y);
					}
				}
			}
		});

		miRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.remove(list.getSelectedIndex());
			}
		});

		setTitle("JList step3");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ListStep3();
	}

}
