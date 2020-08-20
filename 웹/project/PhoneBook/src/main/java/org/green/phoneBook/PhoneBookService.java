package org.green.phoneBook;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneBookService {
	
	@Autowired
	private jdbcPhoneDAO dao;
	
	public PhoneBook[] selectPhone() {
		
		return dao.getAll();
	}
	
	public int insertPhone(PhoneBook pb) {
		
		return dao.insertPhone(pb);
	}
	
	public int deletePhone(int phone_id) {
		
		return dao.deletePhone(phone_id);
	}
	
	public int modifyPhone(PhoneBook pb) {
		
		return dao.modifyPhone(pb);
	}
	
	public PhoneBook searchPhone(int phone_id) {
		
		return dao.searchPhone(phone_id);
	}
	
	public PhoneBook searchPhone(String name) {
		
		return dao.searchPhone(name);
	}
}
