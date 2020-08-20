package vm;

class Drink {
	private String product;
	private int size;
	private int price;

	public Drink(String product, int size, int price) {
		this.product = product;
		this.size = size;
		this.price = price;
	}

	// setter, getter
	public void setProduct(String product) {
		this.product = product;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public int getSize() {
		return size;
	}

	public int getPrice() {
		return price;
	}
}