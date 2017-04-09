package controller;

import view.secretary.FrameMain;
import view.secretary.PanelAgendaView;
import view.secretary.PanelBookingView;
import view.secretary.PanelCalendarView;
import view.secretary.PanelHeadline;
import view.secretary.PanelMiniCalendar;

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
		
		main = new FrameMain();
		hd = new PanelHeadline(this);
		cv = new PanelCalendarView(this);
		av = new PanelAgendaView(this);
		bv = new PanelBookingView(this);
		mnc = new PanelMiniCalendar(this);

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
		av.setToWeekly();
	}
	public void setDailyFormat(){
		dailyORweekly = true;
		cv.setToDaily();
		av.setToDaily();
	}
	public void setViewDate(int month, int day, int year){ //SETS THE DATE FOR CALENDAR AND AGENDA VIEWS

	//	if( dailyORweekly){ //IF DAILY VIEW
			cv.setDate(month, day, year);
			//cv.updateCalendarView();
			av.setDate(month, day, year);
			//av.updateAgendaView();
	//	}
	//	else{ // IF WEEKLY VIEW
			//////TRANSFORM "DAY" INTO THE MONDAY OF THAT WEEK. THAT IS ALL WHAT IT NEEDS.
			///////////////////////// I kinda don't know how to do that for now. 
			/////////////////////////////////////
		//	cv.setDate(month, day, year);
		//	cv.updateCalendarView();
		//	av.setDate(month, day, year);
		//	av.updateAgendaView();
	//	}
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

	//book appointment on behalf of client
	public void setAppointment()
	{	//arraylist containing all reservation details from user
		// sorry for not using the calendar thing... 
		//am still trying to adapt and adjust to be more consistent and uniform along with your guys' codes.

		
		
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

		this.updateView();
	}
	
	public void cancelAppointment()
	{
		
		
		this.updateView();
	}*/