package txWeb;

import java.util.concurrent.CyclicBarrier;

/**
 * @author txpc
 * @date 2020年4月26日 下午3:33:25
 */
public class CyclicBarrierDemo {
	/**
	 * @Title: CyclicBarrierDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年4月26日 下午3:33:25
	 */

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
			System.out.println("召唤神龙");
		});
		for (int i = 1; i <= 7; i++) {
			final int ii = i;
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t 收集了" + ii + "颗龙珠");
				try {
					
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
	}
}
