package uni.yust.task4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Supermacket {

	private BlockingQueue<Customer> customers;

	public Supermacket() {
		this.customers = new PriorityBlockingQueue<Customer>();
	}

	public void consume() {
		try {
			Customer customer = customers.take();
			int time = 0;
			for (int i = 0; i < customer.getBasketCount(); i++) {
				time = new Random().nextInt(3001) + 3000;
				System.out.println(customer + ": puts No." + (i + 1) + " basket  [" + time + "ms]");
				TimeUnit.MILLISECONDS.sleep(time);
			}
			System.out.println("-->" + customer + " is leaving");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void produce(String name) {
		if (name.equals("NC")) {
			this.customers.add(new Customer("Customer(N)"));
		} else if (name.equals("GC")) {
			this.customers.add(new Customer("Customer(G)"));
		} else {
			System.err.println("Wrong Customer category  (NC for normal customer)(GC for gold customer)");
		}
	}
}
