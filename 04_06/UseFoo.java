class Foo {
	public int num = 2;
}
class UseFoo {
	public static void main(String[] args) {
		/*
			final�� ���� ��� f�� �ٸ� Foo��ü�� ��Ÿ���� ����.
		*/
		final Foo f = new Foo();
		f.num = 4;
		f = new Foo();
	}
}
