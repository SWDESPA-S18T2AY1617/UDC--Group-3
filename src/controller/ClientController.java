package controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import view.client.*;
import model.Appointment;
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
	private MainController controller;
	
	public static final int PANEL_DAY = 1;
	public static final int PANEL_RESERVATION = 2;
	public static final int PANEL_WEEK = 3;
	public static final int PANEL_CREATE = 4;
	
	public ClientController(MainController controller)
	{
		super(controller);
		this.controller = controller;
		
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
		
		f.setFTitle(client.getName());
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pd);
	}

	public void updateAll(Iterator<Appointment> activity) {
		//ArrayList<Activity> act = new ArrayList<>();
		//while(activity != null && activity.hasNext())
		//	act.add(activity.next());
		pc.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pa.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);
		pm.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr());
		pd.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);
		pw.update(super.getMonthCurr(), super.getDayCurr(), super.getYearCurr(), activity);
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
	
	public Client getClient() {
		return client;
	}
	
	public ArrayList<String> getDoctorName() {
		
		return this.getDoctorName();
	}
	/*
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	public Iterator<Appointment> checkAppointments(ArrayList<String> filter)//An iterator of appointments will be better
	{
		//will call MainController to check appointments from the model
	}
	*/
	
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	public boolean setAppointment(Client c, Calendar start, Calendar end) {
		// TODO Auto-generated method stub
		return controller.setAppointment(c, start, end);
		
	}

	public void cancelAppointment(Calendar start) {
		// TODO Auto-generated method stub
		controller.cancelAppointment(start);
	}

	@Override
	public void setAppointment() {
		// TODO Auto-generated method stub
		
	}

}
