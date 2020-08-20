
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Decoration {
	
	//폰트 객체
	public static Font f1 = new Font("맑은 고딕", Font.BOLD, 18);	//month 일
	public static Font f2 = new Font("맑은 고딕", Font.BOLD, 22);	//요일(일월화수목금)
	public static Font f3 = new Font("맑은 고딕", Font.BOLD, 25);	//큰글씨(하단 버튼 사이 날 정보에 쓰임)
	public static Font f4 = new Font("맑은 고딕", Font.PLAIN, 15);	//year에 들어갈 일
	public static Font f5 = new Font("맑은 고딕", Font.BOLD, 14);	//일정 만들기/수정하기 버튼 폰트

	//색상 객체
	public static Color darkGrey = new Color(69,69,69); 
	public static Color lightGrey = new Color(213, 213, 213);
	public static Color pink = new Color(255, 217, 236);
	public static Color blue = new Color(196,222,255);

	
	//넘김 버튼 이미지 객체
	public static ImageIcon left = new ImageIcon("left.png");
	public static ImageIcon right = new ImageIcon("right.png");
	public static ImageIcon leftDouble = new ImageIcon("left.double.png");
	public static ImageIcon rightDouble = new ImageIcon("right.double.png");
	

	
	
	
	
	
	//패널의 배경색을 흰 색으로 설정 하는 메서드
	public static void setPnlWhite(JPanel pnl) {
		pnl.setOpaque(true);	
		pnl.setBackground(Color.WHITE);
	}
	
	//레이블의 배경색을 흰 색으로 설정 하는 메서드
	public static void setLblWhite(JLabel lbl) {
		lbl.setOpaque(true);	
		lbl.setBackground(Color.WHITE);
	}
	
	//프레임의 배경색을 흰 색으로 설정하는 메서드
	public static void setFrameWhite(JFrame frame) {
		frame.setBackground(Color.WHITE);
	}
	
	//=============================================
	
	//아이콘 설정
	public static void setIcon(JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("to-do-list.png");
		frame.setIconImage(img);
	}
	
	//버튼 회색으로 꾸미기
	public static void setBtnGrey(JButton btn) {
		btn.setBackground(darkGrey);
		btn.setBorderPainted(false);
		btn.setFont(f1);
		btn.setForeground(Color.WHITE);
	}
	
	//버튼 흰색으로 꾸미기
	public static void setBtnWhite(JButton btn) {
		btn.setBackground(Color.WHITE);
		btn.setFont(f5);
		btn.setForeground(darkGrey);
	}
	
	//버튼 테두리 없애기, 배경색 지우기
	public static void setBtn(JButton btn) {
		btn.setBorder(null);
		btn.setBackground(null);
	}
	
	//콤보박스 흰색으로
	public static void setCbWhite(JComboBox cb) {
		cb.setBackground(Color.WHITE);
		cb.setFont(Decoration.f1);
	}
}
	

