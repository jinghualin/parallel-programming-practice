package uni.yust.task1;


/**
 * 
 * @author jinghua
 *
 *
 * A Thread that a customer puts baskets into machine
 */
public class Produce implements Runnable {

	private Supermacket supermacket;
	private Machine machine;
	private Customer customer;

	public Produce(Supermacket supermacket, Machine machine, Customer customer) {
		this.supermacket = supermacket;
		this.customer = customer;
		this.machine = machine;
	}

	public void run() {
		System.out.println("-> " + customer + " goes to " + machine + " with " + customer.getBasketCount() + " baskets");
		for (int i = 0; i < customer.getBasketCount(); i++) {
			System.out.println("--> " + customer + " puts No." + (i + 1) + " basket");
			this.machine.receiveBasket();
		}
		System.out.println("[leave]" + customer + " is leaving and " + machine + " is free");
		supermacket.leave(machine);  // add the machine to available machine list
	}
}
