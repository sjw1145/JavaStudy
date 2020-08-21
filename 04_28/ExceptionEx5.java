class ExceptionEx5 {
	
	public static int[] makeArray(int length) {
		int[] arr = null;
		try {
			// ���� ����X
			arr = new int[length];
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			// ���� ����X
			e.printStackTrace();
		} finally {
			// ���� ����	
			// �ܺ��ڿ��� ���õ� ������ �ַ� ��ġ�Ѵ�.
			// ���ܹ߻� ���ο� ������� ����ȴ�.
			System.out.println("done");
		}
		
		return arr;
	}

	public static void main(String[] args)	{
		makeArray(3);
		makeArray(-3);
		System.out.println("end of main");
	}
}
