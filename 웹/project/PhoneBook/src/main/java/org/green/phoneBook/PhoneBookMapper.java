package org.green.phoneBook;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PhoneBookMapper implements RowMapper<PhoneBook>{
	
	private static String toEn(String kor) {
		String en = null;
		try {
			en = new String(kor.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return en;
	}
	
	@Override
	public PhoneBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		int phone_id = rs.getInt("phone_id");
		String name = toEn(rs.getString("name"));
		String nick = toEn(rs.getString("nick"));
		String tel = toEn(rs.getString("tel"));
		String gender = toEn(rs.getString("gender"));
		return new PhoneBook(phone_id, name, nick, tel, gender);
	}
}
