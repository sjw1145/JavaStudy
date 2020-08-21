class Some {
	// 1. �����ڸ� private �ϰ� �����.
	private Some() {
	}
	// 2. �ڽ�Ÿ��(Some) static ��ü�� �����Ѵ�.
	private static final Some instance = new Some();

	// 3. instance�� ���� getter�� �����.
	public static Some getInstance() {
		return instance;
	}
}
class SingletonEx {
	public static void main(String[] args) {
		Some obj = Some.getInstance();
	}
}
