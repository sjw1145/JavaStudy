import java.util.*;
class CollectionEx3 {
	public static void main(String[] args)	{
		Collection<String> main = new ArrayList<String>();
		main.add("a");
		main.add("b");
		main.add("c");
		main.add("d");
		main.add("e");
		main.add("f");

		Collection<String> others = new ArrayList<String>();
		others.add("a");
		others.add("b");
		others.add("c");
		others.add("x");
		
		System.out.println("main - " + main);
		System.out.println("others - " + others);
		// others�� ���ҿ� ��ġ�ϴ� ���� ��� ����
		// main.removeAll(others);
		// others�� ���ҿ� ��ġ���� �ʴ� ���� ��� ����
		// main.retainAll(others);
		// others�� ��� ���Ұ� main�� �����ϴ°�?
		// System.out.println(main.containsAll(others));
		// others�� ��� ���Ҹ� main�� �߰�
		// main.��l(others);
		System.out.println("main - " + main);
		System.out.println("others - " + others);
	}
}
