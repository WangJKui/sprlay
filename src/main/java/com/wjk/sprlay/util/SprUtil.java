package com.wjk.sprlay.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName  SprUtil   
 * @Description 自定义工具类
 * @author WangJKui
 * @date   2019年3月22日 下午2:47:13   
 */
public class SprUtil {
	//
	private SprUtil() {
	}
	
	// 日期和时间格式 =========================================================
	/** 完整日期时间格式 */
	public static final String FORMAT_DATETIME_EXT = "yyyy-MM-dd HH:mm:ss.SSS";
	/** 默认日期时间格式 */
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** 默认日期格式 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/** 默认时间格式 */
	public static final String FORMAT_TIME = "HH:mm:ss";

	public static final String algorithmName = "MD5"; //指定散列算法为MD5,还有别的算法如：SHA256、SHA1、SHA512
	public static final int hashIterations = 2; //散列迭代次数 md5(md5(pwd)): new Md5Hash(pwd, salt, 2).toString()

	/** 默认字符集 */
	public static final String CHARSET_DEFAULT = "UTF-8";
	public static final String CHARSET_UTF8    = "UTF-8";

	/** HTTP GET请求 */
	public static final String HTTP_GET = "GET";
	/** HTTP POST请求 */
	public static final String HTTP_POST = "POST";

	/** cookie中的JSESSIONID名称 */
	public static final String JSESSIONID_COOKIE = "JSESSIONID";
	/** url中的jsessionid名称 */
	public static final String JSESSIONID_URL = "jsessionid";

	private static Logger logger = LoggerFactory.getLogger(SprUtil.class); 

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	public static Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间的默认日期格式的字符串值
	 * <p>
	 * 默认日期格式由{@link WafConstants#FORMAT_DATE}指定
	 * 
	 * @return 字符串值
	 */
	public static String getDateString() {
		return getDateString(new Date(System.currentTimeMillis()), FORMAT_DATE);
	}

	/**
	 * 获取指定时间的默认日期格式的字符串值
	 * <p>
	 * 默认日期格式由{@link WafConstants#FORMAT_DATE}指定
	 * 
	 * @param date
	 *            指定时间
	 * @return 字符串值
	 */
	public static String getDateString(Date date) {
		return getDateString(date, FORMAT_DATE);
	}

	/**
	 * 获取指定时间的指定日期格式的字符串值
	 * 
	 * @param date
	 *            指定时间
	 * @param format
	 *            指定格式
	 * @return 字符串值
	 */
	public static String getDateString(Date date, String format) {
		if (format == null) {
			format = FORMAT_DATE;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间的指定日期格式的字符串值
	 * 
	 * @param format
	 *            日期格式
	 * @return 字符串值
	 */
	public static String getDateString(String format) {
		return getDateString(new Date(System.currentTimeMillis()), format);
	}

	/**
	 * 获取当前日期时间的默认日期时间格式的字符串值。
	 * <p>
	 * 默认日期时间格式由{@link WafConstants#FORMAT_DATETIME}指定
	 * 
	 * @return 字符串值
	 */
	public static String getDateTimeString() {
		return getDateTimeString(new Date(System.currentTimeMillis()), FORMAT_DATETIME);
	}

	/**
	 * 获取指定时间的默认日期时间格式的字符串值
	 * <p>
	 * 默认日期时间格式由{@link WafConstants#FORMAT_DATETIME}指定
	 * 
	 * @param date
	 *            指定时间
	 * @return 字符串值
	 */
	public static String getDateTimeString(Date date) {
		return getDateTimeString(date, FORMAT_DATETIME);
	}

	/**
	 * 获取指定时间的指定日期时间格式的字符串值
	 * 
	 * @param date
	 *            指定时间
	 * @param format
	 *            指定格式
	 * @return 字符串值
	 */
	public static String getDateTimeString(Date date, String format) {
		if (format == null) {
			format = FORMAT_DATETIME;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间的指定日期时间格式的字符串值
	 * 
	 * @param format
	 *            日期时间格式
	 * @return 字符串值
	 */
	public static String getDateTimeString(String format) {
		return getDateTimeString(new Date(System.currentTimeMillis()), format);
	}

	/**
	 * 获取当前日期是周几
	 * 
	 * @return 周几
	 */
	public static String getWeek() {
		return getDateTimeString("EE");
	}
	
	/**
	 * 
	 * @Title: encodePassword   
	 * @Description: 加密：输入明文得到密文   
	 * @param: @param pwd	明文
	 * @param: @param salt	盐
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String encodePassword(String pwd, String salt) {

		String newPassword = new SimpleHash(algorithmName,pwd,ByteSource.Util.bytes(salt),hashIterations).toHex();

		return newPassword;
	}

	/**
	 * 
	 * @Title: verifyPassword   
	 * @Description: 验证密码
	 * @param: @param targetPassword
	 * @param: @param pwd
	 * @param: @param salt
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean verifyPassword(String targetPassword, String pwd, String salt){
		String newPassword = encodePassword(targetPassword, salt);
		if(newPassword.equals(pwd)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: getUUID32   
	 * @Description: 获取一个32位的UUID值
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 判断list参数是否为空
	 * 
	 * @param list
	 *            list
	 * @return 当list为null或长度为0时返回true，否则返回false
	 */
	public static <T> boolean isEmpty(Collection<T> list) {
		return (list == null || list.size() <= 0);
	}

	/**
	 * 判断map参数是否为空
	 * 
	 * @param map
	 *            map
	 * @return 当map为null或长度为0时返回true，否则返回false
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return (map == null || map.size() <= 0);
	}

	/**
	 * 判断字符串参数是否为空，默认经过trim操作后进行判断
	 * 
	 * @param str
	 *            字符串
	 * @return 当字符串为null或""时返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		return isEmpty(str, true);
	}

	/**
	 * 判断字符串参数是否为空
	 * 
	 * @param str
	 *            字符串
	 * @param afterTrim
	 *            是否经过trim操作之后再判断
	 * @return 当字符串为null或""时返回true，否则返回false
	 */
	public static boolean isEmpty(String str, boolean afterTrim) {

		if (str == null) {
			return true;
		}

		String t = (afterTrim ? str.trim() : str);

		return (t.length() <= 0);
	}

	/**
	 * 判断数组参数是否为空
	 * 
	 * @param array 数组对象
	 * @return 当数组为null或长度小于等于0时返回true，否则返回false
	 */
	public static <T> boolean isEmpty(T[] array) {		
		return (array == null || array.length <= 0);
	}
	
	/**
	 * 判断字符串参数是否代表true
	 * 
	 * @param str
	 *            字符串
	 * @return 当字符串（不区分大小写）为"1", "y", "yes", "true"时返回true，否则返回false
	 */
	public static boolean isTrue(String str) {
		if ("1".equals(str) || "y".equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 把列表集合拼接成一个以逗号分割的字符串
	 * <p>
	 * 1. 以逗号分割
	 * 2. 忽略空值、空字符串
	 * @param list
	 * 		  对象集合(对象必须重写了toString方法)
	 * @return
	 */
	public static String join(List<? extends Object> list) {
		return join(list, ",", false);
	}

	/**
	 * 把列表集合拼接成一个以逗号分割的字符串
	 * @param list
	 *        对象集合(对象必须重写了toString方法)
	 * @param omitNullAndEmpty
	 *        是否忽略空值、空字符串
	 * @return
	 */
	public static String join(List<? extends Object> list, boolean omitNullAndEmpty) {
		return join(list, ",", omitNullAndEmpty);
	}

	/**
	 * 把列表集合拼接成一个指定分隔符分割的字符串
	 * 默认忽略空值、空字符串
	 * @param list
	 *         对象集合(对象必须重写了toString方法)
	 * @param delimitor
	 *        分割符
	 * @return
	 */
	public static String join(List<? extends Object> list, String delimitor) {
		return join(list, delimitor, false);
	}

	/**
	 * 把列表集合拼接成一个指定分隔符分割的字符串
	 * @param list
	 * 		  对象集合(对象必须重写了toString方法)
	 * @param delimitor
	 *        分割符
	 * @param omitNullAndEmpty
	 *        是否忽略空值、空字符串
	 * @return
	 */
	public static String join(List<? extends Object> list, String delimitor, boolean omitNullAndEmpty) {
		StringBuilder sb = new StringBuilder();

		if (list == null)
			return "";

		for (Object obj : list) {
			if ((obj == null || obj.toString().length() == 0) && omitNullAndEmpty) {
				continue;
			}

			if (sb.length() > 0) {
				sb.append(delimitor);
			}

			if (obj != null) {
				sb.append(obj.toString());
			} else {
				sb.append("NULL");
			}
		}

		return sb.toString();
	}

	
	/**
	 * 把字符串解析成一个byte值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的byte值。如果src不是一个byte值，则返回指定的缺省值。
	 */
	public static byte parseByte(String src, byte def) {
		byte temp = 0;

		try {
			temp = Byte.parseByte(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}

	/**
	 * 把字符串解析成一个double值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的double值。如果src不是一个double值，则返回指定的缺省值。
	 */
	public static double parseDouble(String src, double def) {
		double temp = 0;

		try {
			temp = Double.parseDouble(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}

	/**
	 * 把字符串解析成一个float值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的float值。如果src不是一个float值，则返回指定的缺省值。
	 */
	public static float parseFloat(String src, float def) {
		float temp = 0;

		try {
			temp = Float.parseFloat(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}

	/**
	 * 把字符串解析成一个int值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的int值。如果src不是一个int值，则返回指定的缺省值。
	 */
	public static int parseInt(String src, int def) {
		int temp = 0;

		try {
			temp = Integer.parseInt(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}

	/**
	 * 把字符串解析成一个long值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的long值。如果src不是一个long值，则返回指定的缺省值。
	 */
	public static long parseLong(String src, long def) {
		long temp = 0;

		try {
			temp = Long.parseLong(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}
	
	/**
	 * 把字符串解析成一个short值
	 * 
	 * @param src
	 *            待解析的字符串
	 * @param def
	 *            缺省值
	 * @return 解析的short值。如果src不是一个short值，则返回指定的缺省值。
	 */
	public static short parseShort(String src, short def) {
		short temp = 0;

		try {
			temp = Short.parseShort(src);

		} catch (Exception e) {
			temp = def;
		}

		return temp;
	}
	/**
	 * 用指定的分隔符把字符串分割成一个数组
	 * 
	 * @param clz
	 *            数组类型
	 * @param toParse
	 *            待分割的字符串
	 * @param delimitor
	 *            分隔符
	 * @return 数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] parseToArray(Class<T> clz, String toParse, String delimitor) {
		if (toParse == null || toParse.length() == 0) {
			return null;
		}

		return (T[]) toParse.split(delimitor);
	}

	public static String[] parseToArray(String toParse, String delimitor) {
		String[] a = parseToArray(String.class, toParse, delimitor);

		return a;
	}

	/**
	 * 把日期格式字符串转换成日期
	 * 
	 * @param dateString
	 *            字符串
	 * @return 日期
	 */
	public static Date parseToDate(String dateString) {
		return parseToDate(dateString, FORMAT_DATETIME);
	}

	/**
	 * 把日期格式字符串转换成日期
	 * 
	 * @param dateString
	 *            字符串
	 * @param format
	 *            格式
	 * @return 日期
	 */
	public static Date parseToDate(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用指定的分隔符把字符串分割成一个列表
	 * 
	 * @param toParse
	 *            待分割的字符串
	 * @param delimitor
	 *            分隔符
	 * @return 列表
	 */
	public static <T> List<T> parseToList(String toParse, String delimitor) {
		return parseToList(toParse, delimitor, true);
	}

	/**
	 * 用指定的分隔符把字符串分割成一个列表
	 * 
	 * @param toParse
	 *            待分割的字符串
	 * @param delimitor
	 *            分隔符
	 * @param trim
	 *            是否清楚字符串两边的空格
	 * @return 列表
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> parseToList(String toParse, String delimitor, boolean trim) {
		if (toParse == null) {
			return null;
		}

		List<T> list = new ArrayList<T>();
		if (toParse.length() == 0) {
			return list;
		}

		String[] arr = toParse.split(delimitor);
		for (String str : arr) {
			list.add((T) str);
		}

		return list;
	}

	/**
	 * 获取字符串中按指定分割符分割后的字符串列表，不包含最后一位
	 * 
	 * @param rule
	 *            “-aaa[-bbb-ccc-dddd]-”形式的规则码
	 * @param dec
     *         分割符
	 * @return 分割后的字符串列表
	 */
	public static List<String> splitRule(String rule,String dec) {
		return splitRule(rule, dec, false);
	}

	/**
	 * 获取字符串中按指定分割符分割后的字符串列表
	 * 
	 * @param rule
	 *            “-aaa[-bbb-ccc-dddd]-”形式的规则码
     * @param dec
     *         分割符
	 * @param includeLast
	 *            是否包括最后一个ID
	 * @return 分割后的字符串列表
	 */
	public static List<String> splitRule(String rule, String dec, boolean includeLast) {

		List<String> result = new ArrayList<String>();

		if (!SprUtil.isEmpty(rule) && !rule.equals(dec)) {
			// 规则码不是空
			// 并且不是只有分割线

			// 去掉头尾的"-"后，按"-"拆分成数组
			String[] t = rule.substring(1, rule.length() - 1).split(dec);
			
			if (includeLast) {
				// 如果包括自己（最后一个）
				result = Arrays.asList(t);
			} else {
				// 如果不包括自己（最后一个）
				result = Arrays.asList(t).subList(0, t.length - 1);
			}
		}
		List <String> ids = new ArrayList<String>(result);  
		return ids;
	}

	/**
	 * 把对象转换成JSON格式字符串
	 * <p>
	 * 对象中的时间采用缺省的时间格式进行转换。
	 * 
	 * @param obj
	 *            待转换的对象
	 * @return JSON格式字符串
	 */
	public static String toJsonString(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, FORMAT_DATETIME);
	}

	/**
	 * 把对象转换成JSON格式字符串
	 * <p>
	 * 对象中的时间采用指定的时间格式进行转换。
	 * 
	 * @param obj
	 *            待转换的对象
	 * @param dateFormat
	 *            时间格式
	 * @return JSON格式字符串
	 */
	public static String toJsonString(Object obj, String dateFormat) {
		return JSON.toJSONStringWithDateFormat(obj, dateFormat);
	}
	/**
	 * 把参数列表转换成SQL查询子句中的IN语句
	 * 
	 * @param vals
	 *            参数列表
	 * @return IN语句
	 */
	public static <T> String toWhereIn(Collection<T> vals) {
		if (vals == null || vals.size() == 0) {
			return null;
		}

		String str = "";

		for (int i = 0; i < vals.size(); i++) {
			Object[] arr = vals.toArray();
			Object val = arr[i];

			if (i > 0) {
				str += ",";
			}

			if (val instanceof String) {
				str += "'" + (String) val + "'";
			} else if (val instanceof Integer) {
				str += ((Integer) val).intValue();
			} else if (val instanceof Float) {
				str += ((Float) val).floatValue();
			} else if (val instanceof Double) {
				str += ((Double) val).doubleValue();
			} else if (val instanceof Long) {
				str += ((Long) val).longValue();
			} else if (val instanceof Short) {
				str += ((Short) val).shortValue();
			} else if (val instanceof Byte) {
				str += ((Byte) val).byteValue();
			} else {
				str += "'" + (String) val + "'";
			}
		}

		return str;
	}

	
	/**
	 * 把字符串的首字母变成大写
	 * 
	 * @param src
	 *            字符串
	 * @return 首字母变成大写后的字符串
	 */
	public static String wordCap(String src) {
		if (src == null) {
			return src;
		} else if (src.length() == 1) {
			return src.toUpperCase();
		} else {
			return src.substring(0, 1).toUpperCase() + src.substring(1);
		}
	}
	
	/**
	 * 输出JSON格式数据。
	 * <p>
	 * 把任意类型的数据对象转换成JSON格式字符串后，通过HTTP响应对象输出到请求端
	 * （响应内容的类型设置为"text/html;charset=utf-8"）
	 * 
	 * @param response
	 *            HTTP响应对象
	 * @param obj
	 *            待输出的数据对象
	 */
	public static void writeJson(HttpServletResponse response, Object obj) {
		
		String json = JSON.toJSONStringWithDateFormat(obj, FORMAT_DATETIME);
		
		if (logger.isInfoEnabled()) {
			logger.info("response data was wrote: \r\n{}", JSON.toJSONString(obj, true));
		}

		response.setContentType("text/html;charset=" + CHARSET_DEFAULT);
		
		try (PrintWriter writer = response.getWriter()) {

			writer.write(json);
			
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	//===================================================
	public static void main(String[] args) {
		System.out.println(encodePassword("123456", "admin"+"1"));
		
		System.out.println(SprUtil.getWeek());
		
		System.out.println(SprUtil.getDateString());
		
		System.out.println(SprUtil.getDateTimeString());
		
		System.out.println(SprUtil.getCurrentTime());

		System.out.println(SprUtil.getUUID32());
		
		logger.info("response data was wrote: \r\n{}", "wjk");
	}
}
