
public class ExtendThreadTest {
	public static void main(String[] args) {
		ExtendThread et = new ExtendThread();
		//Start 를 이용하여 스레드를 시작
		//run이 실행되고 run 이 종료되면 ExtendThread 가 소멸
		//java.lang
		et.start();
		System.out.println("end of main");
	}
}
