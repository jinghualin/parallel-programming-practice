package uni.yust.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppMain {

	public static void main(String[] args) {

		Supermacket supermacket = new Supermacket();

		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("N for normal customer, G for gold customer");
		Producer normalProducer = new Producer(supermacket, "NC");
		Producer goldProducer = new Producer(supermacket, "GC");
		Consumer consumer = new Consumer(supermacket);

		executorService.submit(normalProducer);
		executorService.submit(goldProducer);

		for (int i = 0; i < 3; i++) {
			executorService.submit(consumer);
		}
		executorService.shutdown();
	}
}
