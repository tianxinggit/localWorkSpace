package txWeb;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author txpc
 * @date 2020年4月14日 下午9:41:35
 */
public class CASDemo {
	public static void main(String[] args) {

		AtomicInteger atomicInteger = new AtomicInteger(2222);
		System.out.println(atomicInteger.compareAndSet(2222, 3333) + "/t :" + atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(2222, 4444) + "/t :" + atomicInteger.get());
		AtomicReference<User> arUserAtomicReference = new AtomicReference<User>();
		// AtomicStampedReference<V>
		new ArrayList<>();
		// Collections.synchronizedList(list)
		int i = 10;
		int j = i >> 1;
		System.out.println(j);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class User {
	public String name;

	public int age;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	User() {
	}
}
