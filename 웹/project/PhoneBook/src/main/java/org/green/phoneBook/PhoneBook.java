package org.green.phoneBook;

import org.springframework.stereotype.Repository;

@Repository
public class PhoneBook {
	private int phone_id;
	private String name;
	private String nick;
	private String tel;
	private String gender;

	public PhoneBook() {
		super();
	}

	public PhoneBook(String name, String nick, String tel, String gender) {
		super();
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.gender = gender;
	}

	public PhoneBook(int phone_id, String name, String nick, String tel, String gender) {
		this.phone_id = phone_id;
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.gender = gender;
	}

	public int getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(int phone_id) {
		this.phone_id = phone_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}