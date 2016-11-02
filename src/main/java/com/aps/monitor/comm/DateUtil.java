package com.aps.monitor.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger LOG = LogManager.getLogger(DateUtil.class);

	private DateUtil() {

	}

	/**
	 * 
	 * @Title: getSimpleDateFormat
	 * @Description: TODO
	 * @param: @return
	 * @return: SimpleDateFormat
	 * @throws @since
	 *             1.0.0
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
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
	public static String formatString(Date date) {
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
	public static Date fromString(String dateStr) {
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			LOG.error(e);
		}

		return date;
	}
}
