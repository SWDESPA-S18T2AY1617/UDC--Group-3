package view.doctor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ViewController {

	private FrameMain f;
	private PanelCalendar pc;
	private PanelMenu pm;
	private PanelCreate pcr;
	private PanelWeek pw;
	private PanelDay pd;
	private PanelAgenda pa;

	public static final int PANEL_WEEK = 1;
	public static final int PANEL_DAY = 2;
	public static final int PANEL_AGENDA = 3;
	public static final int PANEL_CREATE = 4;

	// public ViewController(Controller controller, int year, int month, int
	// day) {
	public ViewController(int year, int month, int day) {
		// TODO Auto-generated constructor stub
		// this.controller = controller;

		f = new FrameMain();
		pc = new PanelCalendar(this, year, month, day);
		// pc = new PanelCalendar(controller, year, month, day);
		pm = new PanelMenu(this, year, month, day);
		// pm = new PanelMenu(controller, year, month, day);
		pcr = new PanelCreate(this, year, month, day);
		// pcr = new PanelCreate(controller, year, month, day);
		pa = new PanelAgenda(this, year, month, day);
		// pa = new PanelAgenda(controller);
		pw = new PanelWeek(year, month, day);
		pd = new PanelDay();

		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pw.getPanelTable());

		// TEST
		this.setAppointments(year, month, day);
	}
	/*
	 * public void upwateAll(int year, int month, int day, Iterator<Activity>
	 * activity) { ArrayList<Activity> act = new ArrayList<>(); while(activity
	 * != null && activity.hasNext()) act.add(activity.next()); pc.upwate(month,
	 * year, day); pa.upwate(month, day, year, act.iterator()); pm.upwate(month,
	 * day, year); pw.upwate(month, day, year, act.iterator()); }
	 */

	public void setMainPanel(int panelConstant) {
		switch (panelConstant) {
		case ViewController.PANEL_WEEK:
			f.setRightPanel(pw.getPanelTable());
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_DAY:
			f.setRightPanel(pd.getPanelTable());
			pc.enableBtnCreate();
			break;
		case ViewController.PANEL_AGENDA:
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

		// NOVEMBER 30 - 15:00 - 19:00
		Calendar start = new GregorianCalendar(2017, 10, 30);
		start.set(Calendar.SECOND, 0);
		Calendar end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 15);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 19);
		end.set(Calendar.MINUTE, 0);

		TestAppointment t = new TestAppointment("appointment", start, end);

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
		t.setTaken(true);
		listA.add(t);

		System.out.println("iterator size " + listA.size());
		pw.update(month, day, year, listA.iterator());
		pd.update(month, day, year, listA.iterator());
		pa.update(year, month, day, listA.iterator());
		pa.updateWeek(year, month, day, listA.iterator());
	}
}
