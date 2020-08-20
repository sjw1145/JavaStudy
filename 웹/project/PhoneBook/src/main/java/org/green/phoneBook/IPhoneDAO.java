package org.green.phoneBook;

import java.sql.Connection;

public interface IPhoneDAO {
	Connection connect();
	void disconnect(Connection con);
	
	// ��� ���� ��������
	PhoneBook[] selectPhone(Connection con);
	// ���
	int insertPhone(Connection con, PhoneBook pb);
	// ����
	int deletePhone(Connection con, int phone_id);
	// ����
	int modifyPhone(Connection con, PhoneBook pb);
	// ã��
	PhoneBook searchPhone(Connection con, int phone_id);
}
