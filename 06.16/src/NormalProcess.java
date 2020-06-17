import javax.swing.JOptionPane;

public class NormalProcess {
	public static void main(String[] args) {
//		String input = JOptionPane.showInputDialog("아무 값 입력");
//		
//		System.out.println("입력한 값 "+input);
//		/////////////블록킹///////////
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
		System.out.println("소요 시간 1 : "+(System.currentTimeMillis() - startTime));
		
		for(int i = 0 ; i<300; i++) {
			System.out.println("ㅣ");
		}
		
		System.out.println("소요 시간2 : "+(System.currentTimeMillis() - startTime));
	}
}
