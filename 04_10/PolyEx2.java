class PolyEx2 {
	public static void parentToChild(Parent p) {

		if(p instanceof Parent) {
			System.out.println("��?");
		}
		// Parent -> Child
		// instanceof : ����ȯ ������ �����Ѵ�.
		if(p instanceof Child) {
			Child c = (Child)p;
		} else {
			System.out.println("��~");
		}
	}
	public static void main(String[] args) {
		//parentToChild(new Child());
		parentToChild(new Parent());
	}
}
