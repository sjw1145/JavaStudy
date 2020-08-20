package org.green.phoneBook;

import java.sql.Connection;

public interface IPhoneDAO {
	Connection connect();
	void disconnect(Connection con);
	
	// 모든 정보 가져오기
	PhoneBook[] selectPhone(Connection con);
	// 등록
	int insertPhone(Connection con, PhoneBook pb);
	// 삭제
	int deletePhone(Connection con, int phone_id);
	// 수정
	int modifyPhone(Connection con, PhoneBook pb);
	// 찾기
	PhoneBook searchPhone(Connection con, int phone_id);
}
