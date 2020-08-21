package MapTest;

/*
전화번호부
조건 : 동명이인(Map , Collection)
	-> Map <K, V> 고려
	-> Vector................................
추가, 삭제, 검색
*/
import java.util.*;

interface HumanManager {
	void add(Human h);

	Human[] search(String name);

	boolean delete(String name);
}

class Human {
	private String name;
	private int age;
	private String mail;
	private String tel;

	public Human(String name, int age, String mail, String tel) {
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.tel = tel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getMail() {
		return mail;
	}

	public String getTel() {
		return tel;
	}

	@Override
	public String toString() {
		return "이름 : " + name + "\t나이 : " + age + "\t이메일 : " + mail + "\t전화번호 : " + tel + "\n";
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	// 사람 이름이 같으면 동명이인 이다.
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Human)) {
			return false;
		}

		Human people = (Human) o;

		// 사람 이름이 같습니까
		return name.equals(people.getName());
	}
}

class TelManager implements HumanManager {
	private HashMap<String, Human> tel;

	public TelManager() {
		tel = new HashMap<String, Human>();
	}

	public HashMap<String, Human> getTel() {
		return tel;
	}

	public void setTel(HashMap<String, Human> tel) {
		this.tel = tel;
	}

	@Override
	public void add(Human h) {
		if (!tel.containsKey(h.getTel())) {
			tel.put(h.getTel(), h);
		} else {
			System.out.println("똑같은 전화번호가 있습니다..");
		}

	}

	@Override
	public Human[] search(String name) {
		ArrayList<Human> temp = new ArrayList<Human>();

		System.out.println("찾는 이름 : " + name);

		Collection<Human> values = tel.values();

		for (Human human : values) {
			if (human.getName().hashCode() == name.hashCode()) {
				System.out.println(human);
				temp.add(human);
			}
		}

		if (temp.size() != 0) {
			Human[] humans = new Human[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				humans[i] = temp.get(i);
			}
			return humans;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(String name) {
		Scanner kb = new Scanner(System.in);

		Human[] temp = search(name);

		if (temp == null) {
			System.out.println("찾는 데이터가 없슴");
			return false;
		}

		System.out.print("삭제할 전화번호를 똑바로 입력하셈 : ");
		String deleteTel = kb.next();

		if (tel.get(deleteTel).getName().equals(name)) {
			tel.remove(deleteTel);
			return true;
		} else {
			System.out.println("잘 못 입력하였음");
			return false;
		}
	}

	public void process() {
		Scanner kb = new Scanner(System.in);
		boolean isCheck = true;

		do {
			System.out.println("1. 추가\t2.삭제\t3.검색\t4.종료");
			int num = kb.nextInt();
			switch (num) {
				case 1:
					add(new Human(kb.next(), kb.nextInt(), kb.next(), kb.next()));
					break;
				case 2:
					System.out.print("삭제할 이름 : ");
					delete(kb.next());
					break;
				case 3:
					System.out.print("검색할 이름 : ");
					search(kb.next());
					break;
				case 4:
					isCheck = false;
					System.out.println("ByeBye");
					break;
				default:
					System.out.println("다시 눌러");
				}

		} while (isCheck);

	}

	@Override
	public String toString() {
		return tel.toString();
	}

}

class Tel {
	public static void main(String[] args) {
		Human[] man = { new Human("서종완", 29, "a7671145@naver.com", "010-7650-3252"),
				new Human("서종완", 28, "a7671145@naver.com", "010-7650-3253"),
				new Human("서종완", 27, "a7671145@naver.com", "010-7650-3254"),
				new Human("서종완", 26, "a7671145@naver.com", "010-7650-3255"),
				new Human("서종완", 25, "a7671145@naver.com", "010-7650-3256"),
				new Human("서종와니", 20, "a7671145@naver.com", "010-7650-1234"),
				new Human("서종와니", 20, "a7671145@naver.com", "010-7650-2345"),
				new Human("서종와니", 20, "a7671145@naver.com", "010-7650-3456"),
				new Human("서종와니", 20, "a7671145@naver.com", "010-7650-5678"),
				new Human("손흥민", 29, "a7671145@naver.com", "010-1234-1234"),
				new Human("손흥민", 28, "a7671145@naver.com", "010-1234-3245"), };

		TelManager manager = new TelManager();

		for (Human temp : man) {
			manager.add(temp);
		}

		manager.process();

	}

}
