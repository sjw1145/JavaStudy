/*
	������ �����ϱⰡ �ſ� ���鱸��.
	���붧�� ������µ� ������ Ư�� ���鱸��.
*/
class StringEx4 {
	public static void main(String[] args)	{
		//			  01234567890	
		String str = "abcdefabghi";
		
		// �ſ� �߿��ϴ�.
		System.out.println(str.indexOf("ab", 1));
		System.out.println(str.lastIndexOf("ab", 5));
		System.out.println(str.indexOf("xx"));
		
		// 2���� ������ �߶�´�
		System.out.println(str.substring(2));
		// 2�̻� 7�̸����� �߶�´�.
		System.out.println(str.substring(2, 7));
	}
}
