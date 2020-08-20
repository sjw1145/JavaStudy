package vm;

public class HumunTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Human h = new Human("철수", 5000);
		
		Drink coke = new Drink("코카콜라", 355, 1500);
		Drink cider = new Drink("사이다", 500, 2000);
		
		VendingMachine vm = new VendingMachine(coke, cider);
		
		h.setDrink(h.order(vm));
		
		System.out.println(h.toStringCheck());
		
		Human y = new Human("영희", 5000);
		y.setDrink(y.order(vm));
	}

}
