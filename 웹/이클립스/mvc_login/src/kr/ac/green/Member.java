package kr.ac.green;

public class Member {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nick;

	public Member() {
	}

	public Member(String user_id, String user_pw, String user_name, String user_nick) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_nick = user_nick;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

}
