class Human {
	private String name;
	private int age;

	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void setAge(int newAge) {
		age = newAge;
	}
	/*
		��ü�� ���� ������ ���ڿ��� ��ȯ
		������ ����̳� ������ ����.
	*/
	public String toString() {		
		return name + "(" + age + ")";
	}
}
class UseHuman {
	public static void main(String[] args) {
		Human h1 = new Human();
		h1.setName("������");
		h1.setAge(28);
		System.out.println(h1);


	}
}