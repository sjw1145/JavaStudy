package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ChattingRoomPanel extends JPanel {
	private JLabel lblPeople;
	private JLabel lblLock;
	private JLabel lblRoomTitle;
	private int roomNumber;
	private String roomTitle;
	private int currentCount;
	private int limitCount;
	private String password;
	private ObjectOutputStream oos;
	
	public ChattingRoomPanel(int roomNumber, String roomTitle, int currentCount, int limitCount, String password, ObjectOutputStream oos) {
		this.roomNumber = roomNumber;
		this.roomTitle = roomTitle;
		this.currentCount = currentCount;
		this.limitCount = limitCount;
		this.password = password;
		this.oos = oos;
		init();
		addListeners();
		setDisplay();
	}

	public void init() {
		lblPeople = new JLabel("참가인원 " + currentCount + " / " + limitCount);
		setLock(password);
	}

	public void addListeners() {
		ChattingRoomPanel.this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent me) {
				showChattingRoomJoin(me);
			}
		});
	}
	public void setLock(String lock){
			if(password.equals("")){
			lblLock = new JLabel("공개", new ImageIcon("unlock.png"), SwingConstants.CENTER);
		} else{
			lblLock = new JLabel("비공개",new ImageIcon("lock.png"), SwingConstants.CENTER);
		}
	}	
	
	public void showChattingRoomJoin(MouseEvent me) {
		ChattingRoomInfoDTO chattingRoomInfoDTO;
		if(limitCount > currentCount) {
			 chattingRoomInfoDTO = new ChattingRoomInfoDTO(RequestProtocol.REQUEST_ENTER_CHATTINGROOM,
					 roomNumber, roomTitle,password,limitCount);
			if(password.equals("")){				 
				  try {
					oos.writeObject(chattingRoomInfoDTO);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				new PassWordInputDialog(this.password, chattingRoomInfoDTO, oos);
			}
		} else{
			JOptionPane.showMessageDialog(ChattingRoomPanel.this, "인원이 가득!!");
		}
	}

	public void setDisplay() {
		setPreferredSize(new Dimension(500, 30));
//		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBorder(new LineBorder(Color.BLACK, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout());

		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(new Color(255, 255, 255));
		JPanel pnlEast = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlEast.getLayout();
		flowLayout.setVgap(6);
		pnlEast.setBackground(new Color(255, 255, 255));

		pnlWest.add(lblLock);

		pnlEast.add(lblPeople);

		add(pnlWest, BorderLayout.WEST);
		lblRoomTitle = new JLabel("[" + roomNumber + "] \t" + "방제목 : " + roomTitle);
		FlowLayout fl_pnlCenter = new FlowLayout(FlowLayout.LEFT);
		fl_pnlCenter.setVgap(6);
		JPanel pnlCenter = new JPanel(fl_pnlCenter);
		pnlCenter.setBackground(new Color(255, 255, 255));
		
				// pnlCenter.add(l);
				pnlCenter.add(lblRoomTitle);
				add(pnlCenter, BorderLayout.CENTER);
		add(pnlEast, BorderLayout.EAST);

	}
}

 