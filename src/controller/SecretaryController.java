package controller;

import view.secretary.FrameMain;
import view.secretary.PanelAgendaView;
import view.secretary.PanelBookingView;
import view.secretary.PanelCalendarView;
import view.secretary.PanelHeadline;
import view.secretary.PanelMiniCalendar;
import java.util.Calendar;

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
		mnc = new PanelMiniCalendar(this. mc.getDoctorNames());

		main.setTop(hd);
		main.setLeft(mnc);
		main.setRight(cv);
		mnc.sendDateToView();

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
		cv.updateCalendarView(controller.getAppointments());
		av.setToWeekly();
		av.updateAgendaView(controller.getAppointments());
	}
	public void setDailyFormat(){
		dailyORweekly = true;
		cv.setToDaily();
		cv.updateCalendarView(controller.getAppointments());
		av.setToDaily();
		av.updateAgendaView(controller.getAppointments());
	}
	public void setViewDate(int month, int day, int year){ //SETS THE DATE FOR CALENDAR AND AGENDA VIEWS

		if( dailyORweekly){ //IF DAILY VIEW
			cv.setDate(month, day, year);
		    cv.updateCalendarView();
			av.setDate(month, day, year);
			av.updateAgendaView(controller.getAppointments());
		}
		else{ 
			cv.setDate(month, day, year);
			cv.updateCalendarView();
			av.setDate(month, day, year);
			av.updateAgendaView(controller.getAppointments());
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
		cv.updateCalendarView(controller.getAppointments());
		av.updateAgendaView(controller.getAppointments());
		
	}
	@Override
	public void setAppointment() {
		// TODO Auto-generated method stub
		if ( controller.setAppointment(bv.get)
		
	}
	@Override
	public void cancelAppointment() {
		// TODO Auto-generated method stub
		
	}

}

/* book appointment on behalf of client
	public void setAppointment()
	{	
		//inputData.get(0) = month of appointment
		//inputData..get(1) = day of appointment
		//inputData..get(2) = year of appointment
		//inputData..get(3) = start hour of appointment
		//inputData..get(4) = stat minute of appointment
		//inputData.get(5) = end hour of appointment
		//inputData..get(6) = end minute of appointment

		// which of the three doctors the booked appointment's for
		if(bv.getInputChoice().equals("Doctor1")){
			//SEND ALL APPROPRIATE DATA TO WHEREEVER FOR DOCTOR NUMBUH 1
		}
		else if(bv.getInputChoice().equals("Doctor2")){
			//SEND ALL APPROPRIATE DATA TO WHEREEVER FOR DOCTOR NUMBUH 1
			
		}
		else if(bv.getInputChoice().equals("Doctor3")){
			//SEND ALL APPROPRIATE DATA TO WHEREEVER FOR DOCTOR NUMBUH 1
			
		}
public String getRadioButtonChoice(){
		return radioGroup.getSelection().getActionCommand();
	}
	
	public String getEventDeets(){
		return eventTitle.getText().trim();
	}
	
	public String getMonthInput(){
		return addMonth.getSelectedItem().toString();
	}
	
	public String getDayInput(){
		return addDay.getSelectedItem().toString();
	}
	
	public String getYearInput(){
		return addYear.getSelectedItem().toString();
	}
	
	public String getStartH(){
		return timeStartHour.getValue().toString();
	}
	
	public String getStartM(){
		return timeStartMinute.getValue().toString();
	}
	
	public String getEndH(){
		return timeEndHour.getValue().toString();
	}
	
	public String getEndM(){
		return timeEndMinute.getValue().toString();
	}
		
		
		this.updateView();
	}*/