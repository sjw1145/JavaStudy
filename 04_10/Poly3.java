class Father {
	public void sayHello() {
		System.out.println("�ȳ�~");
	}
}
class Son extends Father {
	@Override
	public void sayHello() {
		System.out.println("�ȳ��Ͻʴϱ�~");
	}
}

class Poly3 {
	public static void main(String[] args) {
		Son s = new Son();
		s.sayHello();

		Father f = s;
		f.sayHello();
	}
}
