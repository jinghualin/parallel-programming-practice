package uni.yust.task3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Supermacket {

	private static int ID = 1;
	private static final int CustomerMaxCount = 25;

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		final Semaphore semaphore = new Semaphore(3);
		Random random = new Random();
		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(1);
		AtomicInteger count = new AtomicInteger(0);

		Runnable customerTask = () -> {
			final int customerId = ID++;
			final int basketCount = random.nextInt(4) + 2;
			int time = 0;
			System.out.println("Customer[" + customerId + "] arrives with " + basketCount +" baskets.");
			try {
				semaphore.acquire();
				for (int i = 0; i < basketCount; i++) {
					time = random.nextInt(3001) + 3000;
					System.out
							.println("Customer[" + customerId + "]: puts No." + (i + 1) + " basket  [" + time + "ms]");
					try {
						TimeUnit.MILLISECONDS.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Customer[" + customerId + "] is leaving.");
				semaphore.release();
			}
		};

		Runnable task = () -> {
			
			if (count.incrementAndGet() <= CustomerMaxCount) {
				executorService.submit(customerTask);
			} else {
				countDownLatch.countDown();
			}
		};

		scheduledExecutorService.scheduleAtFixedRate(task, 0, 7, TimeUnit.SECONDS);
		try {
			countDownLatch.await();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledExecutorService.shutdown();
		executorService.shutdown();
		
	}

}
