/*
	값이 변하는 문자열
*/
class StringBufferEx1 {
	public static void main(String[] args)	{
		StringBuffer buf = new StringBuffer("abc");
		// XYZ를 추가한다. buf의 내용이 변함
		buf.append("XYZ");

		System.out.println(buf);
		/*
			012345
			abcXYZ
		*/
		buf.delete(1, 4);
		System.out.println(buf);

		buf.insert(1, "$$$");
		System.out.println(buf);

		buf.reverse();
		System.out.println(buf);

		// 해당하는 새로운 문자열을 리턴
		String str = buf.substring(1, 3);
		System.out.println(str);
		System.out.println(buf);

		// 내용을 조작하는 메서드는 체이닝을 지원한다.
		buf.append("a").append("b").append("c").insert(1, "xxx");
		System.out.println(buf);
		// StringBuffer -> String
		String value = buf.toString();
	}
}
