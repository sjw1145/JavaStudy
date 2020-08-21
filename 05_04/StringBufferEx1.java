/*
	���� ���ϴ� ���ڿ�
*/
class StringBufferEx1 {
	public static void main(String[] args)	{
		StringBuffer buf = new StringBuffer("abc");
		// XYZ�� �߰��Ѵ�. buf�� ������ ����
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

		// �ش��ϴ� ���ο� ���ڿ��� ����
		String str = buf.substring(1, 3);
		System.out.println(str);
		System.out.println(buf);

		// ������ �����ϴ� �޼���� ü�̴��� �����Ѵ�.
		buf.append("a").append("b").append("c").insert(1, "xxx");
		System.out.println(buf);
		// StringBuffer -> String
		String value = buf.toString();
	}
}