package kr.ac.green;

class ChattingRoomInfo {
	private Client roomHost;
	private int roomNumber;
	private String roomTitle;
	private String roomPassword;
	private int roomCurrentCount;
	private int roomLimitCount;
	
	
	public ChattingRoomInfo(Client roomHost, int roomNumber, String roomTitle, String roomPassword,
			int roomCurrentCount, int roomLimitCount) {
		super();
		this.roomHost = roomHost;
		this.roomNumber = roomNumber;
		this.roomTitle = roomTitle;
		this.roomPassword = roomPassword;
		this.roomCurrentCount = roomCurrentCount;
		this.roomLimitCount = roomLimitCount;
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

}

 
