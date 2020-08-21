package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * ��â ���� - JDialog, JFrame (�Ϲ������� JDialog)
 *  
 */
public class MyFrame extends JFrame {

	// private JButton btnOpen;
	private Vector<String> wordsList;
	private JTextArea taList;

	public MyFrame() {
		// btnOpen = new JButton("Open");
		// ������ BorderLayout North, East, West, South �� ��� �̾�� �Ѵ�.

		wordsList = new Vector<String>();

		JToolBar tBar = new JToolBar();
		tBar.setFloatable(false);

		JLabel lblInput = new JLabel("�Է� : ");
		JTextField tfInput = new JTextField(10);
		tfInput.setEditable(false);
		JButton btnInput = new JButton("�Է�");
		JButton btnList = new JButton("���");
		JPanel pnlInput = new JPanel();
		pnlInput.add(lblInput);
		pnlInput.add(tfInput);
		pnlInput.add(btnInput);
		pnlInput.add(btnList);

		tBar.add(new JButton("Open"));
		tBar.add(new JButton("Save"));
		tBar.addSeparator();
		tBar.add(new JButton("Exit"));
		tBar.add(Box.createHorizontalGlue());
		tBar.add(new JButton("Help"));
		
		taList = new JTextArea(5, 25);
		taList.setEditable(false);
		JScrollPane scroll = new JScrollPane(taList);
		scroll.setBorder(new TitledBorder(
				new LineBorder(Color.GRAY, 1) ,
				"List"));
		

		JPanel pnl = new JPanel();
		JPanel pnlCenter = new JPanel(new BorderLayout());

		// pnl.add(btnOpen);
		pnlCenter.add(pnl, BorderLayout.SOUTH);
		pnlCenter.add(pnlInput, BorderLayout.CENTER);
		pnlCenter.add(scroll, BorderLayout.SOUTH);
		
		add(tBar, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);

		// btnOpen.addActionListener((ae) -> new MyDialog(MyFrame.this));
		btnInput.addActionListener((ae) -> new MyDialog(MyFrame.this));
		btnList.addActionListener((ae) -> {
			taList.setText("");
			for(String word : wordsList) {
				taList.append(word + "\n");
			}
		});
		
		
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	/*
	 * wordsList �� ���翩�� Ȯ��
	 */
	public boolean exists(String words) {
		return wordsList.contains(words);
	}
	
	public void add(String words) {
		wordsList.add(words);
	}

	public static void main(String[] args) {
		new MyFrame();
	}

}
