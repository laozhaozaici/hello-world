package com.lanou.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	/**
	 * 
	 * "2017-01-01 12:00:00"
	 * @param d
	 * @return
	 */
	public static  int getYear(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.YEAR);
	}
	
	
	public static int getMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.MONTH)+1;
	}
	
	
}
