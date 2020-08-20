package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class TotalPeopleDlg extends JDialog {
	private JButton btnClose;

	private JPanel pnlCenter = new JPanel(new BorderLayout());
	
	private JPanel pnlCCenter = new JPanel();
	private JScrollPane scroll = new JScrollPane(pnlCCenter);
	private JPanel pnlSouth = new JPanel();
	private ObjectOutputStream oos;
	private ChattingRoomFrame chattingRoomFrame;


	public TotalPeopleDlg(ChattingRoomFrame chattingRoomFrame, ObjectOutputStream oos) {
		super(chattingRoomFrame);
		this.chattingRoomFrame = chattingRoomFrame;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.oos = oos;
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		btnClose = new JButton("닫기");
	}

	private void setDisplay() {
		pnlCCenter.setBackground(Color.WHITE);
		pnlCCenter.setPreferredSize(new Dimension(80, 0));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(200, 160));
		
		pnlCenter.add(scroll, BorderLayout.CENTER);
		pnlSouth.setBackground(Color.WHITE);

		pnlSouth.add(btnClose);

		getContentPane().add(pnlCenter, BorderLayout.NORTH);
		getContentPane().add(pnlSouth, BorderLayout.CENTER);
	}

	public void setUserlist(Client[] c) {
		int width = pnlCCenter.getWidth();
		int height = 0;
		pnlCCenter.removeAll();
		pnlCCenter.repaint();
		for (int i = 0; i < c.length; i++) {
			UserInfoPanel uip = new UserInfoPanel(c[i].getNickName(), c[i].getGender(), oos);
			uip.getMiInvite().setVisible(true);
			uip.setTotalPeopleDlg(this);
			uip.setChattingRoomFrame(chattingRoomFrame);
			pnlCCenter.add(uip);
			pnlCCenter.setPreferredSize(new Dimension(width, height += 35));
		}
		pnlCCenter.revalidate();
	}

	private void addListeners() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chattingRoomFrame.setPpd();
				dispose();
				
			}
		};
		btnClose.addActionListener(listener);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				chattingRoomFrame.setPpd();
				dispose();
				
			};
		});
	}

	private void showFrame() {
		setIconImage(new ImageIcon("play.png").getImage());
		setTitle("전체인원 확인");
		setSize(150, 230);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}

 


