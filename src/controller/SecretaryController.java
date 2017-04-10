package controller;

import view.secretary.FrameMain;
import view.secretary.PanelAgendaView;
import view.secretary.PanelBookingView;
import view.secretary.PanelCalendarView;
import view.secretary.PanelHeadline;
import view.secretary.PanelMiniCalendar;
import model.Client;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class SecretaryController extends ViewController {
	private FrameMain main;
	private PanelHeadline hd;
	private PanelMiniCalendar mnc;
	private PanelCalendarView cv;
	private PanelAgendaView av;
	private PanelBookingView bv;

	private Boolean dailyORweekly; // daily == true ; false == weekly

	public SecretaryController(MainController mc) { 
		super(mc);

		dailyORweekly = true;

		main = new FrameMain();
		hd = new PanelHeadline(this);
		cv = new PanelCalendarView(this, mc.getDoctorNames());
		av = new PanelAgendaView(this, mc.getDoctorNames());
		bv = new PanelBookingView(this);
		mnc = new PanelMiniCalendar(this, mc.getDoctorNames());

		main.setTop(hd);
		main.setLeft(mnc);
		main.setRight(cv);
		mnc.sendDateToView();
		setDoc1(false);
		setDoc2(false);
		setDoc3(false);
	}
	public void setCalendarPanel(){
		main.setRight(cv);
	}
	public void setAgendaPanel(){
		main.setRight(av);
	}
	public void setBookingPanel(){
		main.setRight(bv);
	}
	public void setWeeklyFormat(){
		dailyORweekly = false;
		cv.setToWeekly();
		cv.updateCalendarView(controller.getAllAppointments());
		av.setToWeekly();
		av.updateAgendaView(controller.getAllAppointments());
	}
	public void setDailyFormat(){
		dailyORweekly = true;
		cv.setToDaily();
		cv.updateCalendarView(controller.getAllAppointments());
		av.setToDaily();
		av.updateAgendaView(controller.getAllAppointments());
	}
	public void setViewDate(int month, int day, int year){ //SETS THE DATE FOR CALENDAR AND AGENDA VIEWS

		if( dailyORweekly){ //IF DAILY VIEW
			cv.setDate(month, day, year);
		    cv.updateCalendarView(controller.getAllAppointments());
			av.setDate(month, day, year);
			av.updateAgendaView(controller.getAllAppointments());
		}
		else{ 
			cv.setDate(month, day, year);
			cv.updateCalendarView(controller.getAllAppointments());
			av.setDate(month, day, year);
			av.updateAgendaView(controller.getAllAppointments());
		}
	}
	public void setDoc1(Boolean setting){
		cv.setDoc1(setting);
		av.setDoc1(setting);
	}
	public void setDoc2(Boolean setting){
		cv.setDoc2(setting);
		av.setDoc2(setting);
	}
	public void setDoc3(Boolean setting){
		cv.setDoc3(setting);
		av.setDoc3(setting);
	}
	@Override
	public void updateView() {
		cv.updateCalendarView(controller.getAllAppointments());
		av.updateAgendaView(controller.getAllAppointments());
		
	}
	@Override
	public void setAppointment() {
		Client temp = new Client(bv.getClientName());
		// TODO Auto-generated method stub
		//getMonthInput, getDayInput, getYearInput
		//getStartH, getStartM
		//getEndH, getEndM
		Calendar start = Calendar.getInstance();
		start.set(bv.getYearInput(), bv.getMonthInput(), bv.getDayInput(), bv.getStartH(), bv.getStartM(),0 );
		Calendar end = Calendar.getInstance();
		end.set(bv.getYearInput(), bv.getMonthInput(), bv.getDayInput(), bv.getEndH(), bv.getEndM(),0 );

		if(controller.setAppointment(temp, start, end))
			JOptionPane.showMessageDialog(null, "Appointment reserved!");
		else
			JOptionPane.showMessageDialog(null, "Appointment booking error, please try again.");
		
	}
	@Override
	public void cancelAppointment() {
		// TODO Auto-generated method stub
		
	}

}
