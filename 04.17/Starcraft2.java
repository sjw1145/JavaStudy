abstract class Unit implements IUnitType{
	private String name;
	private int hp;
	private int amor;
	private int speed;
	
	//interface 지상유닛이냐 공중유닛이냐
	private IUnitType unitType;

	public Unit(String name, int hp, int amor, int speed) {
		this.name = name;
		this.hp = hp;
		this.amor = amor;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAmor() {
		return amor;
	}

	public void setAmor(int amor) {
		this.amor = amor;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public IUnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(IUnitType unitType) {
		this.unitType = unitType;
	}
	
	public void move() {
		getUnitType().move();
	}

	@Override
	public String toString() {
		String info = "이름 : " + getName() + "\t체력 : " + getHp() + "\t방어력 : " + getAmor();
		info += "\t스피드 : " + getSpeed();
		return info;
	}
}

interface IUnitType {
	void move();
}

class Ground implements IUnitType {
	@Override
	public void move() {
		System.out.println("지상이동");
	}
	@Override
	public String toString() {
		return "GROUND";
	}
}

class Air implements IUnitType {
	@Override
	public void move() {
		System.out.println("공중이동");
	}
	
	@Override
	public String toString() {
		return "AIR";
	}
}


public interface IAttackable {
	public final static String GROUND ="GROUND";
	
	void attack(Unit unit);
}

abstract class AttackUnit extends Unit implements IAttackable {
	private int power;

	public AttackUnit(String name, int hp, int amor, int speed, int power) {
		super(name, hp, amor, speed);
		this.power = power;
		
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	//공격하다.
	@Override
	public void attack(Unit unit) {

	}

	@Override
	public String toString() {
		return super.toString() + "\t공격력 : " + getPower();
	}
}

public class Mutal extends AttackUnit {
	public Mutal(String name, int hp, int amor, int speed, int power) {
		super(name, hp, amor, speed, power);
		setUnitType(new Air());
	}
}

public class Zealot extends AttackUnit {
	public Zealot(String name, int hp, int amor, int speed, int power) {
		super(name, hp, amor, speed, power);
		setUnitType(new Ground());
	}
}

public class Starcraft2 {
	public static void main(String[] args) {
		Mutal m = new Mutal("zz", 100, 1, 10, 10);
		
		Zealot z = new Zealot("zzzzz", 100, 3, 5, 16);
		Zealot z2 = new Zealot("zzzzzzzzzzz", 100, 3, 5, 16);

		m.move();
		z.move();

		
		z.attack(m);
		z.attack(z);
	}
}