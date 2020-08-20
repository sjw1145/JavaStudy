package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class ServerListener extends Thread {
	private HashMap<Client, ObjectOutputStream> clientList;
	private Vector<ChattingRoomInfo> roomList;
	private WaitingRoomUserList waitingRoomUserList;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private static String filter;
	private Socket clientSocket;
	private boolean stopFlag;
	private boolean inviteFlag;

	// 생성자
	public ServerListener(HashMap<Client, ObjectOutputStream> clientList, Vector<ChattingRoomInfo> roomList,
			WaitingRoomUserList waitingRoomUserList, Socket clientSocket) {

		this.clientList = clientList;
		this.roomList = roomList;
		this.waitingRoomUserList = waitingRoomUserList;
		this.clientSocket = clientSocket;

		try {
			ois = new ObjectInputStream(clientSocket.getInputStream());
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream("message.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			filter = br.readLine();
			br.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 클라이언트 생성
	private synchronized void createClient(PushDataDTO pushDataDTO) {

		String[] clientData = (String[]) pushDataDTO.getData();

		// 닉네임 , 성별
		String clientNickName = clientData[0];
		String gender = clientData[1];

		// 닉네임 중복 체크
		if (nickCheck(clientNickName)) {
			try {
				// 만약 닉네임이 존재 할 경우
				oos.writeObject(new NickNameOverlapException(clientNickName));
				oos.flush();
				oos.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			// 클라이언트 생성
			Client client = new Client(clientNickName, gender);

			synchronized (waitingRoomUserList) {
				// 대기실 유저 1명 추가
				waitingRoomUserList.addClient(client);
			}

			synchronized (clientList) {
				// 클라이언트 추가
				clientList.put(client, oos);
			}

			// RESPONSE_NICK_CHECK_OK
			WaitingRoomUpdateDTO data = returnWaitingRoomUpdateDTO(ResponseProtocol.RESPONSE_NICK_CHECK_OK);

			try {
				if (data != null) {
					oos.writeObject(data);
					oos.flush();
					oos.reset();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Thread 의 닉네임을 클라이언트 닉네임으로 설정한다.
			setName(clientNickName);

			Client[] waitingUserList = waitingRoomUserList.returnClient();
			try {
				data.setCode(ResponseProtocol.RESPONSE_NICK_CHECK_NOTIFY);
				if (data != null && waitingUserList != null) {
					for (int i = 0; i < waitingUserList.length; i++) {
						if (waitingUserList[i].equals(clientNickName) == false) {
							ObjectOutputStream tempOos = clientList.get(waitingUserList[i]);

							tempOos.writeObject(data);
							tempOos.flush();
							tempOos.reset();
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 대기실 채팅방 목록, 사용자 목록을 만들어서 리턴
	private WaitingRoomUpdateDTO returnWaitingRoomUpdateDTO(int code) {
		Client[] userList = waitingRoomUserList.returnClient();
		int roomSize = roomList.size();
		ChattingRoomSubInfo[] chattingRoomSubInfo = new ChattingRoomSubInfo[roomList.size()];
		for (int i = 0; i < roomSize; i++) {
			ChattingRoomInfo temp = roomList.get(i);

			chattingRoomSubInfo[i] = new ChattingRoomSubInfo(temp.getRoomNumber(), temp.getRoomTitle(),
					temp.getRoomCurrentCount(), temp.getRoomLimitCount());
			chattingRoomSubInfo[i].setRoomPassword(temp.getRoomPassword());
		}
		WaitingRoomUpdateDTO data = new WaitingRoomUpdateDTO(code, userList, chattingRoomSubInfo);

		return data;
	}

	// 닉네임 중복체크
	private synchronized boolean nickCheck(String nickName) {
		Set<Client> clients = clientList.keySet();

		Iterator<Client> itr = clients.iterator();

		while (itr.hasNext()) {
			Client temp = itr.next();
			if (temp.equals(nickName)) {
				return true;
			}
		}
		return false;
	}

	// 대기실 메세지 보내기
	private synchronized boolean msgSend(PushDataDTO pushDataDTO) {
		String nickName = getName();
		Client[] temp = waitingRoomUserList.returnClient();
		String msg = (String) pushDataDTO.getData();
		String result = msg.replaceAll(filter, "*");
		String data = "[" + nickName + "]" + " : " + result;

		pushDataDTO.setCode(ResponseProtocol.RESPONSE_WATINGROOM_MSG_INPUT_OK);
		pushDataDTO.setData(data);
		synchronized (clientList) {
			for (int i = 0; i < temp.length; i++) {
				ObjectOutputStream tempOos = clientList.get(temp[i]);
				if (tempOos != null) {
					try {
						tempOos.writeObject(pushDataDTO);
						tempOos.flush();
						tempOos.reset();
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
			return true;
		}
	}

	// 채팅방 메세지 보내기
	private synchronized boolean roomMsgSend(PushDataDTO pushDataDTO) {

		String nickName = getName();

		Client client = null;

		// 메세지
		String data = msgChange(nickName, pushDataDTO);
		pushDataDTO.setCode(ResponseProtocol.RESPONSE_CHATTINGROOM_MSG_INPUT_OK);

		for (int i = 0; i < roomList.size(); i++) {
			ChattingRoomInfo chattingRoomInfo = roomList.get(i);

			client = chattingRoomInfo.searchUser(nickName);
			if (client != null) {
				Client[] userList = chattingRoomInfo.getUserList();

				pushDataDTO.setData(data);

				int count = chattingRoomInfo.getUserNumber();
				for (int idx = 0; idx < count; idx++) {
					try {
						ObjectOutputStream tempOos = clientList.get(userList[idx]);
						tempOos.writeObject(pushDataDTO);
						tempOos.flush();
						tempOos.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			}
		}

		return false;
	}

	// 채팅방 생성
	private synchronized void createRoom(ChattingRoomInfoDTO chattingRoomInfoDTO) {
		String clientNickName = getName();
		Client host = waitingRoomUserList.getClient(clientNickName);
		if (host != null) {
			int limitCount = chattingRoomInfoDTO.getRoomLimitCount();

			ChattingRoomInfo chattingRoomInfo = new ChattingRoomInfo(limitCount);

			int ranDomRoomNumber = 0;
			for (ChattingRoomInfo temp : roomList) {
				int num = temp.getRoomNumber();
				ranDomRoomNumber = (int) (Math.random() * 100) + 1;
				if (num != ranDomRoomNumber) {
					break;
				}
			}

			String roomTitle = chattingRoomInfoDTO.getRoomTitle();
			String roomPassword = chattingRoomInfoDTO.getRoomPassword();

			chattingRoomInfo.setRoomHost(host);
			chattingRoomInfo.setRoomNumber(ranDomRoomNumber);
			chattingRoomInfo.setRoomTitle(roomTitle);
			chattingRoomInfo.setRoomPassword(roomPassword);
			chattingRoomInfo.setRoomCurrentCount(0);

			roomList.add(chattingRoomInfo);

			waitingRoomUserList.removeClient(host);

			chattingRoomInfo.addUser(host);

			ChattingRoomSubInfo[] chattingRoomSubInfoList = new ChattingRoomSubInfo[roomList.size()];
			for (int i = 0; i < roomList.size(); i++) {
				ChattingRoomInfo temp = roomList.get(i);
				chattingRoomSubInfoList[i] = new ChattingRoomSubInfo(temp.getRoomNumber(), temp.getRoomTitle(),
						temp.getUserNumber(), temp.getRoomLimitCount());
				chattingRoomSubInfoList[i].setRoomCurrentCount(temp.getRoomCurrentCount());
				chattingRoomSubInfoList[i].setRoomPassword(temp.getRoomPassword());
			}

			ChattingRoomSubInfo chattingRoomSubInfo = new ChattingRoomSubInfo(ranDomRoomNumber, roomTitle, limitCount);
			chattingRoomSubInfo.setRoomCurrentCount(1);
			chattingRoomSubInfo.setRoomPassword(roomPassword);
			chattingRoomSubInfo.setHost(host);

			try {
				ObjectOutputStream oosTemp = clientList.get(host);
				oosTemp.writeObject(chattingRoomSubInfo);
				oosTemp.flush();
				oosTemp.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}

			waitRoomUpdate(ResponseProtocol.RESPONSE_CREATE_CHATTINGROOM_OK);
		}
	}

	// 채팅방 입장
	private synchronized void joinRoom(ChattingRoomInfoDTO chattingRoomDTO) {
		String nickName = getName();

		String requestRoomNumber = String.valueOf(chattingRoomDTO.getRoomNumber());
		String requestRoomTitle = chattingRoomDTO.getRoomTitle();

		String[] data = new String[2];
		data[0] = requestRoomNumber;
		data[1] = requestRoomTitle;

		Client client = null;
		for (ChattingRoomInfo chattingRoomInfo : roomList) {
			if (chattingRoomInfo.equals(data)) {
				client = waitingRoomUserList.getClient(nickName);

				chattingRoomInfo.addUser(client);

				waitingRoomUserList.removeClient(client);

				Client[] userListTemp = chattingRoomInfo.returnClient();

				ChattingRoomSubInfo subInfo = returnRoomInfo(chattingRoomInfo);
				System.out.println("호스트 : " + chattingRoomInfo.getRoomHost().getNickName());
				try {
					DataDTO dataDTO = new DataDTO(ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_OK, subInfo,
							userListTemp);
					oos.writeObject(dataDTO);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					String msg = nickName + "님이 입장하였습니다..";
					for (int i = 0; i < userListTemp.length; i++) {
						String name = userListTemp[i].getNickName();
						if (name.equals(nickName) == false) {
							ObjectOutputStream tempOos = clientList.get(userListTemp[i]);

							tempOos.writeObject(new DataDTO(ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_NOTIFY,
									userListTemp, msg));

							tempOos.flush();
							tempOos.reset();
						} else {
							System.out.println("????????! " + nickName);
						}
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 대기실 유저 목록
				Client[] userList = waitingRoomUserList.returnClient();

				if (userList != null) {
					try {
						WaitingRoomUpdateDTO waitingRoomUpdateDTO = returnWaitingRoomUpdateDTO(
								ResponseProtocol.RESPONSE_ENTER_CHATTINGROOM_UPDATE);
						for (int i = 0; i < userList.length; i++) {
							ObjectOutputStream tempOos = clientList.get(userList[i]);

							tempOos.writeObject(waitingRoomUpdateDTO);
							tempOos.flush();
							tempOos.reset();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				break;
			}
		}
	}

	private String roomJoinMsg(String nickName) {
		String msg = nickName + "님이 들어왔습니다.";
		return msg;
	}

	private String roomOutMsg(String nickName) {
		String msg = nickName + "님이 나가셨습니다.";
		return msg;
	}

	private String roomSettingChangeMsg() {
		String msg = "방 설정이 변경되었습니다.";
		return msg;
	}

	private String msgChange(String nickName, PushDataDTO pushDataDTO) {

		String msg = ((String) pushDataDTO.getData()).replaceAll(filter, "♥");
		String data = "[" + nickName + "]" + " : " + msg;

		return data;
	}

	// 랜덤 입장
	private synchronized boolean randomJoinRoom() {

		String nickName = getName();
		Client client = null;
		int roomSize = roomList.size();

		if (roomSize != 0) {
			client = waitingRoomUserList.getClient(nickName);

			int randomNumber = (int) (Math.random() * roomSize);

			ChattingRoomInfo info = roomList.get(randomNumber);

			int roomCurrentCount = info.getRoomCurrentCount();
			int roomLimitCount = info.getRoomLimitCount();

			if (info.getRoomPassword().equals("") && roomCurrentCount < roomLimitCount) {
				synchronized (waitingRoomUserList) {
					waitingRoomUserList.removeClient(client);
				}

				info.addUser(client);

				Client[] temp = info.returnClient();
				ChattingRoomSubInfo subInfo = returnRoomInfo(info);
				try {
					DataDTO dataDTO = new DataDTO(ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_OK, subInfo,
							temp);

					oos.writeObject(dataDTO);
					oos.flush();
					oos.reset();

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].equals(nickName) == false) {
							ObjectOutputStream tempOos = clientList.get(temp[i]);
							tempOos.writeObject(new DataDTO(ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_NOTIFY,
									temp, roomJoinMsg(nickName)));
							tempOos.flush();
							tempOos.reset();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				waitRoomUpdate(ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_UPDATE_UI);
				return true;
			}
		}

		return false;
	}

	// 대기실의 모든 유저를 리턴해서 돌려준다.
	private AllUserDataDTO returnAllUserData() {
		Client[] allUserList = waitingRoomUserList.returnClient();
		if (allUserList != null) {
			AllUserDataDTO allUserDataDTO = new AllUserDataDTO(ResponseProtocol.RESPONSE_ALL_USER_DATA_OK, allUserList);

			return allUserDataDTO;
		}
		return null;
	}

	// 채팅방에서 찾아서 삭제함
	private synchronized Client roomSearchClient(String nickName) {
		for (ChattingRoomInfo info : roomList) {

			Client client = info.removeUser(nickName);

			if (client != null) {
				// RESPONSE_CHATTINGROOM_NOTIFY
				try {
					Client[] userList = info.returnClient();

					// 나가셨습니다 메세지
					String msg = roomOutMsg(nickName);

					for (int i = 0; i < userList.length; i++) {
						ObjectOutputStream tempOos = clientList.get(userList[i]);

						tempOos.writeObject(new DataDTO(ResponseProtocol.RESPONSE_CHATTINGROOM_NOTIFY, userList, msg));
						tempOos.flush();
						tempOos.reset();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return client;
			}
		}
		return null;
	}

	// 채팅방 나가기
	private Client exitChattingRoom() {
		String nickName = getName();

		// 채팅방에서 찾아서 삭제함
		Client client = roomSearchClient(nickName);

		// 대기실에 추가
		waitingRoomUserList.addClient(client);

		// 해당 클라이언트 리턴
		return client;
	}

	// 방 설정 변경
	private void roomSettingChange(ChattingRoomInfoDTO chattingRoomInfoDTO) {

		int roomNumber = chattingRoomInfoDTO.getRoomNumber();
		String roomTitle = chattingRoomInfoDTO.getRoomTitle();
		String roomPassword = chattingRoomInfoDTO.getRoomPassword();
		int currentCount = chattingRoomInfoDTO.getRoomCurrentCount();
		int limitCount = chattingRoomInfoDTO.getRoomLimitCount();

		System.out.println("룸 넘버 : " + roomNumber + "\t방 제목 : " + roomTitle);
		// 채팅방의 정보를 이용해서 찾은거 리턴받음
		ChattingRoomInfo info = searchRoom(roomNumber, roomTitle);

		if (info != null) {
			if (info.getRoomCurrentCount() > limitCount) {
				try {
					oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_CHANGE_SETTING_ROOM_NO, "인원 수를 확인 하세요"));
					oos.flush();
					oos.reset();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return;
			}
			if (info.getRoomCurrentCount() <= limitCount) {

				info.setRoomTitle(roomTitle);
				info.setRoomPassword(roomPassword);
				info.setRoomLimitCount(limitCount);

				// 제한인원 만큼 배열을 다시 만든다.
				info.relocate(limitCount);

				// RESPONSE_CHANGE_SETTING_ROOM_NOTIFY

				String msg = roomSettingChangeMsg();

				ChattingRoomSubInfo subInfo = new ChattingRoomSubInfo(roomNumber, info.getRoomTitle(),
						info.getRoomCurrentCount(), info.getRoomLimitCount());
				subInfo.setRoomPassword(roomPassword);
				Client[] list = info.returnClient();
				for (int i = 0; i < list.length; i++) {
					try {
						ObjectOutputStream tempOos = clientList.get(list[i]);

						tempOos.writeObject(
								new DataDTO(ResponseProtocol.RESPONSE_CHANGE_SETTING_ROOM_NOTIFY, subInfo, msg));

						tempOos.flush();
						tempOos.reset();

					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				// RESPONSE_DUE_TO_SETTING_ROOM_WAITINGROOM_UPDATE
				// 대기실 인원이 없을 경우에는 전송하지 않음.
				waitRoomUpdate(ResponseProtocol.RESPONSE_DUE_TO_SETTING_ROOM_WAITINGROOM_UPDATE);

			} else {
				// RESPONSE_CHANGE_SETTING_ROOM_NO
				try {
					oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_CHANGE_SETTING_ROOM_NO, "인원 수를 확인 하세요"));
					oos.flush();
					oos.reset();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	// 대기실 업데이트
	private void waitingRoomUpdate(String nickName) {
		// RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE
		Client[] userList = waitingRoomUserList.returnClient();
		WaitingRoomUpdateDTO data = returnWaitingRoomUpdateDTO(
				ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE);
		if (userList != null) {
			for (int i = 0; i < userList.length; i++) {
				if (userList[i].equals(nickName) != true) {
					ObjectOutputStream tempOos = clientList.get(userList[i]);
					try {
						tempOos.writeObject(data);
						tempOos.flush();
						tempOos.reset();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 귓속말 기능
	private void whisper(PushDataDTO pushDataDTO) {
		String msg = (String) pushDataDTO.getData();

		int start = msg.indexOf(" ") + 1;
		int end = msg.indexOf(" ", start);

		if (end != -1) {
			String to = msg.substring(start, end);
			String msg2 = "귓속말 FROM[" + getName() + "] : " + msg.substring(end + 1);

			Client toClient = searchUser(to);
			if (toClient != null) {
				ObjectOutputStream tempOos = clientList.get(toClient);
				if (tempOos != null) {
					PushDataDTO data = null;
					try {
						data = new PushDataDTO(ResponseProtocol.RESPONSE_WHISPER_OK, msg2);
						tempOos.writeObject(data);
						tempOos.flush();
						tempOos.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						String msg3 = "귓속말 TO[" + to + "] : " + msg.substring(end + 1);
						data.setData(msg3);
						oos.writeObject(data);
						oos.flush();
						oos.reset();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				// RESPONSE_WHISPER_NO
				try {
					oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_WHISPER_NO, "귓속말 전송에 실패함."));
					oos.flush();
					oos.reset();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 유저 찾기
	public Client searchUser(String nickName) {
		Client temp = null;
		// 대기실에서 찾기
		temp = waitingRoomUserList.searchUser(nickName);
		if (temp != null) {
			return temp;
		}

		// 채팅방에서 찾기
		for (ChattingRoomInfo info : roomList) {
			temp = info.searchUser(nickName);
			if (temp != null) {
				return temp;
			}
		}

		return null;
	}

	// 채팅방 찾기
	public ChattingRoomInfo searchRoom(int roomNumber, String roomTitle) {

		String[] str = new String[2];

		str[0] = String.valueOf(roomNumber);
		str[1] = roomTitle;
		System.out.println("써치 룸  " + roomNumber + "\t써치 타이틀  " + str[1]);

		for (int i = 0; i < roomList.size(); i++) {
			ChattingRoomInfo info = roomList.get(i);
			int num = info.getRoomNumber();
			if (num == roomNumber) {
				System.out.println("리턴 !! " + info.getRoomTitle());
				return info;
			}
		}

		return null;
	}

	// 채팅방 폭파
	private void explosionRoom() {
		String nickName = getName();
		Client temp = null;
		int idx = 0;

		for (ChattingRoomInfo info : roomList) {
			temp = info.searchUser(nickName);
			if (temp != null) {
				Client[] userList = info.returnClient();

				for (int i = 0; i < userList.length; i++) {
					Client user = info.removeUser(userList[i].getNickName());
					ObjectOutputStream tempOos = clientList.get(userList[i]);

					try {

						tempOos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_CHATTINGROOM_EXPLOSION,
								"방장이 무책임하게 나가서 방이 폭파 되었습니다."));
						tempOos.flush();
						tempOos.reset();

					} catch (Exception e) {
						e.printStackTrace();
					}
					// 대기실에 추가
					waitingRoomUserList.addClient(user);
				}
				// 채팅방 삭제
				roomList.remove(idx);

				// 대기실 인원들
				Client[] waitingUser = waitingRoomUserList.returnClient();

				WaitingRoomUpdateDTO dto = returnWaitingRoomUpdateDTO(
						ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE);
				if (waitingUser != null) {
					for (int i = 0; i < waitingUser.length; i++) {
						ObjectOutputStream tempOos = clientList.get(waitingUser[i]);

						try {
							tempOos.writeObject(dto);
							tempOos.flush();
							tempOos.reset();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				}
			}
			idx++;
		}
	}

	// 초대 대상에게 메세지를 보낸다.
	private synchronized void inviteClient(PushDataDTO pushDataDTO) {
		String nickName = getName();
		String inviteTo = (String) pushDataDTO.getData();
		Client to = waitingRoomUserList.searchUser(inviteTo);
		if (to != null) {
			inviteFlag = true;
			try {
				ObjectOutputStream tempOos = clientList.get(to);
				tempOos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY, nickName));
				tempOos.flush();
				tempOos.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_FALSE,
						"유저가 종료했거나, 이미 다른 채팅방에 들어갔을지도 모릅니다"));
				oos.flush();
				oos.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 초대수락
	private boolean inviteAccept(PushDataDTO pushDataDTO) {
		// RESPONSE_USER_INVITE_DELIVERY_OK
		String hostNickName = (String) pushDataDTO.getData();
		String nickName = getName();

		for (ChattingRoomInfo info : roomList) {
			Client host = info.getRoomHost();
			String name = host.getNickName();
			if (name.equals(hostNickName)) {
				int currentCount = info.getRoomCurrentCount();
				int limitCount = info.getRoomLimitCount();

				// 현재인원 < 제한인원
				System.out.println("현재인원 " + currentCount + "\t제한인원 " + limitCount);
				if (currentCount < limitCount) {
					Client client = waitingRoomUserList.searchUser(nickName);
					waitingRoomUserList.removeClient(client);

					info.addUser(client);

					Client[] userList = info.returnClient();
					ChattingRoomSubInfo subInfo = returnRoomInfo(info);
					try {
						ObjectOutputStream tempOos = clientList.get(client);
						DataDTO dataDTO = new DataDTO(ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_OK, subInfo,
								userList);

						tempOos.writeObject(dataDTO);
						tempOos.flush();
						tempOos.reset();
					} catch (Exception e) {
						e.printStackTrace();
					}

					String msg = roomJoinMsg(nickName);
					for (int i = 0; i < userList.length; i++) {
						String nick = userList[i].getNickName();
						if (nick.equals(nickName) == false) {
							ObjectOutputStream tempOos = clientList.get(userList[i]);
							try {
								tempOos.writeObject(
										new DataDTO(ResponseProtocol.RESPONSE_USER_INVITE_NOTIFY, userList, msg));
								tempOos.flush();
								tempOos.reset();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					// RESPONSE_USER_INVITE_NOTIFY
					waitRoomUpdate(ResponseProtocol.RESPONSE_DUE_TO_USER_INVITE_WAITINGROOM_UPDATE);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		Object obj = null;

		try {
			while ((obj = ois.readObject()) != null) {
				if (obj instanceof PushDataDTO) {
					PushDataDTO pushDataDTO = (PushDataDTO) obj;
					int code = pushDataDTO.getCode();
					if (code == RequestProtocol.REQUEST_NICK_CHECK) {
						// 클라이언트 생성
						createClient(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_WATINGROOM_MSG_INPUT) {
						// 대기실 메세지 전송
						boolean result = msgSend(pushDataDTO);
						if (!result) {
							// RESPONSE_WATINGROOM_MSG_INPUT_NO
						}
					} else if (code == RequestProtocol.REQUEST_CHATTINGROOM_MSG_INPUT) {
						roomMsgSend(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_WHISPER) {
						// 귓속말
						whisper(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_USER_INVITE) {
						// 대기실 유저 초대 요청
						inviteClient(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_USER_INVITE_ACCEPT) {
						// 초대를 받는다
						boolean result = inviteAccept(pushDataDTO);
						System.out.println("성공 여부 : " + result);
					} else if (code == RequestProtocol.REQUEST_MASTER_ENTRUST) {
						// ENTRUST_DELIVERY
						entrustDelivery(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_MASTER_ENTRUST_RECEIVE) {
						entrustReceive(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_MASTER_ENTRUST_NOT_RECEIVE) {
						String host = (String) pushDataDTO.getData();
						String msg = getName() + "님이 방장 위임을 거절하였습니다.";

						Client clientHost = searchUser(host);

						ObjectOutputStream tempOos = clientList.get(clientHost);
						tempOos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_MASTER_ENTERUST_NO, msg));
						tempOos.flush();
						tempOos.reset();
					} else if (code == RequestProtocol.REQUEST_USER_FORCED_EXIT) {
						// 강제 퇴장 요청 처리
						kickUser(pushDataDTO);
					} else if (code == RequestProtocol.REQUEST_USER_INVITE_OVERLAP) {
						String msg = (String) pushDataDTO.getData();
						String message = "이미 다른 초대를 받고 있습니다";

						Client client = searchUser(msg);
						try {
							ObjectOutputStream tempOos = clientList.get(client);

							tempOos.writeObject(
									new PushDataDTO(ResponseProtocol.RESPONSE_USER_INVITE_OVERLAP_MSG, message));
							tempOos.flush();
							tempOos.reset();

						} catch (IOException e) {

						}
					}
				} else if (obj instanceof ChattingRoomInfoDTO) {

					ChattingRoomInfoDTO chattingRoomInfoDTO = (ChattingRoomInfoDTO) obj;
					int code = chattingRoomInfoDTO.getCode();
					if (code == RequestProtocol.REQUEST_CREATE_CHATTINGROOM) {
						// 채팅방 생성
						createRoom(chattingRoomInfoDTO);
					} else if (code == RequestProtocol.REQUEST_ENTER_CHATTINGROOM) {
						// 채팅방 입장
						joinRoom(chattingRoomInfoDTO);
					} else if (code == RequestProtocol.REQUEST_CHANGE_SETTING_ROOM) {
						// 채팅방 정보 변경
						roomSettingChange(chattingRoomInfoDTO);
					}
				} else if (obj instanceof Integer) {
					int n = (int) obj;
					// 랜덤 입장
					if (n == RequestProtocol.REQUEST_RANDOM_ENTER_CHATTINGROOM) {
						boolean result = randomJoinRoom();
						// 랜덤입장 실패할 경우
						if (!result) {
							oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_NO,
									"랜덤 입장 할 수가 없습니다."));
							oos.flush();
							oos.reset();
						}

					} else if (n == RequestProtocol.REQUEST_ALL_USER_DATA) {
						// 대기실 모든 유저 데이터 요청
						AllUserDataDTO allUserDataDTO = returnAllUserData();
						if (allUserDataDTO != null) {
							oos.writeObject(allUserDataDTO);
							oos.flush();
							oos.reset();
						} else {
							// ResponseProtocol.RESPONSE_ALL_USER_DATA_NO;
							oos.writeObject(
									new PushDataDTO(ResponseProtocol.RESPONSE_ALL_USER_DATA_NO, "대기실에 유저가 아무도 없습니다."));
							oos.flush();
							oos.reset();
						}

					} else if (n == RequestProtocol.REQUEST_CHATTINGROOM_EXIT) {
						String userNickName = getName();

						Client client = exitChattingRoom();

						if (client != null) {
							// RESPONSE_CHATTINGROOM_EXIT_OK data :
							// WaitingRoomUpdateDTO
							WaitingRoomUpdateDTO data = returnWaitingRoomUpdateDTO(
									ResponseProtocol.RESPONSE_CHATTINGROOM_EXIT_OK);
							try {
								oos.writeObject(data);
								oos.flush();
								oos.reset();
							} catch (IOException e) {
								e.printStackTrace();
							}

							waitingRoomUpdate(userNickName);
						}
					} else if (n == RequestProtocol.REQUEST_CHATTINGROOM_HOST_EXIT) {
						explosionRoom();
					} else if (n == RequestProtocol.REQUEST_USER_OUT) {
						userExit();
					}
				} else if (obj instanceof DataDTO) {
					DataDTO dataDTO = (DataDTO) obj;
					int code = dataDTO.getCode();
					if (code == RequestProtocol.REQUEST_USER_INVITE_NOT_ACCEPT) {
						String user = (String) dataDTO.getData()[0];
						String host = (String) dataDTO.getData()[1];

						String msg = user + "님이 초대를 거절하였습니다.";

						Client hostClient = searchUser(host);
						// RESPONSE_USER_INVITE_DELIVERY_NO
						ObjectOutputStream tempOos = clientList.get(hostClient);

						tempOos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_USER_INVITE_DELIVERY_NO, msg));
						tempOos.flush();
						tempOos.reset();
					}
				}

			}
		} catch (SocketException e) {
			if (stopFlag != true) {
				forcedExit();
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// 강제 퇴장
	private void kickUser(PushDataDTO pushDataDTO) {
		// 강제 퇴장할 대상
		String user = (String) pushDataDTO.getData();

		String host = getName();
		// 채팅방 찾기
		ChattingRoomInfo info = returnChattingRoom(host);
		if (info != null) {
			// 채팅방에서 해당 유저를 삭제
			Client kickUser = info.removeUser(user);
			if (kickUser == null) {
				// 강퇴 실패 했을 경우 RESPONSE_USER_FORCED_EXIT_NO
				try {
					oos.writeObject(new PushDataDTO(ResponseProtocol.RESPONSE_USER_FORCED_EXIT_NO, "강퇴에 실패하였습니다"));
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			} else {
				waitingRoomUserList.addClient(kickUser);

				// 해당 유저에게 대기실 채팅방 목록, 사용자 목록을 전달한다
				WaitingRoomUpdateDTO data = returnWaitingRoomUpdateDTO(ResponseProtocol.RESPONSE_USER_FORCED_EXIT_OK);

				ObjectOutputStream tempOos = clientList.get(kickUser);

				try {
					tempOos.writeObject(data);
					tempOos.flush();
					tempOos.reset();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 채팅방에 있는 모든 클라이언트에게 전달
				Client[] list = info.returnClient();
				ChattingRoomSubInfo infoData = returnRoomInfo(info);
				String msg = user + "님이 강퇴당했습니다";
				DataDTO dataDTO = new DataDTO(ResponseProtocol.RESPONSE_USER_FORCED_EXIT_NOTIFY, list, msg);
				for (int i = 0; i < list.length; i++) {
					ObjectOutputStream listTemp = clientList.get(list[i]);
					try {
						listTemp.writeObject(dataDTO);
						listTemp.flush();
						listTemp.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	// 채팅방 찾아서 리턴
	private ChattingRoomInfo returnChattingRoom(String nickName) {
		for (int i = 0; i < roomList.size(); i++) {
			ChattingRoomInfo info = roomList.get(i);

			Client host = info.getRoomHost();
			if (host.getNickName().equals(nickName)) {
				return info;
			}
		}
		return null;
	}

	private void entrustReceive(PushDataDTO pushDataDTO) {
		String userNick = getName();
		String hostNick = (String) pushDataDTO.getData();
		System.out.println("방장 위임을 요청한 클라이언트 : " + hostNick);
		System.out.println("방장 위임을 받을 대상  : " + userNick);

		for (int i = 0; i < roomList.size(); i++) {
			ChattingRoomInfo info = roomList.get(i);

			String host = info.getRoomHost().getNickName();
			System.out.println(i + 1 + "번방 호스트 : " + host);
			if (hostNick.equals(host)) {
				System.out.println("여기 들어왔다 !");
				Client user = info.searchUser(userNick);
				if (user != null) {
					info.setRoomHost(user);

					// 채팅방 사용자들에게 알림
					Client[] list = info.returnClient();
					String message = host + "님에서" + userNick + "님으로 방장이 변경되었습니다.";
					DataDTO dataDTO = new DataDTO(ResponseProtocol.RESPONSE_MASTER_ENTRUST_OK_NOTIFY, list, user,
							message);
					for (int index = 0; index < list.length; index++) {
						ObjectOutputStream tempOos = clientList.get(list[index]);
						try {
							tempOos.writeObject(dataDTO);
							tempOos.flush();
							tempOos.reset();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return;
				}
			}
		}
	}

	private void entrustDelivery(PushDataDTO pushDataDTO) {
		String deliveryUser = (String) pushDataDTO.getData();
		Client deliveryClient = searchUser(deliveryUser);

		ObjectOutputStream tempOos = clientList.get(deliveryClient);
		try {
			tempOos.writeObject(
					new DataDTO(ResponseProtocol.RESPONSE_MASTER_ENTRUST_DELIVERY, getName(), "방장을 받으시겠습니까?"));
			tempOos.flush();
			tempOos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 채팅방의 모든 정보를 리턴
	private ChattingRoomSubInfo returnRoomInfo(ChattingRoomInfo info) {
		System.out.println(info.getRoomNumber());
		ChattingRoomSubInfo subInfo = new ChattingRoomSubInfo(info.getRoomNumber(), info.getRoomTitle(),
				info.getRoomCurrentCount(), info.getRoomLimitCount());

		subInfo.setHost(info.getRoomHost());
		subInfo.setRoomPassword(info.getRoomPassword());

		return subInfo;
	}

	// 대기실에서 정상종료
	private void userExit() {
		synchronized (this) {
			Client client = waitingRoomUserList.searchUser(getName());
			if (client != null) {
				waitingRoomUserList.removeClient(client);

				try {
					oos.writeObject(new Integer(ResponseProtocol.RESPONSE_USER_OUT_OK));
					oos.flush();
					oos.reset();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				clientList.remove(client);
				waitRoomUpdate(ResponseProtocol.RESPONSE_USER_OUT_UPDATE);
			}
			stopFlag = true;
		}
	}

	// 강제종료 했을 때 실행할 메소드
	private void forcedExit() {
		synchronized (this) {
			String name = getName();
			Client client = waitingRoomUserList.searchUser(name);
			if (client != null) {
				waitingRoomUserList.removeClient(client);
				clientList.remove(client);

				// 대기실 유저 업데이트
				waitRoomUpdate(ResponseProtocol.RESPONSE_USER_OUT_UPDATE);
			}
			Iterator<ChattingRoomInfo> iter = roomList.iterator();
			while (iter.hasNext()) {
				ChattingRoomInfo info = iter.next();

				Client user = info.searchUser(name);
				if (user != null) {
					Client host = info.getRoomHost();
					if (host.getNickName().equals(name)) {
						Client[] userList = info.returnClient();
						// 방장일 때 강제 종료
						for (int i = 0; i < userList.length; i++) {
							if (userList[i] != null) {
								Client deleteUser = info.removeUser(userList[i].getNickName());
								try {
									if (host.getNickName().equals(deleteUser.getNickName()) == false) {
										ObjectOutputStream tempOos = clientList.get(userList[i]);
										tempOos.writeObject(
												new PushDataDTO(ResponseProtocol.RESPONSE_CHATTINGROOM_EXPLOSION,
														"방장이 무책임하게 나가서 방이 폭파 되었습니다."));
										tempOos.flush();
										tempOos.reset();

										// 대기실에 추가
										waitingRoomUserList.addClient(deleteUser);
									}

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						// 채팅방 삭제
						iter.remove();
						clientList.remove(user);

						// 대기실 인원들
						Client[] waitingUser = waitingRoomUserList.returnClient();

						WaitingRoomUpdateDTO dto = returnWaitingRoomUpdateDTO(
								ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE);
						if (waitingUser != null) {
							for (int i = 0; i < waitingUser.length; i++) {
								ObjectOutputStream tempOos = clientList.get(waitingUser[i]);

								try {
									tempOos.writeObject(dto);
									tempOos.flush();
									tempOos.reset();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

					} else {
						// 클라이언트 일 때 강제 종료
						// 채팅방에서 삭제
						Client temp = info.removeUser(name);
						clientList.remove(temp);

						// 채팅방 유저에게 알리기 RESPONSE_CHATTINGROOM_NOTIFY
						Client[] list = info.returnClient();

						String msg = roomOutMsg(name);

						for (int i = 0; i < list.length; i++) {
							ObjectOutputStream tempOos = clientList.get(list[i]);
							try {
								tempOos.writeObject(
										new DataDTO(ResponseProtocol.RESPONSE_CHATTINGROOM_NOTIFY, list, msg));
								tempOos.flush();
								tempOos.reset();
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						// 대기실 목록 업데이트
						waitRoomUpdate(ResponseProtocol.RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE);
					}
				}
			}

		}
	}

	// 대기실 모든 유저에게 WaitingRoomUpdateDTO 전송
	private void waitRoomUpdate(int code) {
		WaitingRoomUpdateDTO data = returnWaitingRoomUpdateDTO(code);
		Client[] list = waitingRoomUserList.returnClient();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				ObjectOutputStream tempOos = clientList.get(list[i]);

				try {
					tempOos.writeObject(data);
					tempOos.flush();
					tempOos.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}