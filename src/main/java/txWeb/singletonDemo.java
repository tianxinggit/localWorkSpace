package txWeb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * 
 *  懒汉式和饿汉式区别：
	实例化方面：懒汉式默认不会实例化，外部什么时候调用什么时候new。饿汉式在类加载的时候就实例化，并且创建单例对象。
	线程安全方面：
	饿汉式线程安全 (在线程还没出现之前就已经实例化了，因此饿汉式线程一定是安全的)。
	懒汉式线程不安全( 因为懒汉式加载是在使用时才会去new 实例的，那么你去new的时候是一个动态的过程，是放到方法中实现的，
	比如：
	<blockquote><pre>
	public static synchronized singletonDemo getInstance(){
		if(null != instance){
			instance = new singletonDemo();
		}
		return instance;
	}
	</pre></blockquote>
	如果这个时候有多个线程访问这个实例，这个时候实例还不存在，还在new，就会进入到方法中，有多少线程就会new出多少个实例。一个方法只能return一个实例，那最终return出哪个呢？是不是会覆盖很多new的实例？这种情况当然也可以解决，那就是加同步锁，避免这种情况发生) 。
	执行效率上：饿汉式没有加任何的锁，因此执行效率比较高。懒汉式一般使用都会加同步锁，效率比饿汉式差。
	性能上：饿汉式在类加载的时候就初始化，不管你是否使用，它都实例化了，所以会占据空间，浪费内存。懒汉式什么时候需要什么时候实例化，相对来说不浪费内存。
 
 * @author txpc
 * @date 2020年4月9日 下午5:28:24 
 * 
 */
public class singletonDemo {
	
	public static void main(String[] args) {
		
		Lock lock = null;
		lock.tryLock();
		new ReentrantLock();
	}
	
	// 懒汉式
	public static singletonDemo instance = null;
	private singletonDemo(){
	}
	public static singletonDemo getInstance(){
		if(null != instance){
			instance = new singletonDemo();
		}
		return instance;
	}
	// 饿汉式
	public static final singletonDemo hungryInstance = new singletonDemo();
	public static singletonDemo getHungryInstance(){
		return hungryInstance;
	}
	
}
