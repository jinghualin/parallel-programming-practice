package uni.yust.task4;

public class Consumer implements Runnable {

	private Supermacket supermacket;
	public Consumer(Supermacket supermacket) {
		this.supermacket = supermacket;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			supermacket.consume();
		}
	}
}
