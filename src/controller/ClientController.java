package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import view.client.*;

import model.CalendarPointers;
import model.Client;

public class ClientController extends ViewController {
	
	private Client client;
	
	private FrameMain f;
	private PanelCalendar pc;
	private PanelMenu pm;
	private PanelCreate pcr;
	private PanelDay pd;
	private PanelWeek pw;
	private PanelReservation pa;
	
	public static final int PANEL_DAY = 1;
	public static final int PANEL_RESERVATION = 2;
	public static final int PANEL_WEEK = 3;
	public static final int PANEL_CREATE = 4;
	
	public ClientController(MainController controller)
	{
		super(controller);
		
		f = new FrameMain();
		pc = new PanelCalendar(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pm = new PanelMenu(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pcr = new PanelCreate(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pa = new PanelReservation(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pw = new PanelWeek(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pd = new PanelDay(this);
		
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pd);
	}
	
	public ClientController(MainController controller, Client client)
	{
		super(controller);
		this.client = client;
		
		f = new FrameMain();
		pc = new PanelCalendar(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pm = new PanelMenu(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pcr = new PanelCreate(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pa = new PanelReservation(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pw = new PanelWeek(this, super.getYearBound(), super.getMonthBound(), super.getDayBound());
		pd = new PanelDay(this);
		
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pd);
	}

	public void updateAll(int year, int month, int day) {
		//ArrayList<Activity> act = new ArrayList<>();
		//while(activity != null && activity.hasNext())
		//	act.add(activity.next());
		pc.update(month, year, day);
		pa.update(month, day, year);
		pm.update(month, day, year);
		pd.update(month, day, year);
		pw.update(year, month, day);
	}
	
	public void setMainPanel(int panelConstant) {
		switch(panelConstant) {
			case ClientController.PANEL_DAY :
				f.setRightPanel(pd);
				pc.enableBtnCreate();
				break;
		     case ClientController.PANEL_RESERVATION :
				f.setRightPanel(pa);
				pc.enableBtnCreate();
				break;
				
			case ClientController.PANEL_CREATE :
				f.setRightPanel(pcr);
				pm.unselectToggleBtns();
				break;
			case ClientController.PANEL_WEEK:
				f.setRightPanel(pw);
				pc.enableBtnCreate();
				break;
		}
	}
	
	public void updateView(/*Maybe an Iterator of Appointments*/)
	{
		//will be called by MainController
		
	}
	/*
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	public Iterator<Appointment> checkAppointments(ArrayList<String> filter)//An iterator of appointments will be better
	{
		//will call MainController to check appointments from the model
	}
	*/
	public void setAppointment()
	{
		//will call MainController and will be called by the view to set appointment to the model
		
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		//will call MainController and will be called by the view to cancel appointment
		
		this.updateView();
	}

}
