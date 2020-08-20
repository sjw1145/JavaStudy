package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}

	// �α��� ó��
	public boolean loginCheck(String id, String passwd) {
		List list = new ArrayList();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from HOME");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals(id) && rs.getString(2).equals(passwd)) {
					System.out.println("���̵� ��й�ȣ Ȯ��, DAO ��������");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("�α��� ���� : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	// ȸ�����
	public boolean joinAction(MemberBean bean) {
		String sql = "Insert into HOME values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPasswd());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getNames());
			pstmt.setString(5, bean.getManNumber());
			pstmt.setString(6, bean.getHobby1());
			pstmt.setString(7, bean.getHobby2());
			pstmt.setString(8, bean.getHobby3());
			pstmt.setString(9, bean.getHobby4());
			pstmt.setString(10, bean.getHobby5());
			pstmt.setString(11, bean.getMyView());
			pstmt.executeUpdate();
			System.out.println("ȸ��������񿡳ִ°ż���");
			return true;
		}

		catch (Exception e) {
			System.out.println("ȸ�� ��� ���� : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	// ������ ȸ������ ��Ϻ���
	public List getAllUserInfo() {
		String sql = "select * from HOME";
		//ArrayList list<String> = new ArrayList<String>();
		List list = new ArrayList();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean member = new MemberBean();
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setNames(rs.getString(4));
				member.setManNumber(rs.getString(5));
				member.setHobby1(rs.getString(6));
				member.setHobby2(rs.getString(7));
				member.setHobby3(rs.getString(8));
				member.setHobby4(rs.getString(9));
				member.setHobby5(rs.getString(10));
				member.setMyView(rs.getString(11));
				list.add(member);
			}return list;
		} catch (Exception ex) {
			System.out.println("getBoardList ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	// ����� ���� Ȯ��
	public MemberBean getUserInfo(String id) {
		MemberBean member = null;
		try {
			pstmt = con.prepareStatement("select * from home where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("���������ã��");
				member = new MemberBean();
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setNames(rs.getString(4));
				member.setManNumber(rs.getString(5));
				member.setHobby1(rs.getString(6));
				member.setHobby2(rs.getString(7));
				member.setHobby3(rs.getString(8));
				member.setHobby4(rs.getString(9));
				member.setHobby5(rs.getString(10));
				member.setMyView(rs.getString(11));
			}
			System.out.println("��");
			return member;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	// ����� ����
	public boolean deleteMember(String id) {
		try {
			int result = 0;
			pstmt = con.prepareStatement("delete from home where id = ?");

			pstmt.setString(1, id);

			result = pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("�������������!!");
				return false;
			}
			System.out.println("������");
			return true;

		} catch (Exception e) {
			System.out.println("ȸ����������");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	// ���� ���� ���ϱ�.
	public int getListCount() {
		int x = 0;

		try {
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount ����: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// �� ��� ����.
	public List getBoardList(int page, int limit) {
		String board_list_sql = "select * from " + "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
				+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"
				+ "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "
				+ "(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)) " + "where rnum>=? and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * 10 + 1; // �б� ������ row ��ȣ.
		int endrow = startrow + limit - 1; // ���� ������ row ��ȣ.
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getBoardList ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	// �� ���� ����.
	public BoardBean getDetail(int num) throws Exception {
		BoardBean board = null;
		try {
			pstmt = con.prepareStatement("select * from board where BOARD_NUM = ?");
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getDetail ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	// �� ���.
	public boolean boardInsert(BoardBean board) {
		int num = 0;
		String sql = "";

		int result = 0;

		try {
			pstmt = con.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql += "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," + "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
					+ "BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardInsert ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	// �� �亯.
	public int boardReply(BoardBean board) {
		String board_max_sql = "select max(board_num) from board";
		String sql = "";
		int num = 0;
		int result = 0;

		int re_ref = board.getBOARD_RE_REF();
		int re_lev = board.getBOARD_RE_LEV();
		int re_seq = board.getBOARD_RE_SEQ();

		try {
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? ";
			sql += "and BOARD_RE_SEQ>?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();

			re_seq = re_seq + 1;
			re_lev = re_lev + 1;

			sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql += "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,";
			sql += "BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, ""); // ���忡�� ������ ���ε����� ����.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			return num;
		} catch (SQLException ex) {
			System.out.println("boardReply ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return 0;
	}

	// �� ����.
	public boolean boardModify(BoardBean modifyboard) throws Exception {
		String sql = "update board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			pstmt.executeUpdate();
			return true;
		} catch (Exception ex) {
			System.out.println("boardModify ���� : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	// �� ����.
	public boolean boardDelete(int num) {
		String board_delete_sql = "delete from board where BOARD_num=?";

		int result = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardDelete ���� : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
		}

		return false;
	}

	// ��ȸ�� ������Ʈ.
	public void setReadCountUpdate(int num) throws Exception {
		String sql = "update board set BOARD_READCOUNT = " + "BOARD_READCOUNT+1 where BOARD_NUM = " + num;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate ���� : " + ex);
		}
	}

	// �۾������� Ȯ��.
	public boolean isBoardWriter(int num, String pass) {
		String board_sql = "select * from board where BOARD_NUM=?";

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();

			if (pass.equals(rs.getString("BOARD_PASS"))) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter ���� : " + ex);
		}
		return false;
	}

}
