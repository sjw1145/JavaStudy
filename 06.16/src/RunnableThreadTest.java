
public class RunnableThreadTest {
	public static void main(String[] args) {
		
		
		Thread t = new Thread(new RunnableThread());
		t.start();
		System.out.println("eof");
	}
}
