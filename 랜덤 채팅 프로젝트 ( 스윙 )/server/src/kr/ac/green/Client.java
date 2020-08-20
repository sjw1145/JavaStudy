package kr.ac.green;

import java.io.Serializable;

public class Client implements Serializable{
	private String nickName;
	private String gender;
	
	public Client() {
		
	}
	public Client(String nickName, String gender) {
		super();
		this.nickName = nickName;
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}	
	@Override
	public boolean equals(Object obj) {
		String subNickName = (String) obj;
		
		return nickName.equals(subNickName);
	}
}

