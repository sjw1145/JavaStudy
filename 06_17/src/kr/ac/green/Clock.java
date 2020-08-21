package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Clock extends JFrame{
	
	private JLabel lblHour;
	private JLabel lblMin;
	private JLabel lblSec;
	
	private Font timerFont;
	
	public Clock() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		timerFont = new Font(Font.SERIF, Font.BOLD, 40);
		lblHour = new JLabel("00", JLabel.CENTER);
		lblHour.setFont(timerFont);
		
		lblMin = new JLabel("00", JLabel.CENTER);
		lblMin.setFont(timerFont);
		
		lblSec = new JLabel("00", JLabel.CENTER);
		lblSec.setFont(timerFont);
	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel();
		
		pnlCenter.add(lblHour);
		pnlCenter.add(getSeparator());
		pnlCenter.add(lblMin);
		pnlCenter.add(getSeparator());
		pnlCenter.add(lblSec);
		
		add(pnlCenter, BorderLayout.CENTER);
	}
	
	private JLabel getSeparator() {
		JLabel lbl = new JLabel(":", JLabel.CENTER);
		lbl.setFont(timerFont);
		return lbl;
	}

	private void addListeners() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTime();
			}
		};
		
		new Timer(1000, listener).start();
	}

	private void showFrame() {
		setTitle("Clock");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTime();
		setVisible(true);
	}
	
	private void setTime() {
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		
		lblHour.setText(String.format("%02d", h));
		lblMin.setText(String.format("%02d", m));
		lblSec.setText(String.format("%02d", s));
	}
	
	public static void main(String[] args) {
		new Clock();
	}

}
