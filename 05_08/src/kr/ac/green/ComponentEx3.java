package kr.ac.green;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ComponentEx3 extends JFrame {
	// ����
	public ComponentEx3() {
		setLayout(new GridLayout(1, 0));

		JPanel pnlHobby = new JPanel(new GridLayout(0, 1));

		// ���� ����
		String[] hobbys = { "����", "����", "����", "����", "����" };
		JCheckBox[] chHobby = new JCheckBox[hobbys.length];

		for (int i = 0; i < chHobby.length; i++) {
			chHobby[i] = new JCheckBox(hobbys[i], true);
			pnlHobby.add(chHobby[i]);
		}

		JPanel pnlGrade = new JPanel(new GridLayout(0, 1));
		String[] names = { "A", "B", "C", "D" };
		JRadioButton[] rbtns = new JRadioButton[names.length];

		// ������ �׷��� ( group �ȿ� ���ԵǾ� �ִ� ����� �ϳ��� ���õ� )
		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < names.length; i++) {
			rbtns[i] = new JRadioButton(names[i]);
			rbtns[i].setBackground(Color.RED);
			pnlGrade.add(rbtns[i]);
			group.add(rbtns[i]);
		}

		rbtns[0].setSelected(true);

		String[] menu = { "¥���", "«��", "������", "ĥ������" };
		JComboBox cbMenu = new JComboBox(menu);
		JPanel pnlMenu = new JPanel();
		pnlMenu.add(cbMenu);

		cbMenu.setSelectedIndex(2);

		System.out.println(cbMenu.getSelectedItem());

		add(pnlHobby);
		add(pnlGrade);
		add(pnlMenu);

		setTitle("Component");
		setSize(500, 200);
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		/*
		 * for(JCheckBox temp : chHobby) { System.out.println(temp.getText() +
		 * " : " + temp.isSelected()); }
		 */
	}

	public static void main(String[] args) {
		new ComponentEx3();
	}
}
