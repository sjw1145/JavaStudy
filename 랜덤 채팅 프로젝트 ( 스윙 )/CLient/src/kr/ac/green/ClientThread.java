package kr.ac.green;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientThread extends Thread {
	
	class InviteThread extends Thread {
		private PushDataDTO pushDataDTO;
		private ObjectOutputStream oos;
		public InviteThread(ObjectOutputStream oos, PushDataDTO pushDataDTO) {
			this.oos = oos;
			this.pushDataDTO = pushDataDTO;
		}
		
		public void push(PushDataDTO pushDataDTO) {
			String msg = (String) pushDataDTO.getData();
			int i = JOptionPane.showConfirmDialog(mainFrame, msg + "님이 초대장을 보내셨습니다.");
			try {
				if (i == JOptionPane.YES_OPTION) {
					pushDataDTO.setCode(RequestProtocol.REQUEST_USER_INVITE_ACCEPT);
					pushDataDTO.setData(msg);
					oos.writeObject(pushDataDTO);
					oos.flush();
					oos.reset();
						
//					mainFrame.dispose();

				} else {
					DataDTO dataDTO = new DataDTO(RequestProtocol.REQUEST_USER_INVITE_NOT_ACCEPT, this.getName() ,msg);
					oos.writeObject(dataDTO);
					oos.flush();
					oos.reset();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			push(pushDataDTO);
			inviteThread = null;
			return;
		}
	};

	private InviteThread inviteThread;

	private Socket socket;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private FirstFrame firstFrame;
	private MainFrame mainFrame;
	private ChattingRoomFrame chattingRoomFrame;
	private CreatRoomDialog creatRoomDialog;

	private boolean inviteFlag;

	public ClientThread(FirstFrame firstFrame, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket = socket;
		this.firstFrame = firstFrame;
		this.oos = oos;
		this.ois = ois;

	}

	@Override
	public void run() {
		Object obj = null;
		String name = getName();
		try {// 대기실 메시지 보내기
			while ((obj = ois.readObject()) != null) {
				if (obj instanceof PushDataDTO) {
					PushDataDTO temp = (PushDataDTO) obj;
					int n = temp.getCode();

					if (n == ResponseProtocol.RESPONSE_CHATTINGROOM_MSG_INPUT_OK) {
						// 채팅방 사용자들에게 메세지를 뿌려주는 것
						String msg = (String) temp.getData();
						chattingRoomFrame.inputMsg(msg);
						
					} else if (n == ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_NO) {
						// 랜덤입장에 실패했을 경우 메세지
						String msg = (String) temp.getData();
						JOptionPane.showMessageDialog(mainFrame, msg);
					} else if (n == ResponseProtocol.RESPONSE_WATINGROOM_MSG_INPUT_OK) {
						// 대기실 채팅 전송
						String msg = (String) temp.getData();
						mainFrame.inputMsg(msg);

					} else if (n == ResponseProtocol.RESPONSE_WHISPER_OK) {
						// 귓속말 전송
						JFrame frame = mainFrame;
						if (frame != null) {
							String msg = (String) temp.getData();
							mainFrame.inputMsg(msg);
						} else {
							String msg = (String) temp.getData();
							chattingRoomFrame.inputMsg(msg);
						}
					} else if (n == ResponseProtocol.RESPONSE_WHISPER_NO) {
						// 귓속말 전송 실패
						String msg = (String) temp.getData();
						JOptionPane.showMessageDialog(mainFrame, msg);
					} else if (n == ResponseProtocol.RESPONSE_ALL_USER_DATA_NO) {
						String msg = (String) temp.getData();
						JOptionPane.showMessageDialog(chattingRoomFrame, msg);
					} else if (n == ResponseProtocol.RESPONSE_CHANGE_SETTING_ROOM_NO) {
						String msg = (String) temp.getData();
						JOptionPane.showMessageDialog(creatRoomDialog, msg);
					} else if (n == ResponseProtocol.RESPONSE_CHATTINGROOM_EXPLOSION) {
						Client host = chattingRoomFrame.getHost();
						chattingRoomFrame.dispose();
						String msg = (String) temp.getData();
						if (!host.getNickName().equals(getName())) {
							JOptionPane.showMessageDialog(mainFrame, msg);
						}
						mainFrame = new MainFrame(oos);
					} 
					else if (n == ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY) {
						if(inviteThread != null) {
							temp.setCode(RequestProtocol.REQUEST_USER_INVITE_OVERLAP);
							try {
								oos.writeObject(temp);
								oos.flush();
								oos.reset();
							} catch(Exception e) {
								e.printStackTrace();
							}
							
						} else {
							inviteThread = new InviteThread(oos,temp);
							inviteThread.setName(getName());
							inviteThread.start();
						}
						
					} else if (n == ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_NO) {
						String requestUser = (String) temp.getData();
						chattingRoomFrame.inputMsg(requestUser);
					} else if (n == ResponseProtocol.RESPONSE_MASTER_ENTERUST_NO) {
						String msg = (String) temp.getData();
						chattingRoomFrame.inputMsg(msg);
					} else if (n == ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_FALSE) {
						String msg = (String) temp.getData();
						JOptionPane.showMessageDialog(chattingRoomFrame, msg);
					} else if (n == ResponseProtocol.RESPONSE_USER_FORCED_EXIT_NO) {
						String msg = (String) temp.getData();
						chattingRoomFrame.inputMsg(msg);
					} else if (n == ResponseProtocol.RESPONSE_USER_INVITE_OVERLAP_MSG) {
						String msg = (String) temp.getData();
						chattingRoomFrame.inputMsg(msg);
					}
					// 대기실 입장
				} else if (obj instanceof WaitingRoomUpdateDTO) {
					WaitingRoomUpdateDTO wru = (WaitingRoomUpdateDTO) obj;
					int n = wru.getCode();
					// 클라이언트가 처음 로그인 했을 때 1100
					if (n == ResponseProtocol.RESPONSE_NICK_CHECK_OK) {

						mainFrame = new MainFrame(oos);
						mainFrame.showUser(wru, name);
						mainFrame.showRoomList(wru);
						firstFrame.dispose();
						firstFrame = null;

					} else if (n == ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_UPDATE
							|| n == ResponseProtocol.RESPONSE_NICK_CHECK_NOTIFY
							|| n == ResponseProtocol.RESPONSE_CREATE_CHATTINGROOM_OK
							|| n == ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_UPDATE_UI
							|| n == ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE
							|| n == ResponseProtocol.RESPONSE_DUE_TO_SETTING_ROOM_WAITINGROOM_UPDATE
							|| n == ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE
							|| n == ResponseProtocol.RESPONSE_USER_OUT_UPDATE
							|| n == ResponseProtocol.RESPONSE_DUE_TO_USER_INVITE_WAITINGROOM_UPDATE) {
						// 사용자가 채팅방 입장 했을 때 대기실 유저들의 UI 업데이트
						mainFrame.showUser(wru, name);
						mainFrame.showRoomList(wru);

					} else if (n == ResponseProtocol.RESPONSE_CHATTINGROOM_EXIT_OK
							|| n == ResponseProtocol.RESPONSE_USER_FORCED_EXIT_OK) {
						// 요청한 클라이언트가 채팅방에서 나왔을 경우 실행하는 거임
						chattingRoomFrame.dispose();
						chattingRoomFrame = null;

						mainFrame = new MainFrame(oos);
						mainFrame.showUser(wru, name);
						mainFrame.showRoomList(wru);
						
						if (n == ResponseProtocol.RESPONSE_USER_FORCED_EXIT_OK) {
							new KickImgDlg(mainFrame);
						}

					} else if (n == ResponseProtocol.RESPONSE_USER_OUT_OK) {
						socket.close();
						mainFrame.exit();
					}

					// 채팅방 생성 했을 때의 채팅방 정보를 받는 것
				} else if (obj instanceof ChattingRoomSubInfo) {
					ChattingRoomSubInfo crs = (ChattingRoomSubInfo) obj;
					Client host = crs.getHost();
					int currentC = crs.getRoomCurrentCount();
					int limitC = crs.getRoomLimitCount();
					int roomNum = crs.getRoomNumber();
					String roomTitle = crs.getRoomTitle();
					String roomPassword = crs.getRoomPassword();
					chattingRoomFrame = new ChattingRoomFrame(currentC, limitC, roomNum, roomTitle, oos);
					chattingRoomFrame.setPassword(roomPassword);
					chattingRoomFrame.addPanel(host.getNickName(), host.getGender());
					chattingRoomFrame.useSetting();
					chattingRoomFrame.setHost(host);
					chattingRoomFrame.setMyName(host.getNickName());

					mainFrame.dispose();
					mainFrame = null;

				} else if (obj instanceof AllUserDataDTO) {
					AllUserDataDTO ado = (AllUserDataDTO) obj;
					int code = ado.getCode();
					if (code == ResponseProtocol.RESPONSE_ALL_USER_DATA_OK) {
						Client[] list = (Client[])ado.getWaitingRoomUserList();
						if(chattingRoomFrame.getPpd() == null) {
							chattingRoomFrame.createPpd(oos, list);
						}
						/*
						TotalPeopleDlg tpd = new TotalPeopleDlg(chattingRoomFrame, oos);
						Client[] list = ado.getWaitingRoomUserList();
						if (list != null) {
							tpd.setUserlist(list);
						}
						*/
					}
				} else if (obj instanceof RoomUserListDTO) {
					RoomUserListDTO rul = (RoomUserListDTO) obj;
					int code = rul.getCode();
					// (요청 클라이언트) 채팅룸 입장했을 때 유저목록 갱신
					if (code == ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_UI_UPDATE) {
						// 채팅방에 유저가 들어왔을 때 사용자 목록을 업데이트
						chattingRoomFrame.showUser(rul, name);
					}

				} else if (obj instanceof DataDTO) {
					DataDTO ddto = (DataDTO) obj;
					int code = ddto.getCode();

					// 채팅방 입장, 랜덤 입장, 초대 수락 했을 경우
					if (code == ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_OK
							|| code == ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_OK
							|| code == ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_OK) {
						// ChattingRoomSubInfo, userListTemp
						ChattingRoomSubInfo info = (ChattingRoomSubInfo) ddto.getData()[0];
						Client[] list = (Client[]) ddto.getData()[1];

						// 메인 프레임 닫기
						String title = "[" + info.getRoomNumber() + "번방] " + info.getRoomTitle();
						chattingRoomFrame = new ChattingRoomFrame();
						System.out.println("호스트 " + info.getHost().getNickName());
						chattingRoomFrame.setHost(info.getHost());
						chattingRoomFrame.setRoomNum(info.getRoomNumber());
						chattingRoomFrame.setRoomTitle(info.getRoomTitle());
						chattingRoomFrame.setTitle(title);
						chattingRoomFrame.setPassword(info.getRoomPassword());
						chattingRoomFrame.setCurrentC(info.getRoomCurrentCount());
						System.out.println("현재인원 : " + info.getRoomCurrentCount());
						chattingRoomFrame.setLimitC(info.getRoomLimitCount());
						chattingRoomFrame.showUser(list, name);
						chattingRoomFrame.notUserMenu();
						chattingRoomFrame.setOos(oos);
						chattingRoomFrame.setMyName(getName());

						if(mainFrame != null) {
							mainFrame.dispose();
							mainFrame = null;
						}


					} else if (code == ResponseProtocol.RESPONSE_MASTER_ENTRUST_DELIVERY) {
						String hostName = (String) ddto.getData()[0];
						String message = (String) ddto.getData()[1];

						int select = JOptionPane.showConfirmDialog(chattingRoomFrame, message);
						if (select == JOptionPane.YES_OPTION) {

							oos.writeObject(new PushDataDTO(RequestProtocol.REQUEST_MASTER_ENTRUST_RECEIVE, hostName));
							oos.flush();
							oos.reset();
						} else if (select == JOptionPane.NO_OPTION) {
							oos.writeObject(
									new PushDataDTO(RequestProtocol.REQUEST_MASTER_ENTRUST_NOT_RECEIVE, hostName));
							oos.flush();
							oos.reset();
						}
					} else if (code == ResponseProtocol.RESPONSE_MASTER_ENTRUST_OK_NOTIFY) {
						// data[0] = 클라이언트배열 , data[1] host data[2] = String 메세지
						Client[] uesrList = (Client[]) ddto.getData()[0];
						Client host = (Client) ddto.getData()[1];
						String message = (String) ddto.getData()[2];
						chattingRoomFrame.setHost(host);
						chattingRoomFrame.showUser(uesrList, name);
						chattingRoomFrame.inputMsg(message);
					}

					if (code == ResponseProtocol.RESPONSE_CHATTINGROOM_NOTIFY
							|| code == ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_NOTIFY
							|| code == ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_NOTIFY
							|| code == ResponseProtocol.RESPONSE_USER_INVITE_NOTIFY) {
						Client[] userList = (Client[]) ddto.getData()[0];
						String msg = (String) ddto.getData()[1];
						chattingRoomFrame.inputMsg(msg);
						chattingRoomFrame.showUser(userList, name);
						chattingRoomFrame.setCurrentC(userList.length);
						System.out.println("인원 : " + chattingRoomFrame.getCurrentC());
					} else if (code == ResponseProtocol.RESPONSE_CHANGE_SETTING_ROOM_NOTIFY) {
						ChattingRoomSubInfo crs = (ChattingRoomSubInfo) ddto.getData()[0];
						String msg = (String) ddto.getData()[1];
						String title = crs.getRoomTitle();
						int currentC = crs.getRoomCurrentCount();
						int limitC = crs.getRoomLimitCount();
						int roomNum = crs.getRoomNumber();
						String password = crs.getRoomPassword();
						chattingRoomFrame.setChattingRoom(title, currentC, limitC, roomNum, password);
						chattingRoomFrame.inputMsg(msg);
					} else if (code == ResponseProtocol.RESPONSE_USER_FORCED_EXIT_NOTIFY) {
						Client[] list = (Client[]) ddto.getData()[0];
						String msg = (String) ddto.getData()[1];
						chattingRoomFrame.showUser(list, name);
						chattingRoomFrame.inputMsg(msg);
					}
				} else if (obj instanceof NickNameOverlapException) {
					NickNameOverlapException nickNameOverlapException = (NickNameOverlapException) obj;
					JOptionPane.showMessageDialog(firstFrame, nickNameOverlapException.getMessage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

 