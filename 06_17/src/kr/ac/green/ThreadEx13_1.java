package kr.ac.green;

public class ThreadEx13_1 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 300; i++) {
			System.out.println("-");
		}
		System.out.println("스레드1번 Daed");
	}
}
