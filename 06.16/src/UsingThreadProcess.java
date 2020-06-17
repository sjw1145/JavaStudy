
public class UsingThreadProcess {
	static long startTime = 0;
	
	public static void main(String[] args) {
		ThreadEx th1 = new ThreadEx();
		
		startTime = System.currentTimeMillis();
		th1.start();
		
		for(int i = 0; i < 300; i++) {
			System.out.println("-");
		}
		
		System.out.println("소요 시간1 : "+(System.currentTimeMillis() - UsingThreadProcess.startTime));
	}
}
