/*
	powerjava 172p 1��
	�簢���� ��Ÿ���� Ŭ���� Rectangle�� ������. 
	�簢���� ����(w)�� ����(h)�� ������, �簢���� ���̸� ��ȯ�ϴ� area(), 
	�簢���� �ѷ��� ��ȯ�ϴ� perimeter()���� �޼ҵ带 ������. 
	rectangle Ŭ������ �ۼ��ϰ� ��ü�� �����Ͽ� �׽�Ʈ�϶�. 
*/

class RecTangle{
	int w;
	int h;

	int area(){
		return w * h;
	}

}

class UseRecTangle {
	public static void main(String[] args) {
		RecTangle rt = new RecTangle();
		rt.w = 5;
		rt.h = 3;
		rt.area();
	}
}
