package uni.yust.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Supermacket {

	private final static int MACHINE_MAX_COUNT = 3;

	private int machine;

	private final ReentrantLock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	public Supermacket() {
		this.machine = MACHINE_MAX_COUNT;
	}

	public void enter() {
		lock.lock();
		try {
			while (this.machine == 0) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			machine--;
		} finally {
			lock.unlock();
		}
	}

	public void leave() {
		lock.lock();
		try {
			this.machine++;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
