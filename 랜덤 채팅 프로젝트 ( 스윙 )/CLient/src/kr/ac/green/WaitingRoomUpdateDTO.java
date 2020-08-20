package kr.ac.green;

import java.io.Serializable;

public class WaitingRoomUpdateDTO extends RequestCode implements Serializable {

	private Client[] waitingRoomUserList;
	private ChattingRoomSubInfo[] chattingRoomSubInfo;

	public WaitingRoomUpdateDTO(int code, Client[] waitingRoomUserList, ChattingRoomSubInfo[] chattingRoomSubInfo) {
		super(code);
		this.waitingRoomUserList = waitingRoomUserList;
		this.chattingRoomSubInfo = chattingRoomSubInfo;
	}

	public Client[] getWaitingRoomUserList() {
		return waitingRoomUserList;
	}

	public void setWaitingRoomUserList(Client[] waitingRoomUserList) {
		this.waitingRoomUserList = waitingRoomUserList;
	}

	public ChattingRoomSubInfo[] getChattingRoomSubInfo() {
		return chattingRoomSubInfo;
	}

	public void setChattingRoomSubInfo(ChattingRoomSubInfo[] chattingRoomSubInfo) {
		this.chattingRoomSubInfo = chattingRoomSubInfo;
	}

}


 

