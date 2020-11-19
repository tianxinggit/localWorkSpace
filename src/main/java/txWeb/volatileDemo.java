package txWeb;

import java.util.concurrent.atomic.AtomicInteger;

/**  
 * @author txpc
 * @date 2020年4月9日 下午3:45:18 
 */
public class volatileDemo {
	/**  
	 * @Title: volatileDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年4月9日 下午3:45:18 
	 */

	public static void main(String[] args) {
		
//		seeOkDemo();
		mydate md = new mydate();
		for (int i = 0; i < 20; i++) {

			new Thread(() -> {

				for (int j = 0; j < 1000; j++) {
					md.addPlusPlus();
					md.addPlusPluss();
				}
			}, String.valueOf(i)).start();
		}
		while(Thread.activeCount() > 2){
			Thread.currentThread().yield();
		}
		System.out.println(Thread.currentThread().getName()+" final atomicnumber:"+md.an);
		System.out.println(Thread.currentThread().getName()+" final number:"+md.number);
	}

	public static void seeOkDemo() {
		mydate md = new mydate();
		new Thread(() -> {

			System.out.println(Thread.currentThread().getName()+"\t begin add");
			try {
				Thread.currentThread().sleep(3000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
			md.addTo60();
			System.out.println(Thread.currentThread().getName()+"\t end of add,number:" + md.number);
		}, "AAA").start();
		
		while(md.number == 0){
			
		}
		System.out.println(Thread.currentThread().getName()+"\t end of main,number:" + md.number);
	}
}

class mydate{
	volatile int number = 0;
	public void addTo60(){
		this.number = 60;
	}
	AtomicInteger an = new AtomicInteger();
	public void addPlusPluss(){
		number++;
	}
	public void addPlusPlus(){
		an.getAndIncrement();
	}
}