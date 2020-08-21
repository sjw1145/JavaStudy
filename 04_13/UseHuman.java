class Human {
	private String name;
	private int age;

	public Human(String name, int age) {
		setName(name);
		setAge(age);
	}

	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	// ���̿� �̸��� ������ ���� ����̴�.
	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Human) ) {
			return false;
		}
		// h1.equals(h2)
		Human h = (Human)other;
		// ���̺�
		boolean result1 = (age == h.getAge());
		// String�� equals ��� -> ���ڿ� ������ ������ ����.
		boolean result2 = name.equals( h.getName() );

		return result1 && result2;
	}
}
class UseHuman {
	public static void main(String[] args) {
		Human h1 = new Human("A", 20);
		Human h2 = new Human("B", 30);
		Human h3 = new Human("C", 20);
		Human h4 = new Human("A", 20);

		System.out.println(h1.equals(h2));
		System.out.println(h1.equals(h3));
		System.out.println(h1.equals(h4));
	}
}
