/*
	int[][][][][][][][][][][][][][] arr = 
		new int[5][][][][][][][][][][][][][];

	{ {0,0,0}, {0,0,0} } 
*/


class ArrEx4 {
	public static void main(String[] args)	{
		// { null, null, null}
		// ���ҹ迭�� ���̰� ��� ���� �ʿ�� ����.
		int[][] arr = new int[3][];
		arr[0] = new int[] {1,2,3};
		arr[1] = new int[] {4, 5};
		arr[2] = new int[] {7,8,9,10};
		
		// { {1,2,3}, {4, 5}, {7,8,9,10} }

		for(int[] temp : arr) {
			for(int n : temp) {
				System.out.println(n);
			}
		}
	}
}
