package com.wjk.sprlay.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SprUtil {

	// 日期和时间格式 =========================================================
	/** 完整日期时间格式 */
	public static final String FORMAT_DATETIME_EXT = "yyyy-MM-dd HH:mm:ss.SSS";
	/** 默认日期时间格式 */
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** 默认日期格式 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/** 默认时间格式 */
	public static final String FORMAT_TIME = "HH:mm:ss";



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

}
