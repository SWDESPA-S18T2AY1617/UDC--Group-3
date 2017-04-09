package controller;

import model.CalendarPointers;

public abstract class ViewController {
	
	protected MainController controller;
	protected CalendarPointers calendar;

	public ViewController(MainController controller)
	{
		this.controller = controller;
		calendar = new CalendarPointers();
	}
	
	public abstract void updateView();
	
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	//public Iterator<Appointment> checkAppointments(ArrayList<String> filter);
	
	public abstract void setAppointment();
	
	public abstract void cancelAppointment();
	
	//these methods will be called by the views
	public int getDayCurr() {
		return calendar.getDayCurr();
	}
	
	public int getMonthCurr() {
		return calendar.getMonthCurr();
	}

	public int getYearCurr() {
		return calendar.getYearCurr();
	}
	
	public int getDayBound() {
		return calendar.getDayBound();
	}

	public int getMonthBound() {
		return calendar.getMonthBound();
	}

	public int getYearBound() {
		return calendar.getYearBound();
	}
	
	public void setDayCurr(int day) {
		calendar.setDayCurr(day);
		//this.updateView();
	}

	public void setMonthCurr(int month) {
		calendar.setMonthCurr(month);
	}

	public void setYearCurr(int year) {
		calendar.setYearCurr(year);
	}
	
	public void resetDay() {
		calendar.resetDay();
	}
	
	public void addMonth() {
		calendar.addMonth();
	}

	public void subMonth() {
		calendar.subMonth();
	}
}
