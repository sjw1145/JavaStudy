package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class PassWordInputDialog extends JDialog {
	private JLabel lblTitle;
	private JLabel lblPw;
	private JPasswordField pfPw;
	private JButton btnOk;
	private JButton btnCancel;
	private String password;
	ChattingRoomInfoDTO chattingRoomInfoDTO;
	private ObjectOutputStream oos;

	public PassWordInputDialog(String password, ChattingRoomInfoDTO chattingRoomInfoDTO, ObjectOutputStream oos) {
		this.password = password;
		this.chattingRoomInfoDTO = chattingRoomInfoDTO;
		this.oos = oos;
		init();
		addListener();
		setDisplay();
		showFrame();

	}

	private void init() {
		lblTitle = new JLabel("해당 채팅방은 잠금 상태입니다.");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 12));
		lblPw = new JLabel("비밀번호");
		pfPw = new JPasswordField(15);
		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
	}

	private void addListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		};
		btnCancel.addActionListener(listener);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((String.valueOf(pfPw.getPassword()).equals(password))) {
					try {
						System.out.println("롸!~~~~~~~~~~~~");
						oos.writeObject(chattingRoomInfoDTO);
						oos.flush();
						oos.reset();
						dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(PassWordInputDialog.this, "비밀번호가 틀렸습니다.");
				}
			}
		});
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel();
		JPanel pnlCenter = new JPanel(new BorderLayout(0, 0));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlNorth.add(lblTitle);

		pnlCenter.add(lblPw, BorderLayout.WEST);
		pnlCenter.add(pfPw, BorderLayout.EAST);

		pnlSouth.add(btnOk);
		pnlSouth.add(btnCancel);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}

	private void showFrame() {
		setTitle("비밀번호를 입력해주세요.");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}
 
