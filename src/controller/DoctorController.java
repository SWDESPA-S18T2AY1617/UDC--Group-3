package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.doctor.*;
import model.Appointment;
import model.CalendarPointers;
import model.Client;
import model.Doctor;

public class DoctorController extends ViewController {
	
	private Doctor d;
	
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
	}
	
	public DoctorController(MainController controller, Doctor d) {
		super(controller);
		this.d = d;
	}
	
	public void createGUI() {
		System.out.println("gui");
		f = new FrameMain();
		pc = new PanelCalendar(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pm = new PanelMenu(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pcr = new PanelCreate(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pa = new PanelAgenda(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pw = new PanelWeek(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pd = new PanelDay(this);
		
		pc.setDoctorName(d.getName());
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pw.getPanelTable());
		
		// TEST
		this.setAppointments(super.getYearBound(), super.getMonthBound(), super.getDayBound());
		System.out.println("donecreateandshow");
	}

	/**
	 * Used when jumping to a different date / creation or modification of appointments so panels are all up-to-date.
	 *  
	 * @param year
	 * @param month
	 * @param day		
	 * @param activity 	list of appointments during the week / list of all appointments 
	 */
	public void updateAll(Iterator<Appointment> activity) {
		
		pc.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pm.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pa.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);
		pw.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);
		pd.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);

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

	public Doctor getDoctor() {
		return d;
	}
	
	public void setDoctor(Doctor d) {
		this.d = d;
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

	public boolean showDoctorWho() {
		System.out.println("open sesame");
		PanelDoctorWho fdw = new PanelDoctorWho();
		JDialog d1 = new JDialog();
		d1.setLocationRelativeTo(null);
		d1.setSize(new Dimension(400, 180));
		d1.setContentPane(fdw);
		d1.setModal(true);
		d1.setVisible(true);
		System.out.println("close sesame");
		
		if(fdw.getNewDoctor() == 0) {
			System.out.println("old doc");
			try {
				return true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input!");
			}
			return true;
		} else if(fdw.getNewDoctor() == 1) {
			System.out.println("new doc");
			Doctor d = new Doctor(fdw.getNewDocName());
			this.d = d;
			
			return true;
		}
		return false;
	}
	
	// testing
	private void setAppointments(int year, int month, int day) {
		List listA = new ArrayList<Appointment>();
		Calendar start;
		Calendar end;
		Appointment t;

		start = new GregorianCalendar(year, month, 10);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 00);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 5);
		end.set(Calendar.MINUTE, 0);

		t = new Appointment(d, start, end);
		t.setClient(new Client("Alola Raichu"));
		listA.add(t);
		
		// NOVEMBER 5 - 05:00 - 6:00
		start = new GregorianCalendar(year, month, day + 5);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 5);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 6);
		end.set(Calendar.MINUTE, 0);

		t = new Appointment(d, start, end);
		listA.add(t);


		start = new GregorianCalendar(year, month, day + 3);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 15);
		start.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR, 19);
		end.set(Calendar.MINUTE, 0);

		t = new Appointment(new Doctor("Shirley Chu"), start, end);
		
		listA.add(t);

		
		start = new GregorianCalendar(year, month, day + 2);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 4);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 9);
		end.set(Calendar.MINUTE, 0);

		t = new Appointment(d, start, end);
		listA.add(t);

		start = new GregorianCalendar(year, month, day + 4);
		start.set(Calendar.SECOND, 0);
		end = (Calendar) start.clone();
		start.set(Calendar.HOUR, 10);
		start.set(Calendar.MINUTE, 30);
		end.set(Calendar.HOUR, 12);
		end.set(Calendar.MINUTE, 0);

		t = new Appointment(d, start, end);
		//t.setTaken(true);
		listA.add(t);

		System.out.println("iterator size " + listA.size());
		pw.update(month, day, year, listA.iterator());
		pd.update(month, day, year, listA.iterator());
		pa.update(year, month, day, listA.iterator());
		pa.updateWeek(year, month, day, listA.iterator());
	}
	
	public void showGUI() {
		f.setVisible(true);
	}
	
	public void deleteAppointment(Calendar start) {
		controller.deleteAppointment(start);
	}
	
	public boolean addAppointment(Appointment appointment) {
		return controller.addAppointment(appointment);
	}
	
	@Override
	public void updateView() {
		pc.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pm.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pa.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), controller.get3MonthAppointments(super.getYearCurr(), super.getMonthCurr()));
		pw.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), controller.get3MonthAppointments(super.getYearCurr(), super.getMonthCurr()));
		pd.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), controller.get3MonthAppointments(super.getYearCurr(), super.getMonthCurr()));
	}

	@Override
	public void setAppointment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAppointment() {
		
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
