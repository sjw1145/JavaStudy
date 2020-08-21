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

	public void love(Human h) throws CrimeUnderTheAgeOf19 {
		// �̼����ڸ� ����� �� ���� : ���ܻ�Ȳ
		if(h.getAge() <= 19) {
			// ���ܹ߻�
			throw new CrimeUnderTheAgeOf19();
		} else {
			System.out.println(name + "��" + h.getName());
		}
	}
}
class CrimeUnderTheAgeOf19 extends Exception {
	public CrimeUnderTheAgeOf19() {
		super("ö��û��");
	}
}

class ExceptionEx5 {
	public static void main(String[] args)	{
		Human h1 = new Human("�ں���", 26);
		Human h2 = new Human("����", 25);
		try {
			h1.love(h2);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println(e.getMessage());
		}

		Human h3 = new Human("X����", 44);
		Human h4 = new Human("����", 17);
		try {
			h3.love(h4);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println(e.getMessage());
		}

		Human h5 = new Human("�¹�", 18);
		try {
			h5.love(h4);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println("�ູ�ϰ� �߻�ƿ�~");
		}
	}
}
