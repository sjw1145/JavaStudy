import java.awt.*;
import javax.swing.*;
class MyUtils {
	// frame�� ȭ�� ���߾ӿ� ��ġ��Ű�� �޼��带 �ϼ��϶�.
	public static void goCenter(JFrame frame)	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		Dimension frameSize = frame.getSize();

		int x = (screenSize.width - frameSize.height) / 2;
		int y = (screenSize.height - frameSize.height) / 2;

		frame.setLocation(x, y);
	}
}
