package kr.ac.green;

import java.io.Serializable;

public class RoomUserListDTO extends RequestCode implements Serializable{
	
	private Client[] userList;
	
	public RoomUserListDTO(int code,Client[] userList) {
		super(code);
		this.userList = userList;
	}

	public Client[] getUserList() {
		return userList;
	}

	public void setUserList(Client[] userList) {
		this.userList = userList;
	}
	
	
}
 

