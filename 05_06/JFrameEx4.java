import java.awt.*;
import javax.swing.*;

class JFrameEx4 {
	public static void main(String[] args)	{
		JFrame f = new JFrame("�޷�");
		f.setSize(300, 300);
		// �߾ӿ� �´�....�߸�
		f.setLocationRelativeTo(null);
		// ũ�⺯�� �Ұ�
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
