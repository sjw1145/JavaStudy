import javax.swing.JOptionPane;

public class NormalProcess {
	public static void main(String[] args) {
//		String input = JOptionPane.showInputDialog("�ƹ� �� �Է�");
//		
//		System.out.println("�Է��� �� "+input);
//		/////////////���ŷ///////////
//		
//		for(int i = 10; i > 0; i--) {
//			System.out.println(i);
//			try {
//				Thread.sleep(1000);
//			}catch (Exception e) {
//			}
//		}
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i<300; i++) {
			System.out.println("-");
		}
		System.out.println("�ҿ� �ð� 1 : "+(System.currentTimeMillis() - startTime));
		
		for(int i = 0 ; i<300; i++) {
			System.out.println("��");
		}
		
		System.out.println("�ҿ� �ð�2 : "+(System.currentTimeMillis() - startTime));
	}
}
