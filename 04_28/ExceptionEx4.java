class ExceptionEx4 {

	public static void makeException(int[] arr) {
		try {
			System.out.println(arr[10]);
		// JDK1.7~
		// ���� ���ܵ鿡 ���� ������ �۾��� �����ϴ� ���
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			System.out.println("���ܹ߻�");
		} 
	}

	public static void main(String[] args)	{
		makeException(new int[]{1,2,3,4});
		makeException(null);
	}
}
