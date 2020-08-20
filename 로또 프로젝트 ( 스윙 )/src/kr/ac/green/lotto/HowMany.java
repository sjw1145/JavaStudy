package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HowMany extends JFrame implements ActionListener {

	public final static String topText = "몇 개 살래? ( 최대 5개 )";
	public final static int LOTTO_SIZE = 5;

	private JLabel lblTop;
	private JTextField tfInput;
	private JButton btnSubmit;

	public HowMany() {
		init();
		setDisplay();
		addEvent();
		showFrame();
	}

	private void init() {
		lblTop = new JLabel(topText, JLabel.CENTER);
		tfInput = new JTextField(10);
		btnSubmit = new JButton("OK");
	}

	private void setDisplay() {
		JPanel pnlMain = new JPanel();

		pnlMain.setOpaque(true);
		pnlMain.setBackground(Color.WHITE);

		pnlMain.setLayout(new BorderLayout());

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(tfInput);
		pnlCenter.add(btnSubmit);

		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTop);

		JLabel lblMain = new JLabel(new ImageIcon("main.gif"));

		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);

		add(pnlMain, BorderLayout.CENTER);
		add(lblMain, BorderLayout.NORTH);

	}

	private void showFrame() {
		setTitle("로또 몇 개 살래?");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBackground(Color.BLUE);

		setVisible(true);
	}

	private void addEvent() {
		btnSubmit.addActionListener(this);
	}

	private boolean textValidate() {
		try {
			String temp = tfInput.getText();
			int number = Integer.parseInt(temp);

			if (number > LOTTO_SIZE || number <= 0) {
				JOptionPane.showMessageDialog(this, "1에서 5입력할 것", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				return true;
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "숫자만 입력하시오", "알림", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	
	public int changedResult() {
		return Integer.parseInt(tfInput.getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (textValidate() != false) {
			new LottoTicketFrame(changedResult());
			this.setVisible(false);
		}
	}
}
