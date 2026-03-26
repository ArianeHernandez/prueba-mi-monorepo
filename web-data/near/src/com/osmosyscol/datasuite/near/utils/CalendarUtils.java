package com.osmosyscol.datasuite.near.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
	public static Calendar calendarAddDays(Calendar cal, int nrOfDays) {
		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.DATE, nrOfDays);
		// TODO Auto-generated method stub
		return c;
	}


	public static Calendar dateAsCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

}
