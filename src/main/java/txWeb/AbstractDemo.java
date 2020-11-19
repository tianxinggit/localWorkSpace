package txWeb;
/**  
 * 抽象类示例
 * @author txpc
 * @date 2020年6月10日 下午3:13:36 
 */
public abstract class AbstractDemo {
	/**  
		1.抽象类中可以定义构造器 
		2.可以有抽象方法和具体方法 
		3.接口中的成员全都是public的 
		4.抽象类中可以定义成员变量 
		5.有抽象方法的类必须被声明为抽象类，而抽象类未必要有抽象方法 
		6.抽象类中可以包含静态方法
		7.一个类只能继承一个抽象类 
	 */

	private static final String aa = "aa";
	
	AbstractDemo(){
		
	}
	public abstract void test();
	
	protected abstract void testq();
	
	public void testw(){
		
	}
	
	public static void teste(){
		
	}
}
