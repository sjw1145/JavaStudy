package myLotto;

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

	public final static String topText = "�� �� �췡? (�ִ� 5��)";
	public final static int LOTTO_SIZE = 5;
	
	private JLabel lblMain;
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
		JPanel pnlMain = new JPanel(new BorderLayout());
		lblMain = new JLabel(new ImageIcon("main.gif"));
		
		//====================================
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTop);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(tfInput);
		pnlCenter.add(btnSubmit);
		
		JPanel pnlEast = new JPanel();
		JPanel pnlWest = new JPanel();
		
		pnlNorth.setOpaque(true);
		pnlNorth.setBackground(Color.WHITE);
		pnlCenter.setOpaque(true);
		pnlCenter.setBackground(Color.WHITE);
		
		//====================================
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		
		add(lblMain, BorderLayout.NORTH);
		add(pnlMain, BorderLayout.CENTER);

	}

	private void showFrame() {
		setTitle("�ζ� �� �� �췡?");
		setSize(600,450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				JOptionPane.showMessageDialog(
						this, 
						"1����5�� �Է��Ͻÿ�", 
						"�˸�", 
						JOptionPane.ERROR_MESSAGE);
			} else {
				return true;
			}	
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(
					this, 
					"���ڸ� �Է��Ͻÿ�.", 
					"�˸�", 
					JOptionPane.ERROR_MESSAGE);
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
