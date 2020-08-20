package kr.ac.green;

import java.io.Serializable;

public class ChattingRoomSubInfo implements Serializable {
	private int roomNumber;
	private String roomTitle;
	private int roomCurrentCount;
	private int roomLimitCount;
	private String roomPassword;
	private Client host;

	public ChattingRoomSubInfo(int roomNumber, String roomTitle, int roomCurrentCount, int roomLimitCount) {
		super();
		this.roomNumber = roomNumber;
		this.roomTitle = roomTitle;
		this.roomCurrentCount = roomCurrentCount;
		this.roomLimitCount = roomLimitCount;
	}
	public ChattingRoomSubInfo(int roomNumber, String roomTitle, int roomLimitCount) {
		super();
		this.roomNumber = roomNumber;
		this.roomTitle = roomTitle;

		this.roomLimitCount = roomLimitCount;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public Client getHost() {
		return host;
	}

	public void setHost(Client host) {
		this.host = host;
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
}

