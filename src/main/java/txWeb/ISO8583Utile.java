package txWeb;

import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * @author txpc
 * @date 2019年12月4日 上午10:26:07 
 */
public class ISO8583Utile {


	public static String int2char2(int val) {
		DecimalFormat fmt = new DecimalFormat("00");

		if ((val < 0) || (val > 99))
			return null;
		return fmt.format(val);
	}

	public static String int2char3(int val) {
		DecimalFormat fmt = new DecimalFormat("000");

		if ((val < 0) || (val > 999))
			return null;
		return fmt.format(val);
	}

	public static String StringFillLeftZero(String str, int len) {
		if (str.length() < len) {
			StringBuffer sb = new StringBuffer(len);
			for (int i = 0; i < len - str.length(); i++)
				sb.append('0');
			sb.append(str);
			return new String(sb);
		} else
			return str;
	}

	public static String StringFillRightZero(String str, int len) {
		if (str.length() < len) {
			StringBuffer sb = new StringBuffer(len);
			sb.append(str);
			for (int i = 0; i < len - str.length(); i++)
				sb.append('0');
			return new String(sb);
		} else
			return str;
	}

	public static String StringFillRightBlank(String str, int len) {
		if (str.length() < len) {
			StringBuffer sb = new StringBuffer(len);
			sb.append(str);
			for (int i = 0; i < len - str.length(); i++)
				sb.append(' ');
			return new String(sb);
		} else
			return str;
	}

	/** BCD码转为10进制串 */
	public static String bcd2String(byte[] bytes, boolean deleteflag) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append((byte) ((bytes[i] & 0xf0) >>> 4)).append((byte) (bytes[i] & 0x0f));
		}
		return deleteflag ? sb.substring(1) : sb.toString();
	}

	/** 10进制转为BCD码 * */
	public static byte[] string2Bcd(String str) {
		String s = str.toUpperCase();
		if (s.length() % 2 != 0)
			s = "0" + s;
		byte[] buffer = new byte[s.length() / 2];
		for (int i = 0; i < buffer.length; i++) {
			int i1, i2;
			char c1, c2;
			c1 = s.charAt(2 * i);
			if (c1 - 'A' >= 0) {
				i1 = c1 - 'A' + 10;
			} else {
				i1 = c1 - '0';
			}
			c2 = s.charAt(2 * i + 1);
			if (c2 - 'A' >= 0) {
				i2 = c2 - 'A' + 10;
			} else {
				i2 = c2 - '0';
			}
			buffer[i] = (byte) (i1 * 0x10 + i2);
		}
		return buffer;
	}

	// 字符串转制成ASCII码，返回对应的字节数组
	public static byte[] getAsciiBytes(String input) {
		char[] c = input.toCharArray();
		byte[] b = new byte[c.length];
		for (int i = 0; i < c.length; i++)
			b[i] = (byte) (c[i] & 0x007F);

		return b;
	}

	public static BitSet getBitMap(byte[] bitmap, int len) {
		if (bitmap == null)
			return null;
		BitSet bs = new BitSet(128 + 1);
		for (int i = 0; i < len * 8; i++) {
			if ((bitmap[i / 8] & (0x80 >>> (i % 8))) != 0)
				bs.set(i + 1, true);
		}
		return bs;
	}

	public static String Byte2Hex(byte[] bs, int len) {
		return Byte2Hex(bs, 0, len);
	}

	public static String Byte2Hex(byte[] bs, int offset, int len) {
		StringBuffer sb = new StringBuffer();
		byte hi, lo;
		for (int i = offset; i < offset + len; i++) {
			hi = (byte) ((bs[i] >> 4) & 0x0f);
			lo = (byte) (bs[i] & 0x0f);

			hi = (hi < 10 ? (byte) (hi + '0') : (byte) (hi - 10 + 'A'));
			lo = (lo < 10 ? (byte) (lo + '0') : (byte) (lo - 10 + 'A'));

			sb.append((char) hi);
			sb.append((char) lo);
		}
		return new String(sb);
	}

	public static boolean isValidDecString(String str) {
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch < '0' || ch > '9')
				return false;
		}
		return true;
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/*
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hex
	 * 
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	// 对字节数组分组后做异或运算

	public static String getXorString(byte[] xorbyte) {
		String hexString = "";
		int len = xorbyte.length;// 做异或运算数组的长度
		int remainderlen = 0;// 余数
		int addlen = 0;
		byte[] addbyte = new byte[8];
		if (len % 8 != 0) {
			remainderlen = len % 8;// 余数
			addlen = 8 - remainderlen;
			for (int i = 0; i < addlen; i++) {
				addbyte[i] = 0x00;
			}
		}
		byte[] Xorbyte = new byte[len + addlen];
		System.arraycopy(xorbyte, 0, Xorbyte, 0, len);
		if (addlen > 0) {
			System.arraycopy(addbyte, 0, Xorbyte, len, addlen);
		}

		int number = (len + addlen) / 8;// 循环次数
		// 分组进行异或运算
		byte[] xorCycleA = new byte[8];// 异或运算的数组
		byte[] xorCycleB = new byte[8];// 异或运算的数组
		byte[] resultCycle = new byte[8];// 异或运算的结果数组
		if (number > 2) {
			// 先做第一组和第二组的异或
			System.arraycopy(Xorbyte, 0, xorCycleA, 0, 8);
			System.arraycopy(Xorbyte, 8, xorCycleB, 0, 8);
			resultCycle = getXorbyte(xorCycleA, xorCycleB);
			for (int i = 2; i < number; i++) {
				System.arraycopy(resultCycle, 0, xorCycleA, 0, 8);
				System.arraycopy(Xorbyte, i * 8, xorCycleB, 0, 8);
				resultCycle = getXorbyte(xorCycleA, xorCycleB);
			}
			hexString = ISO8583Utile.bytesToHexString(resultCycle);
		} else if (number == 2) {
			System.arraycopy(Xorbyte, 0, xorCycleA, 0, 8);
			System.arraycopy(Xorbyte, 8, xorCycleB, 0, 8);
			resultCycle = getXorbyte(xorCycleA, xorCycleB);
			hexString = ISO8583Utile.bytesToHexString(resultCycle);
		} else {// 只有8个字节时是否需要做异或？
			hexString = "";
		}

		return hexString;
	}

	public static byte[] getXorbyte(byte[] xorCycleA, byte[] xorCycleB) {
		byte[] resultCycle = new byte[8];// 结果为8个字节
		for (int i = 0; i < xorCycleA.length; i++) {
			resultCycle[i] = (byte) (xorCycleA[i] ^ xorCycleB[i]);
		}
		return resultCycle;
	}

	/**
	 * 得到字符串中汉字的数量
	 * 
	 * @param str
	 * @return
	 */
	public static int getChinCharNumber(String str) {
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			count += 1;
		}
		return count;
	}

	/**
	 * 根据中行银行返回的54域余额，得到可用余额对应的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getAvailableBalance(String str) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		int num = 0;
		int startindex = 0;
		int endindex = 20;
		String numString = "";
		String reString = "";
		int index = 0;
		if (len > 0 && len % 20 == 0) {
			num = len / 20;
			for (int i = 0; i < num; i++) {
				numString = str.substring(startindex, endindex);
				index = numString.indexOf("02");
				if (index == 2) {
					reString = numString;
					return reString;
				}
				startindex += 20;
				endindex += 20;
			}
		}
		return null;
	}

	/**
	 * 封装POS返回的44域
	 * 
	 * @param issuer
	 *            发卡行8位标识
	 * @param acquirer
	 *            收单行8位标识
	 * @return
	 */
	public static String putIssuerandAcquirer(String issuer, String acquirer) {
		String returnString = "";
		String issuercode = StringFillRightBlank(issuer, 11);
		String acquirercode = StringFillRightBlank(acquirer, 11);
		returnString = issuercode + acquirercode;
		return returnString;
	}


}
