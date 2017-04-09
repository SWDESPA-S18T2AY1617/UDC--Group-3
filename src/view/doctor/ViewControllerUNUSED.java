package view.doctor;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


import model.CalendarPointers;

public class ViewControllerUNUSED {

	private CalendarPointers cp;
	
	private FrameMain f;
	private PanelCalendar pc;
	private PanelMenu pm;
	private PanelCreate pcr;
	private PanelWeek pw;
	private PanelDay pd;
	private PanelAgenda pa;
	//private PanelModifyDelete pmd;

	public static final int PANEL_CAL_WEEK = 1;
	public static final int PANEL_CAL_DAY = 2;
	public static final int PANEL_AGENDA_DAY = 3;
	public static final int PANEL_AGENDA_WEEK = 4;
	public static final int PANEL_CREATE = 5;

	// public ViewController(Controller controller, int year, int month, int
	// day) {
	public ViewControllerUNUSED(int year, int month, int day) {
		// TODO Auto-generated constructor stub
		// this.controller = controller;
		cp = new CalendarPointers();
		
		f = new FrameMain();
		pc = new PanelCalendar(this, cp.getYearBound(), cp.getMonthBound(), cp.getDayBound());
		// pc = new PanelCalendar(controller, year, month, day);
		pm = new PanelMenu(this, cp.getYearBound(), cp.getMonthBound(), cp.getDayBound());
		// pm = new PanelMenu(controller, year, month, day);
		pcr = new PanelCreate(this, cp.getYearBound(), cp.getMonthBound(), cp.getDayBound());
		// pcr = new PanelCreate(controller, year, month, day);
		pa = new PanelAgenda(this, cp.getYearBound(), cp.getMonthBound(), cp.getDayBound());
		// pa = new PanelAgenda(controller);
		pw = new PanelWeek(cp.getYearBound(), cp.getMonthBound(), cp.getDayBound());
		
		pd = new PanelDay();
		//pmd = new PanelModifyDelete(year, month, day);
		
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pw.getPanelTable());
		
		// TEST
		this.setAppointments(year, month, day);
		//JOptionPane.showConfirmDialog(null, pmd, "Modify appointment slot", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
		case ViewController.PANEL_CAL_WEEK:
			f.setRightPanel(pw.getPanelTable());
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_CAL_DAY:
			f.setRightPanel(pd.getPanelTable());
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_AGENDA_DAY:
			pa.update(cp.getYearCurr(), cp.getMonthCurr(), cp.getDayCurr()); // for the actual app, get values from calPointers
			f.setRightPanel(pa);
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_AGENDA_WEEK:
			pa.updateWeek(cp.getYearCurr(), cp.getMonthCurr(), cp.getDayCurr()); // for the actual app, get values from calPointers
			f.setRightPanel(pa);
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_CREATE:
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
		start = new GregorianCalendar(2017, 10, 5);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 5);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 6);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		t.setTaken(true);
		listA.add(t);

		// NOVEMBER 29 - 4:30 - 9:00
		start = new GregorianCalendar(2017, 10, 29);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 4);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 9);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		listA.add(t);

		// NOVEMBER 29 - 10:30 - 12:00
		start = new GregorianCalendar(2017, 10, 29);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 10);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 12);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);
		//t.setTaken(true);
		listA.add(t);

		// NOVEMBER 30 - 15:00 - 19:00
		start = new GregorianCalendar(2017, 10, 30);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 15);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 19);
		end.set(Calendar.MINUTE, 0);

		t = new TestAppointment("appointment", start, end);

		listA.add(t);

		// DECEMBER 1 - 00:30 - 5:00
		start = new GregorianCalendar(2017, 11, 1);
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
}
