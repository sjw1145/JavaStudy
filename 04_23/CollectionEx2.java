import java.util.*;
class CollectionEx2 {
	public static void main(String[] args)	{
		// ���� -> index -> �ĺ���
		Collection<Integer> c = new HashSet<Integer>();

		c.add(3);
		c.add(1);
		c.add(2);
		c.add(5);
		c.add(4);

		// ���� ������ ������ ��ü�� ��´�(Iterator)
		Iterator<Integer> itr = c.iterator();

		while(itr.hasNext()) {
			int num = itr.next();
			System.out.println(num);
		}

		// JDK1.5~
		for(Integer i : c) {
			System.out.println(i);
		}
	}
}