package view.client;

import java.util.ArrayList;
import java.util.Iterator;

import model.CalendarCalculator;

public class Controller {
	private ClientViewController view;
	private ModelController model;
	/*
	private CalendarModel calendar;
	private CalendarPointers model;
	private Converter converter;
	*/
	
	public Controller() {
		model = new ModelController();
		view = new ClientViewController(this, model.getYearCurr(), model.getMonthCurr(), model.getDayCurr());
		
		//view.updateAll(model.getYearCurr(), model.getMonthCurr(), model.getDayCurr(), model.getActivities(this.getFilterMode(), model.getYearCurr(), model.getMonthCurr(), model.getDayCurr()));
	}
	
	public void updateView() {
		view.updateAll(model.getYearCurr(), model.getMonthCurr(), model.getDayCurr());
	}
	/*
	public void addActivity(Activity activity) {
		model.addActivity(activity);
		int filter;
		if(activity instanceof Event) {
			filter = Activity.EVENT;
			FileService.saveEvents(getActivities(filter));
		}
		else if(activity instanceof ToDo) {
			filter = Activity.TASK;
			FileService.saveToDo(getActivities(filter));
		}
		this.updateView();
	}

	public void deleteActivity(int id) {
		model.deleteActivity(id);
		FileService.saveToDo(getActivities(Activity.TASK));
	}
	
	public void markDone(int id) {
		model.markDone(id);
		FileService.saveToDo(getActivities(Activity.TASK));
	}
	
	public boolean isActivityValid(Activity activity) {
		return model.isActivityValid(activity);
	}
*/
	public void setYearToday(int year) {
		model.setYearCurr(year);
		this.updateView();
		//view.updateCalendar(model.getYearCurr(), model.getMonthCurr(), model.getActivities(model.getYearCurr(), model.getMonthCurr()));
	}
	
	public void setDayToday(int day) {
		model.setDayCurr(day);
		this.updateView();
	}
	
	public void resetDay() {
		model.resetDay();
		this.updateView();
	}
	
	public ArrayList<Integer> getFilterMode() {
		ArrayList<Integer> filter = new ArrayList<>();
		/*if(!view.isEventFiltered()) {
			filter.add(Activity.EVENT);
		}
		if(!view.isTaskFiltered()) {
			filter.add(Activity.TASK);
		}
		return filter;
		*/
		return filter;
		
	}
/*	
	public Iterator<Activity> getActivities(ArrayList<Integer> filter, int year, int month) {
		return model.getActivities(filter, year, month);
	} 

	public Iterator<Activity> getActivities(int filter) {
		return model.getActivities(filter);
	}
*/	
	public void addMonth() {
		model.addMonth();
		this.updateView();
	}

	public void subMonth() {
		model.subMonth();
		this.updateView();
	}
	
	public void jumpYear(int year) {
		model.setYearCurr(year);
	}
	
	public void setMainPanel(int panelConstant) {
		view.setMainPanel(panelConstant);
	}
}
