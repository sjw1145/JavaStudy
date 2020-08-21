class Plane {
	private String maker;
	private String model;
	private int maxCount;		// �ִ�°���
	
	// �ʱⰪ
	private static int planes;

	public Plane() {
		/*
		setMaker("����");
		setModel("747");
		setMaxCount(400);
		*/
		this("����", "747", 400);
	}
	public Plane(String maker, String model, int maxCount) {
		setMaker(maker);
		setModel(model);
		setMaxCount(maxCount);
		planes++;
	}
	public static int getPlanes() {
		return planes;
	}
	public String getMaker() {
		return maker;
	}
	public String getModel() {
		return model;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public String toString() {
		String info = "���ۻ� : " + maker + "\n";
		info += "�� : " + model + "\n";
		info += "�ִ�°��� : " + maxCount;
		return info;
	}
}

class PlaneTest {
	public static void main(String[] args) {
		Plane p1 = new Plane();
		p1.setMaxCount(200);
		Plane p2 = new Plane("�������", "A380", 500);
		p2.setModel("A380-1");

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(Plane.getPlanes());
	}
}
