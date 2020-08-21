import java.util.Arrays;
import java.util.Comparator;
/*
	���� Human�� ���ı����� �ƴ� �ٸ� ������ ����
*/
class HumanComparator implements Comparator<Human> {
	/*
		java.util.Comparator<T>
		
		c.compare(a:T, b:T) : int
		
		- ��������
		a > b : ���
		a < b : ����
		a == b : 0
	*/
	@Override
	public int compare(Human h1, Human h2) {
		// �̸����� ����
		return h1.getName().compareTo(h2.getName());
	}
}
class SortEx2 {
	/*
		? : Object
		<? extends T> : T�� ���� Ÿ��(is-a) ��ü
		<? super T> : T�� ����Ÿ��(is-a) ��ü
	*/
	public static void main(String[] args)	{
		Human[] arr = {
			new Human(20, "E"),		// 0
			new Human(23, "B"),		// 1
			new Human(22, "C"),		// 2
			new Human(21, "D"),		// 3
			new Human(24, "A")		// 4
		};

		HumanComparator cmp = new HumanComparator();
		// �κ�����
		// Arrays.sort(arr,0, 3, cmp);
		Arrays.sort(arr, cmp);
		System.out.println(Arrays.toString(arr));

		int idx = Arrays.binarySearch(arr, new Human("D"), cmp);
		System.out.println(idx);
	}
}