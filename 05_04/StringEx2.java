class StringEx2 {
	public static void main(String[] args)	{
		// index -> 순서
		//			  012345
		String str = "abcdef";
		
		// 글자수
		System.out.println(str.length());
		
		// 해당 인덱스의 글자(char) 구하기
		char c = str.charAt(3);
		System.out.println(c);

		for(int idx=0; idx<str.length(); idx++) {
			System.out.println((int)(str.charAt(idx)));
		}

		System.out.println("abc".compareTo("x"));
		System.out.println('x' - 'X');
		System.out.println((int)'힣');
		System.out.println((int)'x');

		System.out.println("abc".compareTo("ABC"));
		System.out.println("abc".compareToIgnoreCase("ABC"));
		System.out.println("abc".equals("ABC"));
		System.out.println("abc".equalsIgnoreCase("ABC"));
		
		// str의 내용이 대문자로 변경된 것이 아님.
		// 대문자로 구성된 새로운 String 객체를 만들어서 리턴
		String newStr = str.toUpperCase();
		System.out.println(newStr);
		System.out.println(newStr.toLowerCase());
		System.out.println(str);
	}
}

