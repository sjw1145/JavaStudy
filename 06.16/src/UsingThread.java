import javax.swing.JOptionPane;

public class UsingThread {
	public static void main(String[] args) {
		ThreadEx th1 = new ThreadEx();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
		
		System.out.println("입력하신 값은 "+ input + "입니다");
	}
}
