/*
	ö���� ���� �����Ѵ�.(�̻ڴϱ� �����Ѵ�)
	����� ö���� �������� �ʴ´�.(�����ܼ� �������� �ʴ´�)

	faceLv : int
	faceLv >= 5 : �̻ڴ�, �߻����
	faceLv < 5 : ������� 

	Human
	====================
	- name : String
	- faceLv : int
	====================
	+ getName() : String
	+ getFaceLv() : int
	+ setName(newName:String) : void
	+ setFaceLv(newFaceLv:int) : void
	+ like(someone:Human) : boolean

*/
class Human {
	private String name;
	private int faceLv;

	public String getName() {
		return name;
	}
	public int getFaceLv() {
		return faceLv;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void setFaceLv(int newFaceLv) {
		faceLv = newFaceLv;
	}
	// h2.like(h1)
	public boolean like(Human someone) {
		boolean like = true;
		if(someone.getFaceLv() < 5) {
			like = false;
		}		
		System.out.println(
			name + (like ? " like " : " hate ") + someone.getName() 	
		);
		return like;
	}
	// h2.doULikeMe(h1)
	public boolean doULikeMe(Human h) {
		return h.like(this);
	}
	public void tuning() {
		int random = (int)(Math.random() * 9) + -2;
		faceLv += random;
		System.out.println("�ܸ�ȭ ��ġ : " + random);
	}
}
class HumanTest {
	public static void main(String[] args) {
		Human h1 = new Human();
		h1.setName("ö��");
		h1.setFaceLv(3);

		Human h2 = new Human();
		h2.setName("����");
		h2.setFaceLv(8);

		//System.out.println(h1.like(h2));
		//System.out.println(h2.like(h1));

		h1.tuning();
		System.out.println(h2.like(h1));
		System.out.println(h2.doULikeMe(h1));
	}
}
