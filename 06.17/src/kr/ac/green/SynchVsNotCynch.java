package kr.ac.green;

public class SynchVsNotCynch {
	private static final long CALL_COUNT = 100000000L;
	public static void main(String[] args) {
		trial(CALL_COUNT, new NotSynch());
		trial(CALL_COUNT, new Synch());
		
	}
	// synchronized 붙이는 것은 공짜가 아니다. (성능 저하...)
	// synchronized (오버라이드 검사 대상에서 제외)
	private static void trial(long count, Object obj) {
		String msg = obj.toString();
		System.out.println(msg + " : BEGIN");
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			obj.toString();
		}
		System.out.println("Elapsed time = " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println(msg + " : END");
	}
}

class Synch {
	private final String name = "Synch";
	@Override
	public synchronized String toString() {
		return "[ " + name + " ]";
	}
}
class NotSynch extends Synch {
	private final String name = "NotSynch";
	@Override
	public String toString() {
		return "[ " + name + " ]";
	}
}