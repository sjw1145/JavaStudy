package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class UserInfoPanel extends JPanel {
	private JLabel lblUser;
	private JLabel lblGender;
	private JPanel pnlHost;
	private JPopupMenu jmenu;
	private JMenuItem miInvite;
	private JMenuItem miWisper;
	private JMenuItem miPass;
	private JMenuItem miKick;

	private String nickName;
	private String gender;
	private ObjectOutputStream oos;

	private TotalPeopleDlg totalPeopleDlg;

	private MainFrame mainFrame;
	private ChattingRoomFrame chattingRoomFrame;

	public UserInfoPanel(String nickName, String gender, ObjectOutputStream oos) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.oos = oos;
		this.nickName = nickName;
		this.gender = gender;
		init();
		addListeners();
		setDisplay();
	}

	public UserInfoPanel(ChattingRoomFrame chattingRoomFrame) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.chattingRoomFrame = chattingRoomFrame;
		init();
		addListeners();
		setDisplay();
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public ChattingRoomFrame getChattingRoomFrame() {
		return chattingRoomFrame;
	}

	public void setChattingRoomFrame(ChattingRoomFrame chattingRoomFrame) {
		this.chattingRoomFrame = chattingRoomFrame;
	}

	public JMenuItem getMiInvite() {
		return miInvite;
	}

	public void setMiInvite(JMenuItem miInvite) {
		this.miInvite = miInvite;
	}

	public JMenuItem getMiWisper() {
		return miWisper;
	}

	public void setMiWisper(JMenuItem miWisper) {
		this.miWisper = miWisper;
	}

	public TotalPeopleDlg getTotalPeopleDlg() {
		return totalPeopleDlg;
	}

	public void setTotalPeopleDlg(TotalPeopleDlg totalPeopleDlg) {
		this.totalPeopleDlg = totalPeopleDlg;
	}

	public JMenuItem getMiPass() {
		return miPass;
	}

	public void setMiPass(JMenuItem miPass) {
		this.miPass = miPass;
	}

	public JPopupMenu getJmenu() {
		return jmenu;
	}

	public void setJmenu(JPopupMenu jmenu) {
		this.jmenu = jmenu;
	}

	public void setMaster() {
		JLabel lblHost = new JLabel(new ImageIcon("왕관.PNG"));
		pnlHost.add(lblHost);
	}

	public JMenuItem getMiKick() {
		return miKick;
	}

	public void setMiKick(JMenuItem miKick) {
		this.miKick = miKick;
	}

	private void addListeners() {
		lblUser.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent me) {
				if (jmenu != null) {
					showPopup(me);
				}
			}

			@Override
			public void mousePressed(MouseEvent me) {
				if (jmenu != null) {
					showPopup(me);
				}
			}
		});
		ActionListener listener = (e) -> {
			Object obj = e.getSource();
			JMenuItem temp = (JMenuItem) obj;
			if (temp == miWisper) {

				String text = lblUser.getText();
				String msg = "/w " + text + " ";
				if (chattingRoomFrame != null) {
					chattingRoomFrame.addTfInput(msg);
				} else {
					mainFrame.addTfInput(msg);
				}

			} else if (temp == miPass) {
				String name = lblUser.getText();

				try {
					oos.writeObject(new PushDataDTO(RequestProtocol.REQUEST_MASTER_ENTRUST, name));
					oos.flush();
					oos.reset();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			} else if (temp == miInvite) {
				PushDataDTO dataDTO;
				int current = chattingRoomFrame.getCurrentC();
				int limit = chattingRoomFrame.getLimitC();
				if (current < limit) {
					dataDTO = new PushDataDTO(RequestProtocol.REQUEST_USER_INVITE, nickName);
					System.out.println("초대 할 닉네임 : " + nickName);
					try {
						oos.writeObject(dataDTO);
						oos.flush();
						oos.reset();

						chattingRoomFrame.getPpd().dispose();
						chattingRoomFrame.setPpd();

						//totalPeopleDlg.dispose();
						//totalPeopleDlg = null;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(chattingRoomFrame, "인원이 가득차서 초대할 수 없습니다.");
				}
			}
		};
		miInvite.addActionListener(listener);
		miWisper.addActionListener(listener);
		miPass.addActionListener(listener);

		miKick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					String user = lblUser.getText();

					oos.writeObject(new PushDataDTO(RequestProtocol.REQUEST_USER_FORCED_EXIT, user));
					oos.flush();
					oos.reset();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void init() {
		jmenu = new JPopupMenu();
		miInvite = new JMenuItem("초대");
		miWisper = new JMenuItem("귓속말");
		miPass = new JMenuItem("방장위임");
		miKick = new JMenuItem("강퇴");
		miPass.setVisible(false);
		miInvite.setVisible(false);
		miKick.setVisible(false);
		lblUser = new JLabel(nickName);
		lblUser.setOpaque(true);
		lblUser.setBackground(Color.WHITE);
		if (gender.equals("여자")) {
			lblGender = new JLabel(new ImageIcon("여자.PNG"));
			lblGender.setOpaque(true);
			lblGender.setBackground(Color.WHITE);
		} else {
			lblGender = new JLabel(new ImageIcon("남자.PNG"));
			lblGender.setOpaque(true);
			lblGender.setBackground(Color.WHITE);
		}

		// lblGender = new JLabel(gender);

	}

	public void showPopup(MouseEvent me) {
		jmenu.show(me.getComponent(), me.getX(), me.getY());
	}

	public void setDisplay() {
		setOpaque(true);
		setBackground(Color.WHITE);
		jmenu.add(miInvite);
		jmenu.add(miWisper);
		jmenu.add(miPass);
		jmenu.add(miKick);
		add(jmenu);
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(120, 30));
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel pnlUser = new JPanel();
		pnlUser.setPreferredSize(new Dimension(120,20));
		
		
		JPanel pnlGender = new JPanel();

		pnlHost = new JPanel();
		pnlHost.setOpaque(true);
		pnlHost.setBackground(Color.WHITE);
		pnlUser.add(lblUser);
		pnlUser.setOpaque(true);
		pnlUser.setBackground(Color.WHITE);
		pnlGender.add(lblGender);
		pnlGender.setOpaque(true);
		pnlGender.setBackground(Color.WHITE);
		add(pnlUser, BorderLayout.CENTER);
		add(pnlGender, BorderLayout.WEST);
		add(pnlHost, BorderLayout.EAST);
	}

	public void deleteJMenu() {
		jmenu = null;
	}

	public void changeColor() {
		lblUser.setOpaque(true);
		lblUser.setForeground(Color.RED);

		lblGender.setOpaque(true);
		lblGender.setForeground(Color.RED);
	}
}

 

