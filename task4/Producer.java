package uni.yust.task4;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
	private Supermacket supermacket;
	private String name;

	public Producer(Supermacket supermacket, String name) {
		this.supermacket = supermacket;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < (this.name.equals("NC") ? 25 : 10); i++) {
			supermacket.produce(name);
			try {
				TimeUnit.SECONDS.sleep((this.name.equals("NC") ? 8 : 10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
