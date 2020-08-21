import java.awt.*;
import javax.swing.*;
class MyUtils {
	// frame을 화면 정중앙에 위치시키는 메서드를 완성하라.
	public static void goCenter(JFrame frame)	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		Dimension frameSize = frame.getSize();

		int x = (screenSize.width - frameSize.height) / 2;
		int y = (screenSize.height - frameSize.height) / 2;

		frame.setLocation(x, y);
	}
}
