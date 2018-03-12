package uni.yust.task1;

public class Customer {
	private static int counter = 1;
	private final int id = counter++;

	private int basketCount;

	public Customer() {
		this.basketCount = (int) (Math.random() * 100 % 4 + 2); // randomly from 2 to 5 baskets
	}

	public String toString() {
		return "Customer [id=" + id + "]";
	}

	public int getBasketCount() {
		return this.basketCount;
	}
}
