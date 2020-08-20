package kr.ac.green;

import java.io.Serializable;

public class AllUserDataDTO extends RequestCode implements Serializable {

	private Client[] waitingRoomUserList;

	public AllUserDataDTO(int code, Client[] waitingRoomUserList) {
		super(code);
		this.waitingRoomUserList = waitingRoomUserList;
	}

	public Client[] getWaitingRoomUserList() {
		return waitingRoomUserList;
	}

	public void setWaitingRoomUserList(Client[] waitingRoomUserList) {
		this.waitingRoomUserList = waitingRoomUserList;
	}
}