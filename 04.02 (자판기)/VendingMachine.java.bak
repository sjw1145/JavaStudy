package vm;

import java.util.Scanner;

class VendingMachine {
	private String color;
	private int money;
	private String manufacturer;
	private Drink cola;
	private Drink cider;
	private int colaStock;
	private int ciderStock;
	
	//생성자
	public VendingMachine() {
		this.color = "초록";
		this.money = 0;
		this.manufacturer = "롯데칠성";
	}
	public VendingMachine(Drink cola, Drink cider) {
		this();
		this.cola = cola;
		this.cider = cider;
		this.colaStock++;
		this.ciderStock++;
	}

	public int getColaStock() {
		return colaStock;
	}
	public void setColaStock(int colaStock) {
		this.colaStock = colaStock;
	}
	public int getCiderStock() {
		return ciderStock;
	}
	public void setCiderStock(int ciderStock) {
		this.ciderStock = ciderStock;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Drink getCola() {
		return cola;
	}
	public void setCola(Drink cola) {
		this.cola = cola;
	}
	public Drink getCider() {
		return cider;
	}
	public void setCider(Drink cider) {
		this.cider = cider;
	}

	// 1 . 금액 투입
	public void moneyInput(int money) {
		Scanner kb = new Scanner(System.in);
		// 자판기에 금액 설정
		setMoney(money);
		System.out.println(toString());
	}

	// 2. 제품 선택과 동시에 반환
	public Drink productSelect() {
		Scanner kb = new Scanner(System.in);
		Drink temp;
		
		System.out.println("1. 콜라\t 2.사이다");
		int choice = kb.nextInt();
		switch (choice) {
			// 잘 못 선택하면 무조건 콜라반환
			default:
			case 1:
				// 선택한 제품 반환
				temp = returnDrink(choice);
				setColaStock(getColaStock()-1);
				return temp;
			case 2:
				setCiderStock(getCiderStock()-1);
				temp = returnDrink(choice);
				return temp;	
		}
	}

	// 3. 제품 반환 메소드
	private Drink returnDrink(int choice) {
		Drink temp;
		switch (choice) {
			default:
			case 1:
				temp = getCola();
				// 자판기 현재 금액에서 음료의 가격을 뺌
				setMoney(getMoney() - cola.getPrice());
				cola = null;
				return temp;
			case 2:
				temp = getCider();
				// 자판기 현재 금액에서 음료의 가격을 뺌
				setMoney(getMoney() - getCider().getPrice());
				cider = null;
				return temp;
		}
	}

	// 4. 잔돈 반환
	public int returnMoney() {
		if (getMoney() > 0) {
			System.out.println("잔돈 : "+getMoney());
			return getMoney();
		} else {
			System.out.println("반환 할 돈이 없음!");
			return 0;
		}
	}
	
	public String toString() {
		String info = "자판기 [ "+getManufacturer()+"\t"+ getColor()+" ]"+"\t현재 금액 : " + getMoney()+"\n";
		info += "재고 [ 코카콜라 : " + getColaStock() + " 사이다 : " + getCiderStock()+" ]";
		return info;
	}
}