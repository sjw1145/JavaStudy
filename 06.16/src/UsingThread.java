import javax.swing.JOptionPane;

public class UsingThread {
	public static void main(String[] args) {
		ThreadEx th1 = new ThreadEx();
		th1.start();
		
		String input = JOptionPane.showInputDialog("�ƹ� ���̳� �Է��ϼ���");
		
		System.out.println("�Է��Ͻ� ���� "+ input + "�Դϴ�");
	}
}
