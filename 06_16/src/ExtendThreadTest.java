
public class ExtendThreadTest {
	public static void main(String[] args) {
		ExtendThread et = new ExtendThread();
		//Start �� �̿��Ͽ� �����带 ����
		//run�� ����ǰ� run �� ����Ǹ� ExtendThread �� �Ҹ�
		//java.lang
		et.start();
		System.out.println("end of main");
	}
}
