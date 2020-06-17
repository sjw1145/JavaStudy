package kr.ac.green;

public class ThreadEx13 {
	static long startTime = 0;

	public static void main(String[] args) {
		ThreadEx13_1 th1 = new ThreadEx13_1();
		ThreadEx13_2 th2 = new ThreadEx13_2();

		th1.start();
		th2.start();

		startTime = System.currentTimeMillis();

		try {
			// 1번은 기다리는 동안 2번이 종료가 되었으면 아무 의미 없음
			// 1번을 기다리다가 1번이 끝나고 2번이 일하고 있을 때 2번을 기다린다.
			// 2개의 작업이 영향을 주는 연산
			// 실제로는 이렇게 쓰면 안 됨
			th1.join();
			// th2.join();
		} catch (InterruptedException e) {
			System.out.println("스레드1 실행불가상태");
		}

		try {
			th2.join();
		} catch (InterruptedException e) {
			System.out.println("스레드2 실행불가상태");
		}

		System.out.println("소요시간 : " + (System.currentTimeMillis() - ThreadEx13.startTime));
		
		System.out.println("메인 스레드 종료");
	}
}
