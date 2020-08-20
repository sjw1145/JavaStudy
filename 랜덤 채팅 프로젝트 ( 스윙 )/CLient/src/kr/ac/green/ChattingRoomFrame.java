package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ChattingRoomFrame extends JFrame {
	// 호스트
	private Client host;

	private JButton btnUserMenu;
	private JButton btnChatRoomSetting;
	private JButton btnClose;
	private JTextArea taChat;
	private JTextField tfInput;
	private JButton btnSend;

	private JPanel pnlUserIn;
	private ObjectOutputStream oos;
	private int currentC;
	private int limitC;
	private int roomNum;
	private String roomTitle;
	private String password;
	// 현재 클라이언트
	private String myName;

	private CreatRoomDialog dlg;
	private TotalPeopleDlg ppd;

	public void setDlg() {
		dlg = null;
	}

	public void setPpd() {
		System.out.println("이거 실행!");
		ppd = null;
	}

	public TotalPeopleDlg getPpd() {
		return ppd;
	}



	public void setHost(Client host) {
		this.host = host;
	}

	public Client getHost() {
		return host;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getPassword() {
		return password;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public ChattingRoomFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	public ChattingRoomFrame(int roomNum, String roomTitle) {
		this();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.roomNum = roomNum;
		this.roomTitle = roomTitle;

	}

	public ChattingRoomFrame(int currentC, int limitC, int roomNum, String roomTitle, ObjectOutputStream oos) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.currentC = currentC;
		this.limitC = limitC;
		this.roomNum = roomNum;
		this.roomTitle = roomTitle;
		this.oos = oos;
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	public void addTfInput(String tfInput) {
		this.tfInput.setText(tfInput);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLimitC(int limitC) {
		this.limitC = limitC;
	}

	public int getLimitC() {
		return limitC;
	}

	public void setDispose() {
		dispose();
	}

	public int getCurrentC() {
		return currentC;
	}

	public void setCurrentC(int currentC) {
		this.currentC = currentC;
	}

	public void setChattingRoom(String title, int currentC, int limitC, int roomNum, String password) {
		setTitle("[ " + roomNum + "번방 ] " + title);
		this.roomTitle = title;
		this.currentC = currentC;
		this.limitC = limitC;
		this.roomNum = roomNum;
		this.password = password;

	}

	private void init() {
		btnUserMenu = new JButton("대기실유저");
		btnChatRoomSetting = new JButton("채팅방 설정");
		btnClose = new JButton("나가기");
		taChat = new JTextArea(10, 18);
		pnlUserIn = new JPanel();
		taChat.setTabSize(4);
		taChat.setLineWrap(true);
		taChat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		taChat.setEditable(false);
		taChat.setOpaque(true);
		taChat.setForeground(Color.BLACK);

	}

	// 방장 방 설정 변경 사용 가능
	public void useSetting() {
		btnChatRoomSetting.setVisible(true);
	}

	// 방 설정 변경 사용 불가
	public void notUseSetting() {
		btnChatRoomSetting.setVisible(false);
	}

	private void setDisplay() {
		JPanel mainPnl = new JPanel();
		mainPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPnl.setLayout(new BorderLayout(0, 0));
		mainPnl.setOpaque(true);
		mainPnl.setBackground(Color.WHITE);

		JPanel pnlButton = new JPanel(new BorderLayout());
		pnlButton.setOpaque(true);
		pnlButton.setBackground(Color.WHITE);
		JPanel pnlContent = new JPanel(new BorderLayout());
		pnlContent.setOpaque(true);
		pnlContent.setBackground(Color.WHITE);
		JPanel pnlText = new JPanel();
		pnlText.setOpaque(true);
		pnlText.setBackground(Color.WHITE);
		pnlText.setPreferredSize(new Dimension(100, 35));

		JPanel pnlButtonWest = new JPanel();
		pnlButtonWest.setOpaque(true);
		pnlButtonWest.setBackground(Color.WHITE);
		btnUserMenu.setPreferredSize(new Dimension(110, 20));
		pnlButtonWest.add(btnUserMenu);
		JPanel pnlButtonEast = new JPanel();
		pnlButtonEast.setOpaque(true);
		pnlButtonEast.setBackground(Color.WHITE);
		btnChatRoomSetting.setPreferredSize(new Dimension(110, 20));
		btnChatRoomSetting.setVisible(false);
		btnClose.setPreferredSize(new Dimension(80, 20));
		pnlButtonEast.add(btnChatRoomSetting);
		pnlButtonEast.add(btnClose);

		pnlButton.add(pnlButtonWest, BorderLayout.WEST);
		pnlButton.add(pnlButtonEast, BorderLayout.EAST);

		JPanel pnlTa = new JPanel(new GridLayout());
		pnlTa.add(taChat);

		JPanel pnlUser = new JPanel();
		pnlUser.setOpaque(true);
		pnlUser.setBackground(Color.WHITE);

		pnlUserIn = new JPanel();
		pnlUserIn.setOpaque(true);
		pnlUserIn.setBackground(Color.WHITE);
		pnlUserIn.setPreferredSize(new Dimension(150, 270));
		pnlUserIn.setBorder(new TitledBorder("사용자 목록"));

		pnlUser.add(pnlUserIn);

		JScrollPane taScroll = new JScrollPane(pnlTa);

		taScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JScrollPane userScroll = new JScrollPane(pnlUser);
		userScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnlContent.add(taScroll, BorderLayout.CENTER);
		pnlContent.add(userScroll, BorderLayout.EAST);
		pnlText.setLayout(new BorderLayout(0, 0));

		mainPnl.add(pnlButton, BorderLayout.NORTH);
		mainPnl.add(pnlContent, BorderLayout.CENTER);
		mainPnl.add(pnlText, BorderLayout.SOUTH);
		btnSend = new JButton("전송");
		btnSend.setPreferredSize(new Dimension(100,20));
		pnlText.add(btnSend, BorderLayout.EAST);
		tfInput = new JTextField(30);
		pnlText.add(tfInput, BorderLayout.CENTER);

		setContentPane(mainPnl);
	}

	private void addListeners() {
		tfInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (tfInput.getText().equals("")) {
						} else {
							PushDataDTO dataDTO = null;
							if (tfInput.getText().toString().indexOf("/w") == 0) {
								dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WHISPER, tfInput.getText());
							} else {
								dataDTO = new PushDataDTO(RequestProtocol.REQUEST_CHATTINGROOM_MSG_INPUT,
										tfInput.getText());
							}
							if(tfInput.getText().length() >= 100 ) {
								JOptionPane.showMessageDialog(ChattingRoomFrame.this, "100자 이내로입력해주세요");
								tfInput.setText("");
							}else {
						
								tfInput.setText("");
								oos.writeObject(dataDTO);
								taChat.setCaretPosition(taChat.getDocument().getLength());
								oos.flush();
								oos.reset();
						}

						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tfInput.getText().equals("")) {
					} else {
						PushDataDTO dataDTO = null;
						if (tfInput.getText().toString().indexOf("/w") == 0) {
							dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WHISPER, tfInput.getText());
						} else {
							dataDTO = new PushDataDTO(RequestProtocol.REQUEST_CHATTINGROOM_MSG_INPUT,
									tfInput.getText());
						}
						if(tfInput.getText().length() >= 100 ) {
							JOptionPane.showMessageDialog(ChattingRoomFrame.this, "100자 이내로입력해주세요");
							tfInput.setText("");
						}else {
						
							tfInput.setText("");
							oos.writeObject(dataDTO);
							taChat.setCaretPosition(taChat.getDocument().getLength());
							oos.flush();
							oos.reset();
						}

					}
				} catch (IOException ew) {
					ew.printStackTrace();
				}

			}
		});
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				exit();
			}
		});
		btnUserMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getPpd() != null) {
					JOptionPane.showMessageDialog(ChattingRoomFrame.this, "대기실 유저 보기는 이미 실행 중 !");
				} else {
					Integer user = RequestProtocol.REQUEST_ALL_USER_DATA;
					try {
						oos.writeObject(user);
						oos.flush();
						oos.reset();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
					
			}
		});
		btnChatRoomSetting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dlg != null) {
					JOptionPane.showMessageDialog(ChattingRoomFrame.this, "이미 실행 중 입니다");
				} else {
					dlg = new CreatRoomDialog(ChattingRoomFrame.this, oos, getRoomTitle(), getPassword(),
						getLimitC(), getRoomNum());
					dlg.setRoomCurrentCount(getCurrentC());
				}
			}
		});

	}

	public void exit() {
		if (host.getNickName().equals(myName)) {
			Integer exit = RequestProtocol.REQUEST_CHATTINGROOM_HOST_EXIT;

			int n = JOptionPane.showConfirmDialog(this, "방장을 위임하시겠습니까?\n아니오를 누를 시 모든 유저가 대기실로 이동합니다.");
			if (n == JOptionPane.YES_OPTION) {

			} else if (n == JOptionPane.NO_OPTION) {

				try {
					oos.writeObject(exit);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			int i = JOptionPane.showConfirmDialog(ChattingRoomFrame.this, "정말로 나가시겠습니까?");
				if (i == JOptionPane.YES_OPTION) {
					Integer exit = RequestProtocol.REQUEST_CHATTINGROOM_EXIT;
					try {
						oos.writeObject(exit);
						oos.flush();
						oos.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
	}

	private void showFrame() {
		setIconImage(new ImageIcon("play.png").getImage());
		setTitle("[ " + roomNum + "번방 ] " + roomTitle);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		tfInput.requestFocus();
	}

	public void inputMsg(String msg) {
			taChat.append(msg + "\n"); 
			taChat.setCaretPosition(taChat.getDocument().getLength());
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	// 방장 설정
	public void addPanel(String nickName, String gender) {
		UserInfoPanel temp = new UserInfoPanel(nickName, gender, oos);
		temp.changeColor();
		temp.deleteJMenu();
		pnlUserIn.add(temp);
		myName = nickName;
		temp.setMaster();
	}

	public void notUserMenu() {
		btnUserMenu.setVisible(false);
	}

	public void showUser(RoomUserListDTO rul, String name) {
		pnlUserIn.removeAll();
		pnlUserIn.repaint();

		Client[] c = rul.getUserList();

		for (int i = 0; i < c.length; i++) {
			UserInfoPanel uip = new UserInfoPanel(c[i].getNickName(), c[i].getGender(), oos);
			uip.setChattingRoomFrame(this);

			btnChatRoomSetting.setVisible(false);
			btnUserMenu.setVisible(false);

			// 자기 자신일 경우
			if (c[i].getNickName().equals(name)) {
				uip.deleteJMenu();
				uip.changeColor();
			}
			
			if (host.getNickName().equals(c[i].getNickName())) {
				uip.setMaster();
			}

			// 방장일 때의 모든 클라이언트들의 권한 설정
			if (host.getNickName().equals(name)) {
				uip.getMiPass().setVisible(true);
				uip.getMiKick().setVisible(true);
				btnChatRoomSetting.setVisible(true);
				btnUserMenu.setVisible(true);
			}

			pnlUserIn.add(uip);
		}

		pnlUserIn.revalidate();
	}

	public void showUser(Client[] userList, String name) {
		pnlUserIn.removeAll();
		pnlUserIn.repaint();
		for (int i = 0; i < userList.length; i++) {
			UserInfoPanel uip = new UserInfoPanel(userList[i].getNickName(), userList[i].getGender(), oos);
			uip.setChattingRoomFrame(this);

			btnChatRoomSetting.setVisible(false);
			btnUserMenu.setVisible(false);

			// 자기 자신일 경우
			if (userList[i].getNickName().equals(name)) {
				uip.deleteJMenu();
				uip.changeColor();
				// 자기 자신인데 방장인 경우 왕관 달아줌
			}
			
			if (host.getNickName().equals(userList[i].getNickName())) {
				uip.setMaster();
			}

			// 클라이언트가 방장일 경우 모든 클라이언트의 권한 설정
			if (host.getNickName().equals(name)) {
				uip.getMiPass().setVisible(true);
				uip.getMiKick().setVisible(true);
				btnChatRoomSetting.setVisible(true);
				btnUserMenu.setVisible(true);
			}

			pnlUserIn.add(uip);
		}
		pnlUserIn.revalidate();
	}
	public void createPpd(ObjectOutputStream oos, Client[] list) {
		ppd = new TotalPeopleDlg(this, oos);
		ppd.setUserlist(list);
	}
	
}
