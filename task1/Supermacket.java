package uni.yust.task1;

import java.util.LinkedList;
import java.util.Queue;



public class Supermacket {
	private final static int MAX_MACHINE_COUNT = 3;

	// machines which are free now
	private volatile Queue<Machine> machines;
	
	// customers who are waiting for a free machine
	private volatile Queue<Customer> customers;

	public Supermacket() {
		this.machines = new LinkedList<Machine>();

		for (int i = 0; i < MAX_MACHINE_COUNT; i++) {
			this.machines.offer(new Machine());
		}

		this.customers = new LinkedList<Customer>();
	}

	
	/**
	 *  customer tries to use a machine
	 */
	public synchronized void enter() {
		while (machines.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		machineEvent();
	}

	/**
	 * when a customer leaves from supermacket, then add this machine to machine queue
	 * 
	 * @param machine
	 *	
	 */
	public synchronized void leave(Machine machine) {
		this.machines.add(machine);
		notify();
	}

	public boolean hasCustomer() {
		if (customers.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * add a new customer to waiting list
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		this.customers.offer(customer);
		System.out.println("[arrive]: " + customer + " brings " + customer.getBasketCount() + " baskets");
	}

	/**
	 * take out a machine from free list and take out a customer from waiting list
	 * the customer goes to the machine and puts in baskets
	 */
	private void machineEvent() {
		try {

			Machine machine = this.machines.poll();
			this.machines.remove(machine);

			Customer customer = this.customers.poll();
			this.customers.remove(customer);

			Produce produce = new Produce(this, machine, customer);
			new Thread(produce).start();
		} catch (Exception e) {
		}
	}
}
