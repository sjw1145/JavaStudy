/*
	(cond) ? A : B;

	(����)�Ұ����� ����;
	if( ���ڳ�? ) {
		// true
		���ִ°� ���ش�;
	} else {
		// false
		��ȭ�Ѵ�;
	}
	�����´�;

	cond
		true : A -> C
		false : B -> C

		(����)�Ұ��� ����.
		if(�λ��� ����?) {			
			VIPS����;
		} else if(�߻����?) {
			���õ������;
		} else if(���������ϳ�?) {
			�̾߱��Ѵ�;
		} else if(�μ��� ������?) {
			ī�䰣��;	
		} else {
			��ȭ�Ѵ�;
		}
*/
class IfEx {
	public static void main(String[] args) {
		int n = 3;

		System.out.println((n == 2) ? "A" : (n >= 3)? "B" : "C");
		(n == 2) ? "A" : "B";

		/*
		int num = 4;
		if(num < 4) {
			if(num < 2) {
				System.out.println("B");
			} else {
				System.out.println("C");		
			}
		}
		*/
		
	}
}
