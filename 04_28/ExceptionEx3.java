class ExceptionEx3 {

	public static void makeException(int[] arr) {
		try {
			System.out.println(arr[10]);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("�ε��� ������ ������.");
		} catch(NullPointerException e) {
			System.out.println("null�� ������ �� �����ϴ�.");
		}
	}

	public static void main(String[] args)	{
		makeException(new int[]{1,2,3,4});
		makeException(null);
	}
}
