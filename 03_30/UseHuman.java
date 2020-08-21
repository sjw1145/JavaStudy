/*
	������ : getter
	������ : setter
*/
class Human {
	private int age = 40;
	private String name;
	private boolean male;

	public boolean isMale() {
		return male;
	}

	// getter
	// ��������� �ܺη� �����ش�.
	public int getAge() {
		// ...
		return age;
	}
	public String getName() {
		return name;
	}
	// setter
	// ��������� ���� �����Ѵ�.
	public void setAge(int newAge) {
		if(newAge < 100) {
			age = newAge;
		}
	}
	public void setName(String newName) {
		name = newName;
	}
}
class UseHuman {
	public static void main(String[] args) {
		Human h = new Human();
		//h.age = 1000;
		h.setAge(40);
		System.out.println(h.getAge());
		h.setName("ȫ�浿");
		System.out.println(h.getName());
	}
}