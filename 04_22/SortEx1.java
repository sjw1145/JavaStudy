import java.util.Arrays;
import java.util.Comparator;

/*
	java.util.Comparator<T>
	
	c.compare(a:T, b:T) : int
	
	- ��������
	a > b : ���
	a < b : ����
	a == b : 0
*/
class Human implements Comparable<Human> {
	private int age;
	private String name;
	
	// ���̷� �˻�
	public Human(int age) {
		setAge(age);
	}
	// �̸����� �˻�
	public Human(String name) {
		setName(name);
	}

	public Human(int age, String name) {
		setAge(age);
		setName(name);
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setName(String name) {
		this.name = name;
	}
	// natural ordering : ���̷� ����
	@Override
	public int compareTo(Human other) {
		return age - other.getAge();
	}
	
	@Override
	public String toString() {
		return name + "(" + age + ")";
	}
}
class SortEx1 {
	public static void main(String[] args)	{
		Human[] arr = {
			new Human(20, "E"),		// 0
			new Human(23, "B"),		// 1
			new Human(22, "C"),		// 2
			new Human(21, "D"),		// 3
			new Human(24, "A")		// 4
		};

		// �κ����� : 0~3���� ����(0�� ����, 3�� �������� ����)
		//Arrays.sort(arr, 0, 3);

		//System.out.println(Arrays.toString(arr));

		// ��ü����
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		/*
			binarySeach���� ����	
				1. arr�� �ݵ�� ���ĵǾ�� �Ѵ�.(natural odering)
					-> Human �� Comparable ����
				2. �Ϲ������� ��� ������ �Ǿ� �ִ� ���¿����� �ŷ��� �� �ִ� ���� �����ش�.(���ı����� �Ǵ� ���� �ߺ��� �������)
				3. ���̷� �����Ѱ�� ���̰� ��ġ�ϴ� ��� ã�� �� �ִ�.
		*/
		int idx = Arrays.binarySearch(arr, new Human(21));
		System.out.println(idx);
	}
}
