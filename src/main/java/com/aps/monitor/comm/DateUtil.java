package com.aps.monitor.comm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @Title: formatString
	 * @Description: TODO
	 * @param: @param date
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static String formatString(Date date) {
		return simpleDateFormat.format(date);
	}
}
