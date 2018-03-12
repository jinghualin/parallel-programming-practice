package uni.yust.task4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Comparable<Customer> {
	private final int id = count.getAndAdd(1);
	private String name;
	private int basketCount;
	private static AtomicInteger count = new AtomicInteger(1);

	public Customer(String name) {
		this.name = name;
		this.basketCount = new Random().nextInt(4) + 2;
		System.out.println("-->" + this + " arrives with " + basketCount + " baskets");
	}

	public int getBasketCount() {
		return this.basketCount;
	}

	public String toString() {
		return name + "[" + id + "]";

	}

	@Override
	public int compareTo(Customer c) {
		if (this.name.equals(c.name)) {
			if (this.id > c.id) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return this.name.compareTo(c.name);
		}
	}
}
