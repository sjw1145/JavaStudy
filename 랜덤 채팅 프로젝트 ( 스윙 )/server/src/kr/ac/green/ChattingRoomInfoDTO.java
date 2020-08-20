package kr.ac.green;

import java.io.Serializable;

public class ChattingRoomInfoDTO extends RequestCode implements Serializable {
	private int roomNumber;
	private String roomTitle;
	private String roomPassword;
	private int roomCurrentCount;
	private int roomLimitCount;

	public ChattingRoomInfoDTO(int code, int RoomNumber, String roomTitle, String roomPassword, int roomLimitCount) {
		super(code);
		this.roomNumber = RoomNumber;
		this.roomTitle = roomTitle;
		this.roomPassword = roomPassword;
		this.roomLimitCount = roomLimitCount;
	}

	public int getRoomCurrentCount() {
		return roomCurrentCount;
	}

	public void setRoomCurrentCount(int roomCurrentCount) {
		this.roomCurrentCount = roomCurrentCount;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public int getRoomLimitCount() {
		return roomLimitCount;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public void setRoomLimitCount(int roomLimitCount) {
		this.roomLimitCount = roomLimitCount;

	}

}

