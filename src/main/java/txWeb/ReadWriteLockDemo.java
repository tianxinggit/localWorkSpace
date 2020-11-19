package txWeb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author txpc
 * @date 2020年4月26日 下午2:32:03
 */
public class ReadWriteLockDemo {
	/**
	 * @Title: ReadWriteLockDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年4月26日 下午2:32:03
	 */
	
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for (int i = 0; i < 5; i++) {
			final int ii = i;
			new Thread(()->{
				myCache.put(ii+"", ii+"");
			},String.valueOf(i)).start();
		}
		for (int i = 0; i < 5; i++) {
			final int ii = i;
			new Thread(()->{
				myCache.get(ii+"");
			},String.valueOf(i)).start();
		}
	}
}
class MyCache {
	private volatile Map<String, Object> map = new HashMap<String, Object>();
	private ReentrantReadWriteLock rwlockLock = new ReentrantReadWriteLock();

	public void put(String key, Object value) {
		rwlockLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在写入： " + key);
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t 写入完成 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			rwlockLock.writeLock().unlock();
		}
		

	}
	
	public void get(String key){
		rwlockLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在读取");
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object resultObject = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t 读取完成："+resultObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			rwlockLock.readLock().unlock();
		}
		
		
	}
}
