/*
	���ܻ�Ȳ ����
*/
class TooLongArraySizeException extends Exception {
	public TooLongArraySizeException() {
		super("3���� �ʰ��� �� �����ϴ�.");
	}
	public TooLongArraySizeException(int validCount) {
		super(validCount + "���� �ʰ��� �� �����ϴ�.");
	}
}

class ExceptionEx3 {
	public static void printSum(int... nums) 
		throws TooLongArraySizeException {
		int count = 5;
		// ���� ���Ұ� 3���� �ʰ��ϴ� �� ��ġ�ʴ´�.
		// �迭�� ���̰� 3�ʰ��� ���ܷ� �����Ѵ�.
		if(nums.length > count) {
			// ���ܰ� �߻��ϴ� ��Ȳ

			// 1. ���ܰ�ü ����
			TooLongArraySizeException e = 
				new TooLongArraySizeException(count);
			// 2. �߻�
			throw e;
		} else {
			int sum = 0;
			for(int n : nums) {
				sum += n;
			}
			System.out.println(sum);
		}
	}
	public static void main(String[] args)	{
		try {
			printSum(1,2,3);
		} catch(TooLongArraySizeException e) {
			System.out.println(e.getMessage());
		}

		try {
			printSum(1,2,3,4,5,6,7);
		} catch(TooLongArraySizeException e) {
			System.out.println(e.getMessage());
		}
	}
}
