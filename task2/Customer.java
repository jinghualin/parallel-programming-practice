package uni.yust.task2;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {

	private static int counter = 1;
	private final int id = counter++;
	private int basketCount;
	private Supermacket supermacket;

	private Random random = new Random();

	public Customer(Supermacket supermacket) {
		this.basketCount = new Random().nextInt(4) + 2;
		this.supermacket = supermacket;
	}

	public void run() {
		supermacket.enter();

		int time = 0;
		for (int i = 0; i < this.basketCount; i++) {
			time = random.nextInt(3001) + 3000;
			System.out.println("Customer[" + id + "]: puts No." + (i + 1) + " basket  [" + time + "ms]");
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		supermacket.leave();
	}

}
