package txWeb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author txpc
 * @date 2020年5月6日 下午11:15:54
 */
public class CloneDemo {
	/**
	 * @Title: CloneDemo.java
	 * @Package txWeb
	 * @Description: TODO(描述)
	 * @author txpc
	 * @date 2020年5月6日 下午11:15:54
	 */

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T a) throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject(a);
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream in = new ObjectInputStream(bin);
		return (T) (in.readObject());
	}
}
