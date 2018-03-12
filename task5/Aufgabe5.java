package task5;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author jinghua
 *
 *
 *         Aufgabe 5 : Bearbeite 5.3 Hilzer’s Barbershop Problem
 */
public class Aufgabe5 {

	private int customers = 0;
	private final Semaphore mutex = new Semaphore(1);
	private final BlockingQueue<Class<Void>> standingRoom = new ArrayBlockingQueue<>(16, true);
	private final BlockingQueue<Class<Void>> sofa = new ArrayBlockingQueue<>(4, true);
	private final Semaphore chair = new Semaphore(3);
	private final Semaphore barber = new Semaphore(0);
	private final Semaphore customer = new Semaphore(0);
	private final Semaphore cash = new Semaphore(0);
	private final Semaphore receipt = new Semaphore(0);

	private final static ExecutorService customerPool = Executors.newFixedThreadPool(20);
	private final static ExecutorService barberPool = Executors.newFixedThreadPool(3);
	private final static CountDownLatch latch = new CountDownLatch(100);

	public static void main(String[] args) {
		Aufgabe5 aufgabe5 = new Aufgabe5();

		for (int i = 0; i < 3; i++) {
			barberPool.submit(aufgabe5.new Barber(i));
		}
		for (int i = 0; i < 100; i++) {
			customerPool.submit(aufgabe5.new Customer(i));
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		customerPool.shutdown();
		barberPool.shutdown();
	}

	private class Barber implements Runnable {
		private final int id;

		public Barber(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			while (true) {
				customer.acquireUninterruptibly();
				barber.release();
				cutHair();

				cash.acquireUninterruptibly();
				acceptPayment();
				receipt.release();
			}
		}

		private void acceptPayment() {
			System.out.println("Barber " + id + " accept payment");
		}

		private void cutHair() {
			System.out.println("Barber " + id + " cut hair");
		}

	}

	private class Customer implements Runnable {
		private final int id;

		public Customer(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				mutex.acquireUninterruptibly();
				if (customers == 20) {
					mutex.release();
					exitShop();
					System.out.println("Shop is full. Customer " + id + " is leaving");
					return;
				}

				customers++;
				mutex.release();

				standingRoom.put(Void.class);
				enterShop();

				sofa.put(Void.class);
				sitOnSofa();
				standingRoom.take();

				chair.acquireUninterruptibly();
				sitInBarberChair();
				sofa.take();

				customer.release();
				barber.acquireUninterruptibly();
				getHairCut();

				chair.release();
				pay();
				cash.release();
				receipt.acquireUninterruptibly();

				mutex.acquireUninterruptibly();
				customers--;
				mutex.release();

				exitShop();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

		private void enterShop() {
			System.out.println("Customer " + id + " enter shop");
		}

		private void sitOnSofa() {
			System.out.println("Customer " + id + " sit on sofa");
		}

		private void sitInBarberChair() {
			System.out.println("Customer " + id + " sit in barber chair");
		}

		private void getHairCut() {
			System.out.println("Customer " + id + " get haircut");
			try {
				TimeUnit.SECONDS.sleep(new Random().nextInt(5) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void pay() {
			System.out.println("Customer " + id + " pay");
		}

		private void exitShop() {
			System.out.println("Customer " + id + " exit shop");
		}
	}
}
