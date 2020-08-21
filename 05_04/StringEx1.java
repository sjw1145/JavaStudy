/*
	문자열(CharSequence)
	- Character
	- 줄 -> 순서 + 복수
	- 대표적인 클래스 -> String, StringBuffer, StringBuilder
	

	String
	- 불변(객체생성 후 나타내는 값을 변경할 수 없다)
		<-> VS StringBuffer, StringBuilder (thread safe)
	- 공유될 수 있다.
*/
class StringEx1 {
	public static void main(String[] args)	{
		String str1 = "abc";
		String str2 = new String("abc");
		String str3 = "abc";
		String str4 = new String("abc");

		System.out.println(str1.equals(str2));
		System.out.println(str1.equals(str3));
		System.out.println(str1.equals(str4));
		
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str1 == str4);
	}
}


















