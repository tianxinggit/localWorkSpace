package txWeb;

import em.SwitchEm;

/**  
 * @author txpc
 * @date 2020年7月31日 上午12:05:14 
 */
public class SwitchTestDemo {
	/**  
	 * @Title: SwitchTestDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年7月31日 上午12:05:14 
	 */
	
	public static void main(String[] args) {
		
		byte b = 1;// byte、short、char、int
		switch (b) {
		case 1:
			break;
		case 2:
			break;
		default:
			break;
		}
		
		//java  5
		switch (SwitchEm.one) {
		case one:
			break;
		case two:
			break;
		default:
			break;
		}
		
		//java 7
		String string = "123";
		switch (string) {
		case "123":
			break;
		case "456":
			break;
		case "":
			break;
		default:
			break;
		}

	}

}
