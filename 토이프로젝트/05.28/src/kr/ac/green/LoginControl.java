package kr.ac.green;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LoginControl {
	public static final int ID = 0;
	public static final int PW = 1;
	public static final int NAME = 2;
	public static final int NICKNAME = 3;
	public static final int GENDER = 4;

	private File userFile;

	private ArrayList<MemberDTO> userList;

	public LoginControl() {
		try {
			userFile = new File("UserFile.txt");
			if (!(userFile.exists())) {
				userFile.createNewFile();
			}

			userList = getAll();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �α��� ���
	public MemberDTO userLogin(String userId, String userPw) {
		userList = getAll();
		for (MemberDTO temp : userList) {
			String id = temp.getId();
			String pw = temp.getPw();
			if ((id.equals(userId)) && (pw.equals(userPw))) {
				temp.setSession(true);
				return temp;
			}
		}
		return null;
	}

	// ���Ͽ� ȸ�� ���� �ٽ� ����
	public void userWrite() {
		FileWriter fw = null;

		try {
			fw = new FileWriter(userFile);

			for (MemberDTO temp : userList) {
				fw.write(temp.toString() + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(fw);
		}
	}

	// ȸ������ ���
	public boolean userJoin(MemberDTO userInfo) {
		FileWriter fw = null;

		// ���� ���� �޾Ƽ� ���� write
		try {
			fw = new FileWriter(userFile, true);

			fw.write(userInfo.toString() + "\n");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyUtils.closeAll(fw);
		}
		return false;
	}

	// ȸ�� ���� ��������
	private ArrayList<MemberDTO> getAll() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();

		FileReader fr = null;
		BufferedReader brd = null;

		MemberDTO user = null;
		String line = null;
		try {
			fr = new FileReader(userFile);

			brd = new BufferedReader(fr);

			while ((line = brd.readLine()) != null) {
				user = new MemberDTO();

				user.setId(line);
				user.setPw(brd.readLine());
				user.setName(brd.readLine());
				user.setNickName(brd.readLine());
				user.setGender(brd.readLine());

				list.add(user);
			}

		} catch (Exception e) {
		} finally {
			MyUtils.closeAll(brd, fr);
		}
		return list;
	}

	// ���̵� �ߺ�üũ
	public void idOverlapCheck(String userId) throws IdCheckException {
		userList = getAll();
		for (MemberDTO temp : userList) {
			String id = temp.getId();
			if (id.equals(userId)) {
				throw new IdCheckException(userId);
			}
		}
	}

	// ȸ��Ż��
	public void withDraw(MemberDTO user) {

		userList.remove(new MemberDTO(user.getId()));

		if (userList.size() != 0) {
			userWrite();
		} else {
			userFile.delete();
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
