package kr.ac.green.lotto;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Loading extends JDialog {
	private JLabel load;
	private Timer time;

	public Loading(LottoTicketFrame tiket) {
		super(tiket, "ÃßÃ·Áß", true);
		init();
		setDisplay();
		showDialog();
	}

	private void init() {
		ImageIcon icon = new ImageIcon("Lotto.gif");
		load = new JLabel(icon);

		time = new Timer();
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				dispose();
			}
		};
		time.schedule(task1, 650);

	}

	private void setDisplay() {
		add(load, BorderLayout.CENTER);

	}

	private void showDialog() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
