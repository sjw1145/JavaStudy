package org.green.phoneBook;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcPhoneDAO {

	@Autowired
	private JdbcTemplate jdbcTmp;
	
	private static String toKor(String en) {
		String kor = null;
		try {
			kor = new String(en.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return kor;
	}
	
	// 등록
	public int insertPhone(PhoneBook pb) {
		String sql = "INSERT INTO phone_book (name, nick, tel, gender) VALUES(?,?,?,?)";

		String name = toKor(pb.getName());
		String nick = toKor(pb.getNick());
		String tel = toKor(pb.getTel());
		String gender = toKor(pb.getGender());

		return jdbcTmp.update(sql, name, nick, tel, gender);

	}

	// 수정
	public int modifyPhone(PhoneBook pb) {
		String sql = "UPDATE phone_book SET name=? , nick=? , tel=? ,gender=? WHERE phone_id=?";

		String name = toKor(pb.getName());
		String nick = toKor(pb.getNick());
		String tel = toKor(pb.getTel());
		String gender = toKor(pb.getGender());
		int phone_id = pb.getPhone_id();

		return jdbcTmp.update(sql, name, nick, tel, gender, phone_id);

	}

	// 삭제
	public int deletePhone(int phone_id) {
		String sql = "DELETE FROM phone_book WHERE phone_id=?";
		return jdbcTmp.update(sql, phone_id);
	}

	// 겟올
	public PhoneBook[] getAll() {
		String sql = "SELECT * FROM phone_book";
		List<PhoneBook> phoneList = jdbcTmp.query(sql, new PhoneBookMapper());
		return phoneList.toArray(new PhoneBook[0]);
	}

	// 전화번호부 검색 ( id )
	public PhoneBook searchPhone(int phone_id) {
		String sql = "SELECT * FROM phone_book WHERE phone_id=?";
		List<PhoneBook> phoneList = jdbcTmp.query(sql, new Object[]{phone_id} , new PhoneBookMapper());
		
		if(phoneList.size() == 1) {
			return phoneList.get(0);
		}
		
		return null;
	}
	// 전화번호부 검색 ( name )
	public PhoneBook searchPhone(String name) {
		String sql = "SELECT * FROM phone_book WHERE name = ?";
		List<PhoneBook> phoneList = jdbcTmp.query(sql, new Object[]{name}, new PhoneBookMapper());
		
		if(phoneList.size() == 1) {
			return phoneList.get(0);
		}
		
		return null;
	}
}
