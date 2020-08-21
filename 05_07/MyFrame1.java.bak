/*
	JFrame
*/
import java.util.*;
import javax.swing.*;
class MyFrame1 extends JFrame {

	public MyFrame1() {
		setTitle("MyFrame1");
		setSize(400, 300);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		Random r = new Random();
		for(int x=0; x<=500; x++) {
			setLocation(r.nextInt(500), r.nextInt(500));
			setSize(r.nextInt(300), r.nextInt(300));
			try {
				Thread.sleep(r.nextInt(1000));
			} catch(Exception e) {}
		}
	}

	public static void main(String[] args)	{
		new MyFrame1();
	}
}
