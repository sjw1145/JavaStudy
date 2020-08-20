package kr.ac.green;

import java.util.Arrays;

public class ChattingRoomInfo {
	private Client roomHost;
	private int roomNumber;
	private String roomTitle;
	private String roomPassword;
	private int roomCurrentCount;
	private int roomLimitCount;
	private Client[] userList;

	private int userNumber;

	public ChattingRoomInfo(int limitCount) {
		this.roomLimitCount = limitCount;
		userList = new Client[roomLimitCount];
	}

	public Client getRoomHost() {
		return roomHost;
	}

	public void setRoomHost(Client roomHost) {
		this.roomHost = roomHost;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public int getRoomCurrentCount() {
		return roomCurrentCount;
	}

	public void setRoomCurrentCount(int roomCurrentCount) {
		this.roomCurrentCount = roomCurrentCount;
	}

	public int getRoomLimitCount() {
		return roomLimitCount;
	}

	public void setRoomLimitCount(int roomLimitCount) {
		this.roomLimitCount = roomLimitCount;
	}

	public Client[] getUserList() {
		return userList;
	}

	public void setUserList(Client[] userList) {
		this.userList = userList;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	// 유저 추가
	public void addUser(Client client) {
		userList[getUserNumber()] = client;
		roomCurrentCount++;
		userNumber++;
	}

	// 유저 리턴
	public Client[] returnClient() {
		Client[] list = new Client[getUserNumber()];

		for (int i = 0; i < list.length; i++) {
			list[i] = userList[i];
		}

		return list;
	}

	// 유저삭제
	public Client removeUser(String nickName) {
		Client temp = null;

		int n = searchUserNumber(nickName);

		if (n != -1) {
			temp = userList[n];
			userList[n] = null;
			for (int i = n + 1; i < userNumber; i++) {
				if (userList[i] != null) {
					userList[i - 1] = userList[i];
				}
			}
		}
		if (temp != null) {
			roomCurrentCount--;
			userNumber--;
		}

		return temp;
	}

	// 유저 찾기
	public Client searchUser(String nickName) {

		for (int i = 0; i < roomCurrentCount; i++) {
			String userNickName = userList[i].getNickName();
			if (nickName.equals(userNickName)) {
				return userList[i];
			}
		}
		return null;
	}

	// 유저 번호 검색
	public int searchUserNumber(String nickName) {
		for (int i = 0; i < roomCurrentCount; i++) {
			String userNickName = userList[i].getNickName();
			if (userNickName != null && nickName.equals(userNickName)) {
				return i;
			}
		}
		return -1;
	}

	// 배열 재 배치
	public void relocate(int newLimitCount) {
		userList = Arrays.copyOf(userList, newLimitCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String[]) {
			String[] data = (String[]) obj;
			int number = Integer.parseInt(data[0]);
			return (roomNumber == number) && (roomTitle.equals(data[1]));
		}

		return false;
	}

}
