
public class ThreadEx extends Thread{
	@Override
	public void run() {
//		for(int i = 10; i > 0; i--) {
//			System.out.println(i);
//			try {
//				sleep(1000);
//			} catch (Exception e) {
//			}
//		}
		for(int i = 0; i < 300; i++) {
			System.out.println("ㅣ");
		}
		
		System.out.println("소요 시간 2: "+(System.currentTimeMillis() - UsingThreadProcess.startTime));
	}

}
