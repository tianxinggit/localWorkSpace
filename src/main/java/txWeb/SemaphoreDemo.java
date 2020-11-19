package txWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author txpc
 * @date 2020年4月26日 下午3:41:37
 */
public class SemaphoreDemo {
	/**
	 * @Title: SemaphoreDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年4月26日 下午3:41:37
	 */
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);// 模拟三个停车位

		for (int i = 0; i < 7; i++) {// 6辆车

			new Thread(() -> {
				try {

					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
					// 停车时间
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"\t 离开车位");
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}

}
