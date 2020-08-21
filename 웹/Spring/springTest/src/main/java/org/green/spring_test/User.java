package org.green.spring_test;


public class User {
	private String userName;
	private int userAge;
	
	public User() {
		super();
	}
	public User(String userName, int userAge) {
		super();
		this.userName = userName;
		this.userAge = userAge;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userAge=" + userAge + "]";
	}
	
}
