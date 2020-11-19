package txWeb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author txpc
 * @date 2020年4月26日 下午5:04:01
 */

class ShareData {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws Exception {
		lock.lock();
		try {
			if (number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		try {
			if (number == 0) {
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class ProdConsumer_TraditionDemo {
	/**
	 * @Title: ProdConsumer_TraditionDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年4月26日 下午5:04:01
	 */

	public static void main(String[] args) {

		String string = new String("123");
		char[] charArray = string.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = charArray.length - 1;i>=0;i--) {
			char c = charArray[i];
			sb.append(String.valueOf(c));
		}
		System.out.println(sb.toString());
		StringBuilder sbBuilder = new StringBuilder(string);
		sbBuilder.reverse();
		ShareData shareData = new ShareData();
		long currentTimeMillis = System.currentTimeMillis();
		LinkedList<Integer> lliIntegers = new LinkedList<>();
		for (int i = 0; i < 60000000; i++) {
			lliIntegers.add(i);
		}
		lliIntegers.add(123,123);
		System.out.println((System.currentTimeMillis() - currentTimeMillis));
		long m2 = System.currentTimeMillis();
		ArrayList<Integer> aliIntegers = new ArrayList<>();
		for (int i = 0; i < 60000000; i++) {
			aliIntegers.add(i);
		}
		aliIntegers.add(123,123);
		System.out.println((System.currentTimeMillis() - m2));
		
		
		/*new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}, "AA").start();
		new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}, "BB").start();
		new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}, "CC").start();
		new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}, "DD").start();*/
	}
}
