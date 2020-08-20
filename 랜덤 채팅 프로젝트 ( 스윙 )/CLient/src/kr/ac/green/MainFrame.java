package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

public class MainFrame extends JFrame {

	private Socket sock;

	private JTextArea taChat;
	private JTextField tfChat;
	private JButton btnCreate;
	private JButton btnRandom;
	private JButton btnEnter;
	private ObjectOutputStream oos;
	private FirstFrame icon;
	public ObjectOutputStream getOos() {
		return oos;
	}

	private JPanel pnlChat;
	private JPanel pnlUser;
	private JPanel pnlMe;
	private JPanel pnlChatRoom;

	public MainFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	public MainFrame(ObjectOutputStream oos) {
		this();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.oos = oos;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public Socket getSock() {
		return sock;
	}

	public void init() {
		taChat = new JTextArea(8, 40);
		taChat.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		taChat.setEditable(false);
		taChat.setOpaque(true);
		taChat.setForeground(Color.BLACK);
		tfChat = new JTextField(40);
		btnCreate = new JButton("방생성");
		btnRandom = new JButton("랜덤입장");
		btnEnter = new JButton("전송");
		pnlUser = new JPanel();
		System.out.println(pnlUser);
		pnlMe = new JPanel();
		pnlUser.setPreferredSize(new Dimension(120, 0));
		pnlUser.setOpaque(true);
		pnlUser.setBackground(Color.WHITE);
		pnlChatRoom = new JPanel();
		pnlChatRoom.setOpaque(true);
		pnlChatRoom.setBackground(Color.WHITE);
		pnlChatRoom.setPreferredSize(new Dimension(260, 0));
		pnlMe.setPreferredSize(new Dimension(150, 0));
		taChat.setTabSize(4);
		taChat.setLineWrap(true);
		taChat.setWrapStyleWord(true);
		

	}

	public void showUser(WaitingRoomUpdateDTO wru, String name) {
		int width = pnlUser.getWidth();
		int height = 0;
		Client[] c = wru.getWaitingRoomUserList();
		pnlUser.removeAll();
		pnlUser.repaint();

		for (int i = 0; i < c.length; i++) {
			UserInfoPanel uip = new UserInfoPanel(c[i].getNickName(), c[i].getGender(), oos);
			uip.setMainFrame(this);
			if (c[i].getNickName().equals(name) == true) {
				pnlMe.removeAll();
				uip.deleteJMenu();
				uip.changeColor();
				pnlMe.add(uip);

			} else {
				pnlUser.add(uip);
				pnlUser.setPreferredSize(new Dimension(width, height += 35));
			}

		}
		pnlUser.revalidate();
	}

	public void showRoomList(WaitingRoomUpdateDTO wru) {
		int width = pnlChatRoom.getWidth();
		int height = 0;
		pnlChatRoom.removeAll();
		pnlChatRoom.repaint();
		ChattingRoomSubInfo[] getChattingRoomSubInfo = wru.getChattingRoomSubInfo();

		for (int i = 0; i < getChattingRoomSubInfo.length; i++) {
			int roomNumber = getChattingRoomSubInfo[i].getRoomNumber();
			String roomTitle = getChattingRoomSubInfo[i].getRoomTitle();
			int currentCount = getChattingRoomSubInfo[i].getRoomCurrentCount();
			int limitCount = getChattingRoomSubInfo[i].getRoomLimitCount();
			String password = getChattingRoomSubInfo[i].getRoomPassword();
			pnlChatRoom.add(new ChattingRoomPanel(roomNumber, roomTitle, currentCount, limitCount, password, oos));
			pnlChatRoom.setPreferredSize(new Dimension(width, height += 35));
			System.out.println("위드" + width + "\t헤잇 " + height);
		}
		pnlChatRoom.revalidate();
	}

	public void setDisplay() {
		JPanel mainPnl = new JPanel();
		mainPnl.setOpaque(true);
		mainPnl.setBackground(Color.WHITE);
		JPanel mainEastPnl = new JPanel(new BorderLayout());
		mainEastPnl.setOpaque(true);
		mainEastPnl.setBackground(Color.WHITE);
		mainPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPnl.setLayout(new BorderLayout(0, 0));

		JPanel pnlChatInput = new JPanel(new BorderLayout());
		pnlChatInput.setOpaque(true);
		pnlChatInput.setBackground(Color.WHITE);
		pnlChatInput.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChatInput.add(tfChat, BorderLayout.CENTER);
		pnlChatInput.add(btnEnter, BorderLayout.EAST);

		JPanel pnlButton = new JPanel(new BorderLayout());
		pnlButton.setOpaque(true);
		pnlButton.setBackground(Color.WHITE);
		JPanel pnlButtonWest = new JPanel();
		pnlButtonWest.setOpaque(true);
		pnlButtonWest.setBackground(Color.WHITE);
		JPanel pnlButtonEast = new JPanel();
		pnlButtonEast.setOpaque(true);
		pnlButtonEast.setBackground(Color.WHITE);
		pnlButtonEast.add(btnCreate);
		pnlButtonEast.add(btnRandom);

		pnlButton.add(pnlButtonWest, BorderLayout.WEST);
		pnlButton.add(pnlButtonEast, BorderLayout.EAST);

		pnlChat = new JPanel(new BorderLayout());
		pnlChat.setOpaque(true);
		pnlChat.setBackground(Color.WHITE);

		JScrollPane taScroll = new JScrollPane(taChat);
		taScroll.setOpaque(true);
		taScroll.setBackground(Color.WHITE);

		taScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		taScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taScroll.getVerticalScrollBar().setValue(taScroll.getVerticalScrollBar().getMaximum());

		pnlChat.add(pnlButton, BorderLayout.NORTH);
		pnlChat.add(taScroll, BorderLayout.CENTER);
		pnlChat.add(pnlChatInput, BorderLayout.SOUTH);

		JScrollPane userScroll = new JScrollPane(pnlUser);
		userScroll.setOpaque(true);
		userScroll.setBackground(Color.WHITE);
		pnlMe.setPreferredSize(new Dimension(150, 80));
		pnlMe.setOpaque(true);
		pnlMe.setBackground(Color.WHITE);
		userScroll.setPreferredSize(new Dimension(150, 200));

		pnlMe.setBorder(new TitledBorder("나"));
		userScroll.setBorder(new TitledBorder("대기자 목록"));
		userScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		userScroll.setOpaque(true);
		userScroll.setBackground(Color.WHITE);

		mainEastPnl.add(pnlMe, BorderLayout.NORTH);
		mainEastPnl.add(userScroll, BorderLayout.CENTER);
		mainPnl.add(pnlChat, BorderLayout.SOUTH);
		mainPnl.add(mainEastPnl, BorderLayout.EAST);

		setContentPane(mainPnl);

		JPanel panel = new JPanel();

		mainPnl.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());

		JScrollPane chatScroll = new JScrollPane(pnlChatRoom);
		chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatScroll.setPreferredSize(new Dimension(300, 0));
		panel.add(chatScroll);
		chatScroll.setOpaque(true);
		chatScroll.setBackground(Color.WHITE);

		chatScroll.setBorder(new TitledBorder("채팅방 목록"));
	}

	public void addListeners() {
		tfChat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (tfChat.getText().equals("")) {
						} else {
							PushDataDTO dataDTO;
							if (tfChat.getText().toString().indexOf("/w") == 0) {
								dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WHISPER, tfChat.getText());
							} else {
								dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WATINGROOM_MSG_INPUT,
										tfChat.getText());
							}
							if(tfChat.getText().length() >= 100 ) {
								JOptionPane.showMessageDialog(MainFrame.this, "100자 이내로입력해주세요");
								tfChat.setText("");
						    }else {
						
							tfChat.setText("");
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
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btnCreate) {
					new Dialog(new CreatRoomDialog(MainFrame.this, oos));
				}
			}
		});
		btnRandom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btnRandom) {
					randomRoomJoin();
				}
			}
		});
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tfChat.getText().equals("")) {
					} else {
						PushDataDTO dataDTO;
						if (tfChat.getText().toString().indexOf("/w") == 0) {
							dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WHISPER, tfChat.getText());
						} else {
							dataDTO = new PushDataDTO(RequestProtocol.REQUEST_WATINGROOM_MSG_INPUT, tfChat.getText());
						}

						if(tfChat.getText().length() >= 100 ) {
							JOptionPane.showMessageDialog(MainFrame.this, "100자 이내로입력해주세요");
							tfChat.setText("");
						}else {
						
						tfChat.setText("");
						oos.writeObject(dataDTO);
						taChat.setCaretPosition(taChat.getDocument().getLength());
						oos.flush();
						oos.reset();
						}
					}

				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}

		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				Integer out = RequestProtocol.REQUEST_USER_OUT;
				try {
					oos.writeObject(out);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void randomRoomJoin() {
		Integer random = RequestProtocol.REQUEST_RANDOM_ENTER_CHATTINGROOM;
		try {
			oos.writeObject(random);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		System.exit(0);
	}

	public void showFrame() {
		setIconImage(new ImageIcon("play.png").getImage());
		setTitle("채팅창");
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		tfChat.requestFocus();
	}

	public void inputMsg(String msg) {
			taChat.append(msg + "\n"); 
			taChat.setCaretPosition(taChat.getDocument().getLength());
	}

	public void addTfInput(String msg) {
		this.tfChat.setText(msg);
	}
}

 

