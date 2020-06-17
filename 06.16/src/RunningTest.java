
public class RunningTest {
	
	public static void main(String[] args) {
		/*
		 * 자신을 생성한 쓰레드의 우선순위 값을 그대로 물려받는다.
		 * 메인스레드는 기본적으로 5
		 */
		SomeThread t1 = new SomeThread("A");
		SomeThread t2 = new SomeThread("B");
		SomeThread t3 = new SomeThread("C");
		
		//쓰레드의 우선순위 1 , 5, 10
		//우선순위가 높은 쓰레드가 선택될 가능성이 높다.
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
}
