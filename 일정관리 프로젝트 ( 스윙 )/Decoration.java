
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
	
	//��Ʈ ��ü
	public static Font f1 = new Font("���� ���", Font.BOLD, 18);	//month ��
	public static Font f2 = new Font("���� ���", Font.BOLD, 22);	//����(�Ͽ�ȭ�����)
	public static Font f3 = new Font("���� ���", Font.BOLD, 25);	//ū�۾�(�ϴ� ��ư ���� �� ������ ����)
	public static Font f4 = new Font("���� ���", Font.PLAIN, 15);	//year�� �� ��
	public static Font f5 = new Font("���� ���", Font.BOLD, 14);	//���� �����/�����ϱ� ��ư ��Ʈ

	//���� ��ü
	public static Color darkGrey = new Color(69,69,69); 
	public static Color lightGrey = new Color(213, 213, 213);
	public static Color pink = new Color(255, 217, 236);
	public static Color blue = new Color(196,222,255);

	
	//�ѱ� ��ư �̹��� ��ü
	public static ImageIcon left = new ImageIcon("left.png");
	public static ImageIcon right = new ImageIcon("right.png");
	public static ImageIcon leftDouble = new ImageIcon("left.double.png");
	public static ImageIcon rightDouble = new ImageIcon("right.double.png");
	

	
	
	
	
	
	//�г��� ������ �� ������ ���� �ϴ� �޼���
	public static void setPnlWhite(JPanel pnl) {
		pnl.setOpaque(true);	
		pnl.setBackground(Color.WHITE);
	}
	
	//���̺��� ������ �� ������ ���� �ϴ� �޼���
	public static void setLblWhite(JLabel lbl) {
		lbl.setOpaque(true);	
		lbl.setBackground(Color.WHITE);
	}
	
	//�������� ������ �� ������ �����ϴ� �޼���
	public static void setFrameWhite(JFrame frame) {
		frame.setBackground(Color.WHITE);
	}
	
	//=============================================
	
	//������ ����
	public static void setIcon(JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("to-do-list.png");
		frame.setIconImage(img);
	}
	
	//��ư ȸ������ �ٹ̱�
	public static void setBtnGrey(JButton btn) {
		btn.setBackground(darkGrey);
		btn.setBorderPainted(false);
		btn.setFont(f1);
		btn.setForeground(Color.WHITE);
	}
	
	//��ư ������� �ٹ̱�
	public static void setBtnWhite(JButton btn) {
		btn.setBackground(Color.WHITE);
		btn.setFont(f5);
		btn.setForeground(darkGrey);
	}
	
	//��ư �׵θ� ���ֱ�, ���� �����
	public static void setBtn(JButton btn) {
		btn.setBorder(null);
		btn.setBackground(null);
	}
	
	//�޺��ڽ� �������
	public static void setCbWhite(JComboBox cb) {
		cb.setBackground(Color.WHITE);
		cb.setFont(Decoration.f1);
	}
}
	

