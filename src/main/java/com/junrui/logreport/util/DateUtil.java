package com.junrui.logreport.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil 
{
	
	public static long getToDayStartTime()
	{
		Calendar today=Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today.getTime().getTime();
	}
	
	public static long getToDayEndTime()
	{
		Calendar today=Calendar.getInstance();
		today.add(Calendar.DATE, 1);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today.getTime().getTime()-1;
	}
	
	
	public static Long getCurrentLongTime()
	{
		return System.currentTimeMillis();
	}
	
	
	public static String getCurrentDayStr()
	{
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String getCurrentDayStr(Calendar cal)
	{
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}
	
}
