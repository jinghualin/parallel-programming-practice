package uni.yust.task1;


/**
 * 
 * @author jinghua
 *
 *
 * Pfandautomat
 */
public class Machine {
	private static int counter = 1;
	private final int id = counter++;

	public String toString() {
		return "Machine [id=" + id + "]";
	}

	/*
	 * receive a basket randomly from 3 to 6 second
	 */
	public void receiveBasket(){
		try {
			Thread.sleep((long) (Math.random() * 100 % 4 + 3) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
