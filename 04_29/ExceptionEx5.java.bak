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
		// 미성년자를 사랑할 수 없다 : 예외상황
		if(h.getAge() <= 19) {
			// 예외발생
			throw new CrimeUnderTheAgeOf19();
		} else {
			System.out.println(name + "♥" + h.getName());
		}
	}
}
class CrimeUnderTheAgeOf19 extends Exception {
	public CrimeUnderTheAgeOf19() {
		super("철컹청컹");
	}
}

class ExceptionEx5 {
	public static void main(String[] args)	{
		Human h1 = new Human("박보검", 26);
		Human h2 = new Human("제니", 25);
		try {
			h1.love(h2);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println(e.getMessage());
		}

		Human h3 = new Human("X영욱", 44);
		Human h4 = new Human("가현", 17);
		try {
			h3.love(h4);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println(e.getMessage());
		}

		Human h5 = new Human("승미", 18);
		try {
			h5.love(h4);
		} catch(CrimeUnderTheAgeOf19 e) {
			System.out.println("행복하게 잘살아요~");
		}
	}
}
