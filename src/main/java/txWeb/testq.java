package txWeb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author txpc
 * @date 2019年11月21日 下午4:30:41
 * <pre>123123
 * 1231212
 * </pre>
 * 
 * 
 * <blockquote>12312
 * 31231212
 * </blockquote>
 */
public class testq {
	private static final Logger log = LoggerFactory.getLogger(testq.class);
	
    private static final String hexString = "0123456789ABCDEF";
   
	private static void test2(int a) throws FileNotFoundException, IOException, ClassNotFoundException{
		User user = new User("123", 23);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C://obj"));
		out.writeObject(user);
		out.close();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C://obj"));
		User use = (User)in.readObject();
		System.out.println(use);
		in.close();
		
	}
	public static void main(String[] args) throws Exception {
//		HashMap<Integer, String> map = new HashMap<>();
//		map.put(1, "1");
//		map.put(2, "2");
//		map.put(4, "4");
//		map.put(5, "5");
//		map.put(3, "3");
//		Set<Entry<Integer, String>> entrySet = map.entrySet();
//		for (Entry<Integer, String> entry : entrySet) {
//			System.out.println(entry.getKey());
//		}
//		test2(map);
//		List<Object> synchronizedList = Collections.synchronizedList(null);
//		
//		Object[] obj = new Object[7];
//		for (int i = 0; i < 6; i++) {
//			obj[i] = i;
//		}
//		System.arraycopy(obj, 2, obj, 2+1, 5);
//		obj[2] = 0;
//		for (Object object : obj) {
//			
//			System.out.println(object);
//		}
//		ArrayList<Integer> list=new ArrayList<>();
//		list.add(0);
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		
//		list.add(2, 0);
//		System.out.println(list.toString());
		int c = 100;
		Integer d = new Integer(100);
		Integer dd = new Integer(100);
		int f = 223;
		Integer gInteger = new Integer(223);
		Integer gIntegerd = new Integer(223);
		System.out.println(c == d);
		System.out.println(d.equals(c));
		System.out.println(gIntegerd == gInteger);
		System.out.println(gIntegerd.equals(gInteger));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		String string = "123";
		String s = new String("123");
		System.out.println(s.equals(string));
		System.out.println(s == string);
		StringBuilder sBuilder = new StringBuilder("123456");
		sBuilder.reverse();
		System.out.println(sBuilder.toString());
		char[] ch = {1};
		string.length();
	}

	@Test
	public void testa(){
			List<String> a = new ArrayList<String>();
					a.add("12345");
					System.out.println(a.contains("123456"));;
					System.out.println(a.contains("12345"));;
        try {
        	LongAdder la = new LongAdder();
        	la.increment();
//        	System.out.println(new String("开关量报警".getBytes("gb2312"),"gb2312"));
//        	System.out.println(encode(new String("开关量报警".getBytes("gb2312"),"gb2312")));
//        	byte[] bytes2 = decode("004B30302020202020202020202020202020202020202020202020202020202020202020202020203338393737303338563230313931323034353020202020202020202030303030333038367A").getBytes("GBK");
			System.out.println(decode("0060DF09083932333337353239DF08083530373330343331DF0F23D508BBB6D3ADB9E2C1D9DE00DF0500DF020130DA083030303030303030DF2200DC0133DF0E023335DF0D0132DF0B023030DF0A023231DF32024C44DF1008303030303030303025"));
//			System.out.println("|"+decode("17615")+"|");
//			for (byte b : bytes2) {
//				System.out.println(b);
//			}
//			byte[] bytes = " K00 ".getBytes("GBK");
//			System.out.println(new String(bytes,"GBK"));
//			for (byte b : bytes) {
//				System.out.println(b);
//			}
		} catch (UnsupportedEncodingException e) {
		}
	}
	
	 /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes("gb2312");
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }
 
    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String (new String(baos.toByteArray(),"GBK").getBytes("GBK"),"GBK");
    }


	@Test
	public void testb() {
		// 响应POS更新报文长度77
//		byte[] returnByte = new byte[77];
//		returnByte = MessageUtil.addHeadByte(returnByte);
//
//		byte[] headLenght = new byte[2];
//		String hexStr = Integer.toHexString(returnByte.length - 2);
//		String lenString = ISO8583Utile.StringFillLeftZero(hexStr, 4);
//		headLenght = ISO8583Utile.string2Bcd(lenString);
//		System.arraycopy(headLenght, 0, returnByte, 0, headLenght.length);
//
//		
		String decode = null;
		try {
			decode = decode("0003303000");
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		byte[] bytes = decode.getBytes();
		System.arraycopy("00".getBytes(), 0, bytes, 2, "00".getBytes().length);

		
		try {
			System.out.println();
			System.out.println(new String(bytes, "GBK"));
			for (byte b : bytes) {
				System.out.print(b + "-");
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}

	@Test
	public void c() {
		try {
			d();
		} catch (Exception e) {
			log.error("ceee:{}", e.getMessage(), e);
		}
	}
	@Test
	public void d() throws Exception {
//		try {
//
//			int i = 0;
//			int j = 1;
//			int m = j/i;
//			System.out.println(m);
//		} catch (Exception e) {
//			throw e;
//		}
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
		
		// 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(3,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
 
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
	}}

	@Test
	public void test1(Integer b, String a){
		
	}

	@Test
	public static int test1() {
		try {
			int a = 1 / 1;
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}

	}
	@Test
	public static HashMap<Integer, String> test2(HashMap<Integer, String> map) {
		LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		List<Entry<Integer, String>> list = new ArrayList<Entry<Integer, String>>(entrySet);
		Collections.sort(list, new Comparator<Entry<Integer, String>>() {

			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				return o2.getKey()-o1.getKey();
			}
		});
		for (Entry<Integer, String> entry : list) {
			lhm.put(entry.getKey(), entry.getValue());
		}
		Set<Entry<Integer, String>> entrySet2 = lhm.entrySet();
		for (Entry<Integer, String> entry : entrySet2) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
		return null;
	}
	@Test
	public static void test2(){
		StringBuilder sBuilder = new StringBuilder("123456");
		sBuilder.reverse();
		System.out.println(sBuilder.toString());
	}
	
	

}
