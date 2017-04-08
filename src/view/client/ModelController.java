package view.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import model.CalendarModel;
import model.CalendarPointers;

public class ModelController {
	private CalendarModel calendar;
	private CalendarPointers calPointers;
	//private FileService fileService;
	
	public ModelController() {
		calendar = new CalendarModel();
		calPointers = new CalendarPointers();
		//fileService = new FileService();
	}
	/*
	public void addActivity(Activity activity) {
		calendar.addActivity(activity);
	}

	public void addActivities(ArrayList<Activity> activities) {
		calendar.addActivities(activities);
	}
	
	public void deleteActivity(int id) {
		calendar.deleteActivity(id);
	}
	
	public void markDone(int id) {
		calendar.markDone(id);
	}

	public Iterator<Activity> getActivities() {
		return calendar.getActivities();
	}
	
	public Iterator<Activity> getActivities(int filter) {
		return calendar.getActivities(filter);
	}
	
	public Iterator<Activity> getActivities(ArrayList<Integer> filter, int year, int month) {
		return calendar.getActivities(filter, year, month);
	}
	
	public Iterator<Activity> getActivities(ArrayList<Integer> filter, int year, int month, int day) {
		return calendar.getActivities(filter, year, month, day);
	}
	
	public Iterator<Activity> getActivities(int year, int month) {
		return calendar.getActivities(year, month);
	}
	
	public Iterator<Activity> getTasks(int year, int month) {
		return calendar.getTasks(year, month);
	}
	
	public Iterator<Activity> getEvents(int year, int month) {
		return calendar.getEvents(year, month);
	}

	public int getToDoLeft(int year, int month, int day) {
		return calendar.getToDoLeft(year, month, day);
	}
*/	
	public int getDayCurr() {
		return calPointers.getDayCurr();
	}
	
	public int getMonthCurr() {
		return calPointers.getMonthCurr();
	}

	public int getYearCurr() {
		return calPointers.getYearCurr();
	}
	
	public int getDayBound() {
		return calPointers.getDayBound();
	}

	public int getMonthBound() {
		return calPointers.getMonthBound();
	}

	public int getYearBound() {
		return calPointers.getYearBound();
	}
	
	public void setDayCurr(int day) {
		calPointers.setDayCurr(day);
	}

	public void setMonthCurr(int month) {
		calPointers.setMonthCurr(month);
	}

	public void setYearCurr(int year) {
		calPointers.setYearCurr(year);
	}
	
	public void addMonth() {
		calPointers.addMonth();
	}
	
	public void subMonth() {
		calPointers.subMonth();
	}
	
	public void resetDay() {
		calPointers.resetDay();
	}
	/*
	public boolean isActivityValid(Activity activity) {
		return calendar.isActivityValid(activity);
	}*/
}
