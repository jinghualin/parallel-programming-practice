package uni.yust.task1;

public class App {
	public static void main(String[] args) {

		Supermacket supermacket = new Supermacket();
		Consum consum = new Consum(supermacket);

		Thread thread = new Thread(consum);
		thread.start();
		

		// every 8 seconds comes a new customer
		for (int i = 0; i < 25; i++) {
			supermacket.addCustomer(new Customer());
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
