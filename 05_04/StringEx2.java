class StringEx2 {
	public static void main(String[] args)	{
		// index -> ����
		//			  012345
		String str = "abcdef";
		
		// ���ڼ�
		System.out.println(str.length());
		
		// �ش� �ε����� ����(char) ���ϱ�
		char c = str.charAt(3);
		System.out.println(c);

		for(int idx=0; idx<str.length(); idx++) {
			System.out.println((int)(str.charAt(idx)));
		}

		System.out.println("abc".compareTo("x"));
		System.out.println('x' - 'X');
		System.out.println((int)'�R');
		System.out.println((int)'x');

		System.out.println("abc".compareTo("ABC"));
		System.out.println("abc".compareToIgnoreCase("ABC"));
		System.out.println("abc".equals("ABC"));
		System.out.println("abc".equalsIgnoreCase("ABC"));
		
		// str�� ������ �빮�ڷ� ����� ���� �ƴ�.
		// �빮�ڷ� ������ ���ο� String ��ü�� ���� ����
		String newStr = str.toUpperCase();
		System.out.println(newStr);
		System.out.println(newStr.toLowerCase());
		System.out.println(str);
	}
}

