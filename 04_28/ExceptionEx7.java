class ExceptionEx7 {
	public static void main(String[] args)	{
		/*
			try-catch-finally �� ���� ��ø�� �����ϴ�.

		*/
		try {
			// A
		} catch(B) {
			try {
				// C
			} catch(D) {
				// E
			}
		} finally {
			try {
				// F
			} catch(G) {
				// H
			} finally {
				// I
			}
		}
	}
}
