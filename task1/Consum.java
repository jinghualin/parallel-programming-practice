package uni.yust.task1;


public class Consum implements Runnable {
	private Supermacket supermacket;

	public Consum(Supermacket supermacket) {
		this.supermacket = supermacket;
	}

	
	/**
	 * if there is a customer waiting for machine, let him try to find a free one
	 */
	public void run() {
		while (!Thread.interrupted()) {
			if (supermacket.hasCustomer()) {
				supermacket.enter();
			}
		}
	}

}
