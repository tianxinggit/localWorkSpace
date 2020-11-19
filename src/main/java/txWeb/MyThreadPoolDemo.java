package txWeb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author txpc
 * @date 2020年7月23日 下午11:24:23 
 * 四种获得和使用Java多线程的方式 
 * 1继承Thread类
 * 2实现Runnable接口无返回值，不报异常
 * 3实现Callable接口 有返回值，会炮异常
 * 4通过线程池
 */
public class MyThreadPoolDemo {
	
	public static void main(String[] args) {
		ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardPolicy());
		
		try {
			for (int i = 0; i <= 10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName() + "\t 办理业务  ");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}

	}

	public static void ThreadPoolInit() {
		// ExecutorService threadPool = Executors.newFixedThreadPool(5);
		// ExecutorService threadPool = Executors.newCachedThreadPool();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		try {

			for (int i = 0; i <= 10; i++) {

				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "  办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}
