class StringVsStringBuffer {
	public static int count = 1000000;
	public static void caseOfString() {
		String str = "";
		// System.currentTimeMillis() -> 현재시간을 ms단위로 구한다.
		long time = System.currentTimeMillis();
		for(int i=0; i<count; i++) {
			str += "a";
		}
		time = System.currentTimeMillis() - time;
		System.out.println("String : " + time + "ms");
	}
	public static void caseOfStringBuffer() {
		StringBuffer buf = new StringBuffer();
		long time = System.currentTimeMillis();
		for(int i=0; i<count; i++) {
			buf.append("a");
		}
		time = System.currentTimeMillis() - time;
		System.out.println("StringBuffer : " + time + "ms");
	}
	public static void main(String[] args)	{
		caseOfString();
		// caseOfStringBuffer();
	}
}
