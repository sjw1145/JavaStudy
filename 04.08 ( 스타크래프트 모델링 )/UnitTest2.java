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

//이동하기
	public void move() {
		System.out.println(getName() + "이 이동합니다.");
	}

	@Override
	public String toString() {
		String info = getName() + "\t체력 : " + getHp() + "\t방어력: " + getAmor();
		return info;
	}

}

//공격유닛(공격력, 공격하다)
class AttackUnit extends Unit {
//공격력
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
		System.out.println(getName() + "이 " + unit.getName() + "을 공격합니다");
	}

	public String toString() {
		String info = super.toString() + "\t공격력 : " + getPower();
		return info;
	}
}

//보조유닛(마나, 마나채우기)
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

//마나 채우기
	public void mpCharge() {
		System.out.println("마나를 1초에 1씩 채우고 있습니당..");
	}

	@Override
	public String toString() {
		String info = super.toString() + "\t마나 : " + getMp();
		return info;
	}
}

public class UnitTest2 {

	public static void main(String[] args) {
		AttackUnit zelot = new AttackUnit("질럿", 100, 3, 16);
		AttackUnit mutal = new AttackUnit("뮤탈", 100, 1, 10);
		AssistUnit medic = new AssistUnit("메딕", 80, 0, 150);
		
		System.out.println(zelot.toString());
		System.out.println(mutal.toString());
		System.out.println(medic.toString());
		
		mutal.attack(zelot);
		zelot.attack(medic);
		medic.mpCharge();

	}
}