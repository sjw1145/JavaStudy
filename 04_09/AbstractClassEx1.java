/*
	�߻�Ŭ����
		1. �������
		2. �Ϲݸ޼���
		3. ������
		4. �߻�޼��� => ��ü���� �Ұ����� => ����Ŭ������ �����ؾ���
*/
abstract class Animal {
	// �Ҹ����� : �߻�޼���
	/*
		����� �ؾ��ϴ����� ���� �� �� ������
		��� �ؾ��ϴ����� ���� �� �� ���°��
		(���� ���信�� ���ǰ� ������ ���)
	*/
	public Animal() {
		
	}


	public abstract void sound();

	public String toString() {
		return "Animal";

	}
}
/*
	�߻�Ŭ������ ��ӹ޴� Ŭ������ �ݵ��
	�θ�Ŭ������ �߻�޼��带 �������̵��ؾ��Ѵ�.
*/
class Dog extends Animal {
	@Override
	public void sound() {
		System.out.println("�۸�");
	}
}
// Dog��Cat�� sound�� �ٸ��� �����ȴ�. -> Animal���� ����(how) �Ұ�����.
class Cat extends Animal {
	@Override
	public void sound() {
		System.out.println("�߿�~");
	}
}
class AbstractClassEx1 {
	public static void main(String[] args) {
				
	}
}
