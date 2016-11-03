package com.aps.monitor.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {
	/**
	 * Format string: <b>yyyy-MM-dd HH:mm:ss</b>
	 */
	public final static String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Format string: <b>yyyyMMddHHmmss</b>
	 */
	public final static String FORMAT2 = "yyyyMMddHHmmss";
	/**
	 * Format like: <b>yyyy-MM-dd HH:mm:ss</b>
	 */
	public final static SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat(FORMAT1);
	/**
	 * Format like: <b>yyyyMMddHHmmss</b>
	 */
	public final static SimpleDateFormat SIMPLE_DATE_FORMAT2 = new SimpleDateFormat(FORMAT2);
	private static final Logger LOG = LogManager.getLogger(DateUtil.class);

	private DateUtil() {

	}

	/**
	 * 
	 * @Title: formatString
	 * @Description: TODO
	 * @param: @param
	 *             date
	 * @param: @return
	 * @return: String
	 * @throws @since
	 *             1.0.0
	 */
	public static String formatString(Date date, SimpleDateFormat simpleDateFormat) {
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @Title: fromString
	 * @Description: TODO
	 * @param dateStr
	 * @return Date
	 * @throws:
	 * @since 1.0.0
	 */
	public static Date fromString(String dateStr, SimpleDateFormat simpleDateFormat) {
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			LOG.error(e);
		}

		return date;
	}
}
