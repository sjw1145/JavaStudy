/*
	Car Ŭ������ �ۼ��ϰ� ����
	������: ����, ������, ����
	���ı��, ����, ������, ����
*/
import java.util.*;
class Engine implements Comparable<Engine> {
	private	String enMaker;		//������
	private	int displacement;	//��ⷮ

	public Engine(String enMaker, int displacement){
		setEnMaker(enMaker);
		setDisplacement(displacement);
	}
	public String getEnMaker(){
		return enMaker;
	}
	public int getDisplacement(){
		return displacement;
	}
	public void setEnMaker(String enMaker){
		this.enMaker = enMaker;
	}
	public void setDisplacement(int displacement){
		this.displacement=displacement;
	}
	public String toString(){
		String info="������: "+enMaker;
		info+=" ��ⷮ: "+displacement+"\n";
		return info;
	}
	@Override
	public int compareTo(Engine e){
		int n = displacement - e.getDisplacement();
		if(n != 0)){
			return n;
		}else{
			return enMaker.compareTo(getEnMaker());
		}
	}
}
class Car implements Comparable<Car>{
	private String carMaker;
	private int price;
	private Engine engine;

	public Car(String carMaker, int price, Engine engine){
		setCarMaker(carMaker);
		setPrice(price);
		setEngine(engine);
	}
	public String getCarMaker(){
		return carMaker;
	}
	public int getPrice(){
		return price;
	}
	public Engine getEngine(){
		return engine;
	}
	public void setCarMaker(String carMaker){
		this.carMaker = carMaker;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public void setEngine(Engine engine){
		this.engine = engine;
	}
	public String toString(){
		String info = "������: "+carMaker+"\n";
		info += "����: "+price+"\n";
		info += "����"+engine.toString();
		return info;
	}
	@Override
	public int compareTo(Car c){
		int n = price - c.getPrice();
		if(!(n==0)){
			return n;
		} else{
			int m = carMaker.compareTo(c.getCarMaker());
			if(!(m==0)){
				return m;
			}else{
				return engine.compareTo(c.getEngine());
			}
		}
	}
}
class SortCar{
	public static void main(String[] args) {
		Engine e1 = new Engine("ũ���̽���",2000);
		Engine e2 = new Engine("�ؼ�",1000);
		Engine e3 = new Engine("Ÿ�̾�",500);

		Car c1 = new Car("hite", 999, e1);
		Car c2 = new Car("cass", 2150, e2);
		Car c3 = new Car("mac",2500,e3);
		Car c4 = new Car("mac",2500,e2);
		Car c5 = new Car("cass",2500,e3);
		Car c6 = new Car("mac",2500,e1);

		Car[] n  ={c1,c2,c3,c4,c5,c6};
		Arrays.sort(n);
		System.out.print(Arrays.toString(n));
	}
}
