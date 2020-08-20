package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreatRoomDialog extends JDialog {
	private JLabel roomTitle;
	private JLabel password;
	private MainFrame mainf;
	private ChattingRoomFrame crf;
	private int roomCurrentCount;
	private JLabel peopleLimit;
	private JComboBox<String> cbxPeopleLimit;

	private JTextField tfRoomTitle;
	private JTextField tfpassword;

	private JButton btnConfirm;
	private JButton btnCancel;
	private int roomNum;

	private JButton btnChange;
	private String[] maxPeople = { "1", "2", "3", "4", "5" };
	private ObjectOutputStream oos;

	// ============================
	private JPanel pnlAll = new JPanel(new BorderLayout());
	private JPanel pnlCenter = new JPanel(new GridLayout(0, 1));
	private JPanel pnlCenter1;
	private JPanel pnlCenter2;
	private JPanel pnlCenter3;
	private JPanel pnlSouth = new JPanel();

	public CreatRoomDialog(MainFrame mainf, ObjectOutputStream oos) {
		super(mainf, "방생성", true);
		this.mainf = mainf;
		this.oos = oos;
		init();
		pnlSouth.add(btnConfirm);
		setDisplay();
		addListeners();
		setTitle("채팅방생성");
		showFrame();

	}

	public CreatRoomDialog(ChattingRoomFrame crf, ObjectOutputStream oos, String tfRoomTitle, String tfpassword,
			int cbxPeopleLimit, int roomNum) {
		super(crf);
		init();
		pnlSouth.add(btnChange);
		setDisplay();
		addListeners();
		setTitle("채팅방변경");
		showFrame();
		this.roomNum = roomNum;
		this.crf = crf;
		this.oos = oos;
		
		this.tfRoomTitle.setText(tfRoomTitle);
		this.tfpassword.setText(tfpassword);
		
		String sss = this.cbxPeopleLimit.getItemAt(cbxPeopleLimit - 1);
		this.cbxPeopleLimit.setSelectedItem(sss);

	}

	public int getRoomCurrentCount() {
		return roomCurrentCount;
	}

	public void setRoomCurrentCount(int roomCurrentCount) {
		this.roomCurrentCount = roomCurrentCount;
	}

	private void init() {

		roomTitle = new JLabel("방 제목");
		roomTitle.setPreferredSize(new Dimension(60, 20));
		tfRoomTitle = new JTextField(15);
		password = new JLabel("비밀번호");
		password.setPreferredSize(new Dimension(60, 20));
		tfpassword = new JTextField(15);

		peopleLimit = new JLabel("인원제한");
		peopleLimit.setPreferredSize(new Dimension(60, 20));
		cbxPeopleLimit = new JComboBox<String>(maxPeople);
		btnConfirm = new JButton("확인");
		btnChange = new JButton("변경");
		btnCancel = new JButton("취소");

	}

	private void setDisplay() {

		pnlCenter1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlCenter2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlCenter3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		pnlCenter1.add(roomTitle);
		pnlCenter1.add(tfRoomTitle);
		pnlCenter2.add(password);
		pnlCenter2.add(tfpassword);

		pnlCenter3.add(peopleLimit);
		pnlCenter3.add(cbxPeopleLimit);

		// pnlSouth.add(btnConfirm);
		// pnlSouth.add(btnChange);
		pnlSouth.add(btnCancel);

		pnlCenter.add(pnlCenter1);
		pnlCenter.add(pnlCenter2);
		pnlCenter.add(pnlCenter3);
		pnlAll.add(pnlCenter, BorderLayout.CENTER);
		pnlAll.add(pnlSouth, BorderLayout.SOUTH);
		add(pnlAll, BorderLayout.CENTER);
	}

	private void addListeners() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
//				String str = (String) cbxPeopleLimit.getSelectedItem();
				ChattingRoomInfoDTO crid = new ChattingRoomInfoDTO(RequestProtocol.REQUEST_CREATE_CHATTINGROOM, roomNum,
						tfRoomTitle.getText(), tfpassword.getText(),
						Integer.parseInt(cbxPeopleLimit.getSelectedItem().toString()));
				crid.setRoomCurrentCount(roomCurrentCount);
				if (obj == btnConfirm) {
					try {
						oos.writeObject(crid);
						oos.flush();
						oos.reset();
					} catch (IOException e1) {
						e1.printStackTrace();
					}


				} else if (obj == btnChange) {
					crid.setCode(RequestProtocol.REQUEST_CHANGE_SETTING_ROOM);
					try {
						oos.writeObject(crid);
						oos.flush();
						oos.reset();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				dispose();
				if(crf != null) {
					crf.setDlg();
				}
			}

		};
		btnConfirm.addActionListener(listener);
		btnChange.addActionListener(listener);
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(crf != null) {
					crf.setDlg();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				if(crf != null) {
					crf.setDlg();
				}
			}
		});
	}

	private void showFrame() {
		setIconImage(new ImageIcon("play.png").getImage());
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

 