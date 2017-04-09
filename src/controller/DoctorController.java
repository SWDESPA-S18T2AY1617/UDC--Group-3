package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import view.doctor.*;

import model.CalendarPointers;

public class DoctorController extends ViewController {
	
	private FrameMain f;
	private PanelCalendar pc;
	private PanelMenu pm;
	private PanelCreate pcr;
	private PanelWeek pw;
	private PanelDay pd;
	private PanelAgenda pa;

	public static final int PANEL_CAL_WEEK = 1;
	public static final int PANEL_CAL_DAY = 2;
	public static final int PANEL_AGENDA_DAY = 3;
	public static final int PANEL_AGENDA_WEEK = 4;
	public static final int PANEL_CREATE = 5;

	public DoctorController(MainController controller) {
		super(controller);
		
		f = new FrameMain();
		pc = new PanelCalendar(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pm = new PanelMenu(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pcr = new PanelCreate(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pa = new PanelAgenda(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pw = new PanelWeek(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pd = new PanelDay(this);
		
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pw.getPanelTable());
		
		// TEST
		this.setAppointments(super.getYearBound(), super.getMonthBound(), super.getDayBound());
	}

	/**
	 * Used when jumping to a different date / creation or modification of appointments so panels are all up-to-date.
	 *  
	 * @param year
	 * @param month
	 * @param day		
	 * @param activity 	list of appointments during the week / list of all appointments 
	 */
	public void updateAll(int year, int month, int day, Iterator<TestAppointment> activity) {
		ArrayList<TestAppointment> act = new ArrayList<>();
		while (activity != null && activity.hasNext())
			act.add(activity.next());
		pc.update(month, year, day);
		pa.update(month, day, year, act.iterator());
		pm.update(month, day, year);
		pw.update(month, day, year, act.iterator());
	}

	/**
	 * Used for switching panels without changing dates.
	 * @param panelConstant
	 */
	public void setMainPanel(int panelConstant) {
		switch (panelConstant) {
		case PANEL_CAL_WEEK:
			f.setRightPanel(pw.getPanelTable());
			pc.enableBtnCreate();
			break;
		case PANEL_CAL_DAY:
			f.setRightPanel(pd.getPanelTable());
			pc.enableBtnCreate();
			break;
		case PANEL_AGENDA_DAY:
			pa.update(super.getYearCurr(), super.getMonthCurr(), super.getDayCurr()); // for the actual app, get values from calPointers
			f.setRightPanel(pa);
			pc.enableBtnCreate();
			break;
		case PANEL_AGENDA_WEEK:
			pa.updateWeek(super.getYearCurr(), super.getMonthCurr(), super.getDayCurr()); // for the actual app, get values from calPointers
			f.setRightPanel(pa);
			pc.enableBtnCreate();
			break;
		case PANEL_CREATE:
			f.setRightPanel(pcr);
			pm.unselectToggleBtns();
			break;
		}
	}

	public boolean isTaskFiltered() {
		return pc.isTaskSelected();
	}

	public boolean isEventFiltered() {
		return pc.isEventSelected();
	}

	public void showAvailable(boolean show) {

		pw.showAvailable(show);
	}

	public void showUnavailable(boolean show) {
		pw.showUnavailable(show);
	}

	// testing
	private void setAppointments(int year, int month, int day) {
		List listA = new ArrayList<TestAppointment>();
		Calendar start;
		Calendar end;
		TestAppointment t;

		// NOVEMBER 5 - 05:00 - 6:00
		start = new GregorianCalendar(year, month, day + 5);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 5);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 6);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		t.setTaken(true);
		listA.add(t);


		start = new GregorianCalendar(year, month, day + 2);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 4);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 9);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		listA.add(t);

		start = new GregorianCalendar(year, month, day + 4);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 10);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 12);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		//t.setTaken(true);
		listA.add(t);

		
		start = new GregorianCalendar(year, month, day + 3);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 15);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 19);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);

		listA.add(t);

		
		start = new GregorianCalendar(year, month, 10);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 00);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 5);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		t.setTaken(true);
		listA.add(t);

		System.out.println("iterator size " + listA.size());
		pw.update(month, day, year, listA.iterator());
		pd.update(month, day, year, listA.iterator());
		pa.update(year, month, day, listA.iterator());
		pa.updateWeek(year, month, day, listA.iterator());
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAppointment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAppointment() {
		// TODO Auto-generated method stub
		
	}
}

/*
public class DoctorController extends ViewController {
	
	//private DoctorView view;
	
	public DoctorController(MainController controller)
	{
		super(controller);
		
		//remember to have an instance of their specific ViewControllers to their views
		//view = new DoctorView(this);
	}
	
	public void updateView() {
		
	}
	/*
	//check all appointment slots of this doctor
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check filled appointment slots for day
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check filled appointment slots for week
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check all available appointment slots of this doctor
	public void Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//set 1 appointment slot
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	*/
/*
	//recurring
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	
	public void changeAppointment()
	{
		
		
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		
		
		this.updateView();
	}

}*/
