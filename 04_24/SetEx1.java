/*
	Collection
		- List : +�ε���
		- Set : +�ߺ��� ������� ����
*/
import java.util.*;
class SetEx1 {
	public static void main(String[] args)	{
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("a");
		set.add("a");
		set.add("a");
		set.add("a");
		set.add("a");

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");

		System.out.println(set.size());
		System.out.println(list.size());

	}
}
