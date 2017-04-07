package model;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarPointers {
	
	private Calendar current;
	private final Calendar bound;

	public CalendarPointers() {

		bound = new GregorianCalendar();
		bound.clear(Calendar.SECOND);
		
		current = (Calendar) bound.clone();
	}

	public int getDayCurr() {
		return current.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMonthCurr() {
		return current.get(Calendar.MONTH);
	}

	public int getYearCurr() {
		return current.get(Calendar.YEAR);
	}
	
	public int getDayBound() {
		return bound.get(Calendar.DAY_OF_MONTH);
	}

	public int getMonthBound() {
		return bound.get(Calendar.MONTH);
	}

	public int getYearBound() {
		return bound.get(Calendar.YEAR);
	}
	
	public void setDayCurr(int day) {
		current.set(Calendar.DAY_OF_MONTH, day);
	}

	public void setMonthCurr(int month) {
		current.set(Calendar.MONTH, month);
	}

	public void setYearCurr(int year) {
		current.set(Calendar.YEAR, year);
	}
	
	public void resetDay() {
		current = (Calendar) bound.clone();
	}
	
	public void addMonth() {
		int month = this.getMonthCurr();
		this.setMonthCurr(month + 1);
		if(month + 2 == this.getMonthCurr()){
			this.setDayCurr(1);
			this.setMonthCurr(month + 1);
			this.setDayCurr(CalendarCalculator.getNoDays(this.getYearCurr(), this.getMonthCurr()));
		}
	}

	public void subMonth() {
		int month = this.getMonthCurr();
		this.setMonthCurr(this.getMonthCurr() - 1);
		if(month == this.getMonthCurr()){
			this.setDayCurr(1);
			this.setMonthCurr(month - 1);
			this.setDayCurr(CalendarCalculator.getNoDays(this.getYearCurr(), this.getMonthCurr()));
		}
	}
}