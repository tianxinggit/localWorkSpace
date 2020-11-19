package txWeb;

import java.util.concurrent.CountDownLatch;

/**  
 * @author txpc
 * @date 2020年4月26日 下午2:55:06 
 */
public class CountDownLatchDemo {
	/**  
	 * @Title: CountDownLatchDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @throws InterruptedException 
	 * @date 2020年4月26日 下午2:55:06 
	 */

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 0; i < 7; i++) {
			final int ii = i+1;
			new Thread(() -> {
				System.out.println(CountryEnum.foreach_CountryEnum(ii).getName()+"  已经被灭");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
			
		}
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName()+"\t全部被灭");
		throw new InterruptedException();
	}
}
