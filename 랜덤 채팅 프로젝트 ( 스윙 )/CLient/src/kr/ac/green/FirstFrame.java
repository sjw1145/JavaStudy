package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FirstFrame extends JFrame {
	private JLabel lblNick;
	private JLabel lblServerIp;
	private JLabel lblGender;
	private JLabel lblLogo;
	private JTextField tfNick;
	private JTextField tfServerIp;
	private JRadioButton rbtnMale;
	private JRadioButton rbtnFeMale;
	private JButton btnServerInput;
	private Client client;
	private Socket socket;

	public FirstFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
		setDisplay();
		addListeners();
		showFrame();
		client = new Client();
	}

	private void init() {
		lblNick = new JLabel("닉네임");
		lblServerIp = new JLabel("서버 IP");
		lblGender = new JLabel("성별");
		tfNick = new JTextField(12);
		tfServerIp = new JTextField(12);
		rbtnMale = new JRadioButton("남자");
		rbtnFeMale = new JRadioButton("여자");
		btnServerInput = new JButton("서버 접속");
		lblLogo = new JLabel(new ImageIcon("로고.PNG"));
	}
	
	
	private void setDisplay() {
		JPanel mainPnl = new JPanel();
		mainPnl.setBackground(new Color(254, 225, 63));
		mainPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPnl.setLayout(new BorderLayout(0, 0));

		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.setBackground(new Color(254, 225, 63));
		JPanel pnlNNickOutSide = new JPanel(new FlowLayout());
		pnlNNickOutSide.setBackground(new Color(254, 225, 63));
		JPanel pnlNNickInSide = new JPanel(new BorderLayout());
		pnlNNickInSide.setBackground(new Color(254, 225, 63));
		pnlNNickInSide.add(lblNick, BorderLayout.WEST);
		pnlNNickInSide.add(tfNick, BorderLayout.EAST);
		pnlNNickOutSide.add(pnlNNickInSide);

		JPanel pnlNServerIpOutSide = new JPanel(new FlowLayout());
		pnlNServerIpOutSide.setBackground(new Color(254, 225, 63));
		JPanel pnlNServerIpInSide = new JPanel(new BorderLayout());
		pnlNServerIpInSide.setBackground(new Color(254, 225, 63));
		pnlNServerIpInSide.add(lblServerIp, BorderLayout.WEST);
		pnlNServerIpInSide.add(tfServerIp, BorderLayout.EAST);

		pnlNServerIpOutSide.add(pnlNServerIpInSide);

		JPanel pnlNGenderOutSide = new JPanel(new FlowLayout());
		pnlNGenderOutSide.setBackground(new Color(254, 225, 63));
		JPanel pnlNGenderInSide = new JPanel(new BorderLayout());
		pnlNGenderInSide.setBackground(new Color(254, 225, 63));
		ButtonGroup group = new ButtonGroup();

		group.add(rbtnFeMale);
		group.add(rbtnMale);

		pnlNGenderOutSide.add(lblGender, BorderLayout.WEST);
		pnlNGenderInSide.add(rbtnMale, BorderLayout.CENTER);
		pnlNGenderInSide.add(rbtnFeMale, BorderLayout.EAST);

		pnlNGenderOutSide.add(pnlNGenderInSide);

		pnlNorth.add(pnlNNickOutSide, BorderLayout.NORTH);
		pnlNorth.add(pnlNServerIpOutSide, BorderLayout.CENTER);
		pnlNorth.add(pnlNGenderOutSide, BorderLayout.SOUTH);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(254, 225, 63));
		pnlCenter.add(btnServerInput);

		mainPnl.add(lblLogo, BorderLayout.NORTH);
		mainPnl.add(pnlNorth, BorderLayout.CENTER);
		mainPnl.add(pnlCenter, BorderLayout.SOUTH);

		setContentPane(mainPnl);
	}

	private void addListeners() {
		ActionListener listener = (ae) -> {
			Object src = ae.getSource();
			if (btnServerInput == src) {
				// 입력사항에 이상이없으면 배열에 넣기
				if (tfNick.getText().equals(" ") || tfNick.getText().length() == 0) {
					JOptionPane.showMessageDialog(this, "닉네임을 입력해주세요");
					return;
				}
				
				if (tfNick.getText().length() > 6) {
					JOptionPane.showMessageDialog(this, "6글자 이하로 입력해주세요");
					return;
				}

				if (tfServerIp.getText().equals(" ") || tfServerIp.getText().length() == 0) {
					JOptionPane.showMessageDialog(this, "서버를 입력해주세요");
					return;
				}

				if (!rbtnMale.isSelected() && !rbtnFeMale.isSelected()) {
					JOptionPane.showMessageDialog(this, "성별을 선택하세요");
					return;
				}
				client.setNickName(tfNick.getText());

				String[] data = new String[2];

				data[0] = new String(client.getNickName());
				data[1] = new String(client.getGender());

				try {
					socket = new Socket(tfServerIp.getText(), 10010);
					if (socket.isConnected()) {
						// 서버와 연결된 스트림
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

						// 객체 보내라
						oos.writeObject(new PushDataDTO(RequestProtocol.REQUEST_NICK_CHECK, data));
						oos.flush();
						oos.reset();

						// 리스너 만들고
						ClientThread clientThread = new ClientThread(this, socket, oos, ois);
						clientThread.setName(data[0]);
						clientThread.start();
					}

				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(this, "호스트가 존재하지 않음");
				} catch (ConnectException e) {
					JOptionPane.showMessageDialog(this, "서버를 확인하세요");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		btnServerInput.addActionListener(listener);
		ActionListener alsnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String s = ae.getActionCommand();

				if (s.equals(rbtnMale.getText())) {
					client.setGender(rbtnMale.getText());
				}
				if (s.equals(rbtnFeMale.getText())) {
					client.setGender(rbtnFeMale.getText());
				}
			}
		};
		rbtnMale.addActionListener(alsnr);
		rbtnFeMale.addActionListener(alsnr);
	}

	private void showFrame() {
		setIconImage(new ImageIcon("play.png").getImage());
		setTitle("채팅서버 로그인");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}


	public static void main(String[] args) {
		new FirstFrame();
	}
}

