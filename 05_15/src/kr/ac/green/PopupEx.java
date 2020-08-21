package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupEx extends JFrame {
	private JPopupMenu pMenu;
	private JLabel lblMain;

	public PopupEx() {

		lblMain = new JLabel("Start : ", JLabel.CENTER);
		lblMain.setFont(new Font(Font.SERIF, Font.BOLD, 40));

		pMenu = new JPopupMenu();
		JMenuItem miApple = new JMenuItem("Apple");
		JMenuItem miBanana = new JMenuItem("Banana");
		JMenuItem miKiwi = new JMenuItem("Kiwi");

		pMenu.add(miApple);
		pMenu.addSeparator();
		pMenu.add(miBanana);
		pMenu.add(miKiwi);

		add(lblMain, BorderLayout.CENTER);

		// popup menu visible (Mouse Event)
		// OS���� �˾� ��ȣ�� �ٸ�
		lblMain.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

		});
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblMain.setText(e.getActionCommand());
			}
		};
		
		miApple.addActionListener(actionListener);
		miBanana.addActionListener(actionListener);
		miKiwi.addActionListener(actionListener);

		setTitle("�˾��޴�");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void showPopup(MouseEvent me) {
		// ��ư + ���� �Ǻ�(�˾� ��ȣ�� �½��ϱ�?)
		if (me.isPopupTrigger()) {
			pMenu.show(lblMain, me.getX(), me.getY());
		}
	}

	public static void main(String[] args) {
		new PopupEx();
	}
}
