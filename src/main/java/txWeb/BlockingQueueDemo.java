package txWeb;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.objenesis.instantiator.basic.NewInstanceInstantiator;

/**
 * @author txpc
 * @date 2020年4月26日 下午4:37:00
 */
public class BlockingQueueDemo {
	/**
	 * @Title: BlockingQueueDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @throws InterruptedException
	 * @throws CloneNotSupportedException 
	 * @date 2020年4月26日 下午4:37:00
	 */

	public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
//		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//		blockingQueue.put("a");
//		blockingQueue.put("a");
//		blockingQueue.put("a");
//		blockingQueue.take();
//		blockingQueue.add("a");
//		int a = 1;
//		int b = 1;
//		Integer A = 1;
//		Integer B = 1;
//		String aa = "1";
//		String bb = "1";
//		String cc = new String("1");
//		System.out.println(a == b);
//		System.out.println(A == B);
//		System.out.println(aa == bb);
//		System.out.println(aa == cc);
//		System.out.println(A.equals(B));
//		System.out.println(aa.equals(bb));
//		System.out.println(aa.equals(cc));
		
		Student student = new Student();
		student.setAge(12);
		student.setName("qqq");
		IdCard idCard = new IdCard();
		idCard.setDate(new Date());
		idCard.setId(19941128);
		student.setIdCard(idCard);
		Student clone = null;
		try {
			clone = (Student)CloneDemo.clone(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(clone);
		System.out.println(clone.hashCode());
		System.out.println(student.hashCode());
	}
}

class Student implements Cloneable,Serializable {

	private static final String Student = null;
	public String name;
	public Integer age;
	public IdCard idCard;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Student student = (Student) super.clone();
		student.idCard = (IdCard)idCard.clone();
		return student;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", idCard=" + idCard + "]";
	}
	
	Student(){}
	Student(String name, Integer age, IdCard idCard){
		this.name = name;
		this.age = age;
		this.idCard = idCard;
	}
	

}
class IdCard implements Cloneable,Serializable {
	public Integer id;
	public Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", date=" + date + "]";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (IdCard) super.clone();
	}
	IdCard(){}
	IdCard(Integer id, Date date){
		this.id = id;
		this.date = date;
	}
	
}