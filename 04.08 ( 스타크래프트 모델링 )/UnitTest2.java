class Unit {
	private String name;
	private int hp;
	private int amor;

	public Unit(String name, int hp, int amor) {
		this.name = name;
		this.hp = hp;
		this.amor = amor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAmor(int amor) {
		this.amor = amor;
	}

	public int getHp() {
		return hp;
	}

	public int getAmor() {
		return amor;
	}

//�̵��ϱ�
	public void move() {
		System.out.println(getName() + "�� �̵��մϴ�.");
	}

	@Override
	public String toString() {
		String info = getName() + "\tü�� : " + getHp() + "\t����: " + getAmor();
		return info;
	}

}

//��������(���ݷ�, �����ϴ�)
class AttackUnit extends Unit {
//���ݷ�
	private int power;

	public AttackUnit(String name, int hp, int amor, int power) {
		super(name, hp, amor);
		this.power = power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void attack(Unit unit) {
		System.out.println(getName() + "�� " + unit.getName() + "�� �����մϴ�");
	}

	public String toString() {
		String info = super.toString() + "\t���ݷ� : " + getPower();
		return info;
	}
}

//��������(����, ����ä���)
class AssistUnit extends Unit {
	private int mp;

	public AssistUnit(String name, int hp, int amor, int mp) {
		super(name, hp, amor);
		this.mp = mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMp() {
		return mp;
	}

//���� ä���
	public void mpCharge() {
		System.out.println("������ 1�ʿ� 1�� ä��� �ֽ��ϴ�..");
	}

	@Override
	public String toString() {
		String info = super.toString() + "\t���� : " + getMp();
		return info;
	}
}

public class UnitTest2 {

	public static void main(String[] args) {
		AttackUnit zelot = new AttackUnit("����", 100, 3, 16);
		AttackUnit mutal = new AttackUnit("��Ż", 100, 1, 10);
		AssistUnit medic = new AssistUnit("�޵�", 80, 0, 150);
		
		System.out.println(zelot.toString());
		System.out.println(mutal.toString());
		System.out.println(medic.toString());
		
		mutal.attack(zelot);
		zelot.attack(medic);
		medic.mpCharge();

	}
}