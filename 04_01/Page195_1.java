class Circle {
	private int r;
	private int cx;
	private int cy;

	public double area() {
		return r * r * 3.14;
	}
	public int getR() {
		return r;
	}
	public void setR(int newR) {
		r = newR;
	}
	public int getCx() {
		return cx;
	}
	public void setCx(int newCx) {
		cx = newCx;
	}
	public int getCy() {
		return cy;
	}
	public void setCy(int newCy) {
		cy = newCy;
	}
	public String toString() {
		String info = "������ : " + r + "\n";
		info += "�߽���ǥ : " + cx + ", "  + cy + "\n";
		info += "���� ���� : " + area();
		return info;
	}
	public void print() {
		System.out.println(toString());
	}
}
class Page195_1 {
	public static void main(String[] args) {
		Circle c = new Circle();
		c.setR(10);
		c.setCx(5);
		c.setCy(7);
		c.print();
	}
}

















