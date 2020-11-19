package txWeb;

/**
 * @author txpc
 * @date 2019年12月4日 上午10:26:48
 */
public class MessageUtil {

	/**
	 * returnByte头2位为（returnByte-2）长度
	 */
	public static byte[] addHeadByte(byte[] returnByte) {
		byte[] headLenght = new byte[2];
		String hexStr = Integer.toHexString(returnByte.length - 2);
		String lenString = ISO8583Utile.StringFillLeftZero(hexStr, 4);
		headLenght = ISO8583Utile.string2Bcd(lenString);
		System.arraycopy(headLenght, 0, returnByte, 0, headLenght.length);
		return returnByte;
	}
}
