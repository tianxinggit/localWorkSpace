package txWeb;

/**
 * @author txpc
 * @date 2020年7月27日 下午11:26:25
 */

class HoldLockThread implements Runnable {

	private String locka;
	private String lockb;

	public HoldLockThread(String locka, String lockb) {

		this.locka = locka;
		this.lockb = lockb;

	}

	@Override
	public void run() {

		synchronized (locka) {
			System.out.println(Thread.currentThread().getName() + "自己持有：" + locka + " 尝试获取：" + lockb);
			synchronized (lockb) {
				System.out.println(Thread.currentThread().getName() + "自己持有：" + lockb + " 尝试获取：" + locka);
			}
		}
	}

}

public class DeadLockDemo {

	public static void main(String[] args) {
		String locka = "locka";
		String lockb = "lockb";

		new Thread(new HoldLockThread(locka, lockb), "ThreadAAA").start();

		new Thread(new HoldLockThread(lockb, locka), "ThreadBBB").start();
	}

}
