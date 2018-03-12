package uni.yust.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author Jinghua Lin
 *
 */

public class App {
	private static final int CUSTOMER_COUNT = 25;

	public static void main(String[] args) {
		Supermacket supermacket = new Supermacket();
		ExecutorService executorService = Executors.newCachedThreadPool();
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < CUSTOMER_COUNT; i++) {
			executorService.execute(new Customer(supermacket));
			try {
				TimeUnit.SECONDS.sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();
	}
}
