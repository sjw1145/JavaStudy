/*
	�������� ���� -> ��ü ������ ����� ��
*/
class Human {
	private String name;
	private int age;
	public Human() {
		
	}
	// �����³������� ���� Ŭ������.
	public Human(String newName) {
		setName(newName);
	}
	public Human(String newName, int newAge) {
		setName(newName);
		setAge(newAge);
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
}
class UseHuman {
	public static void main(String[] args) {
		Human h = new Human();
	}
}
