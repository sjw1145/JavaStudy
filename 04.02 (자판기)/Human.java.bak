package vm;

import java.util.Scanner;

class Human {
	private String name;
	private int money;
	private Drink drink;

	// constructor
	public Human(String name, int money) {
		this.name = name;
		this.money = money;
	}

	// getter , setter
	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Drink getDrink() {
		return drink;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public String toStringCheck() {
		if (getDrink() != null) {
			return toString();
		} else {
			String info = getName() + "는(은) " + getMoney() + "원을 가지고 아무것도 안하고 그냥 갔습니다.";
			return info;
		}
	}

	public String toString() {
		String info = getName() + "는(은) 자판기에서 ";
		info += getDrink().getProduct() + "를 받아 마셨습니다.";
		info += "그리고 돈은 " + getMoney() + "원이 남았습니다";
		return info;
	}

	// 얼마를 넣을까? (사람이 정해야 함)
	public int howMuch() {
		Scanner kb = new Scanner(System.in);
		System.out.print(getName()+"는(은) 자판기에 얼마를 넣을까요? ");
		int howMoney = kb.nextInt();
		// 가진 돈과 얼마를 넣을지 비교해서 가진 돈이 많거나 같으면 넣음
		if (getMoney() >= howMoney) {
			return howMoney;
		} else {
			return 0;
		}
	}

	public int what() {
		Scanner kb = new Scanner(System.in);
		System.out.println("1. 제품선택\t 2. 잔돈반환");
		int choice = kb.nextInt();
		switch (choice) {
			case 1:
				return choice;
			default:	
			case 2:
				return choice;
		}
	}

	public Drink order(VendingMachine vm) {
		// 얼마를 넣을지 정한다.
		int money = howMuch();
		if (money != 0) {
			// 사람의 돈을 넣은 돈 만큼 뺀다.
			setMoney(getMoney() - money);
			// 자판기에 돈을 넣는다.
			vm.moneyInput(money);
			//제품을 선택할건지 제품을 반환할껀지
			int num = what();
			//제품선택
			if(num == 1) {
			// 제품을 선택하면 자판기에서 상품이 나온다.
				Drink temp = vm.productSelect();
				// 제품이 반환됐으면 음료를 받고 잔돈을 받아 리턴함
				if (temp != null) {
					// 그 다음 잔돈을 반환한다. (자판기에서 반환 버튼을 누르는거)
					setMoney(getMoney() + vm.returnMoney());
					return temp;
				}
			} else {
				//잔돈반환
				setMoney(getMoney() + vm.returnMoney());
			}
		}
		return null;
	}
}