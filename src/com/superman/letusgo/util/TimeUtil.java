package com.superman.letusgo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String TimeStamp2Date(long timestamp, String formats){   
		  String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
		  return date;    
		}
	public static long Date2TimeStamp(String dateString) {
		Date date = null;
		long timeStamp = 0;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString);
			timeStamp = date.getTime();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return timeStamp;
	}
	/* 获取毫秒数 */
	public static long getTimeMillis () {
		return System.currentTimeMillis();
	}
}
