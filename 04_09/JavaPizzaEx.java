// ����
abstract class JavaPizza {
	private void sayHello() {
		System.out.println("�ȳ��ϼ���~�ڹ������Դϴ�~");
	}
	public final void processing() {
		sayHello();
		cooking();
		boxing();
	}
	private void cooking() {
		System.out.println("���� ���...");
	}
	protected abstract void boxing(); 
}
class JavaPizzaBusan extends JavaPizza {
	@Override
	protected void boxing() {
		System.out.println("�츮�� ���̰�~");
	}
}
class JavaPizzaSeoul extends JavaPizza {
	@Override
	protected void boxing() {
		System.out.println("���߼���. �ں����~");
	}
}
class JavaPizzaEx {
	public static void main(String[] args) {
		new JavaPizzaBusan().processing();
		new JavaPizzaSeoul().processing();
	}
}