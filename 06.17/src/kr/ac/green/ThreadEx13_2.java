package kr.ac.green;

public class ThreadEx13_2 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 300; i++) {
			System.out.println("|");
		}
		System.out.println("스레드2번 Daed");
	}
}
