import java.awt.*;
import javax.swing.*;
/*
	(400, 300) ũ�⸦ ������ â�� ȭ�� �߾ӿ� ��ġ��Ű�ÿ�.
*/
class JFrameEx3 {
	public static void main(String[] args)	{
		JFrame f = new JFrame("���߾�");
		f.setSize(400, 300);
		// ũ������ �� �̵�
		MyUtils.goCenter(f);
		// ��üȭ�� -> ũ�⸦ �����ϰ� ����
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
