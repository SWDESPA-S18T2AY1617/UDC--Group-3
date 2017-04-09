package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarCalculator {

	public static Calendar getNewCalendar() {
		Calendar c = new GregorianCalendar();
		// c.set(9999, 0, 1, 0, 0, 0);
		c.clear();
		return c;
	}

	public static Calendar getNewCalendar(int year, int month) {
		Calendar c = new GregorianCalendar();
		c.set(year, month, 0, 0, 0, 0);
		return c;
	}

	public static Calendar getNewCalendar(int year, int month, int day) {
		Calendar c = new GregorianCalendar();
		c.set(year, month, day, 0, 0, 0);
		return c;
	}

	public static Calendar getNewCalendar(int year, int month, int day, int hour, int minute) {
		Calendar c = new GregorianCalendar();
		c.set(year, month, day, hour, minute, 0);
		return c;
	}

	public static int getFirstMondayOfMonth(int year, int month) {
		Calendar c = getNewCalendar(year, month);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getFirstMondayOfWeek(int year, int month, int day) {
		Calendar c = getNewCalendar(year, month, day);
		int dayOfWeekInMonth = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// c.set(Calendar.DAY_OF_WEEK_IN_MONTH, dayOfWeekInMonth);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getNoDays(int year, int month) {
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		return cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}

	public static int getDayOfWeek(int year, int month, int day) {
		Calendar c = getNewCalendar(year, month, day);

		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int getStartMonth(int year, int month) {
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}

	public static int getNextMonth(int month) {
		if (month == 11)
			return 0;
		else
			return month + 1;
	}

	public static int getPrevMonth(int month) {
		if (month == 0)
			return 11;
		else
			return month - 1;
	}

	// Collapse to 30 mins interval start calendar
	public static ArrayList<Calendar> getCollapsedCalendar(Calendar start, Calendar end) {
		ArrayList<Calendar> list = new ArrayList<>();
		Calendar bound = (Calendar) start.clone();
		while (bound.before(end)) {
			list.add((Calendar) bound.clone());
			bound.set(Calendar.MINUTE, bound.get(Calendar.MINUTE) + 30);
		}

		return list;
	}
}