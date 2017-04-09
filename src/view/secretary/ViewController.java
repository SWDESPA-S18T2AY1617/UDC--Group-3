package view.secretary;

import java.util.ArrayList;
public class ViewController{ 

	private FrameMain main;
	private PanelHeadline hd;
	private PanelMiniCalendar mnc;
	private PanelCalendarView cv;
	private PanelAgendaView av;
	private PanelBookingView bv;

	private Boolean dailyORweekly; // daily == true ; false == weekly
	public ViewController(){ 
	
		//super(model);
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

		if( daily){ //IF DAILY VIEW
			cv.setDate(month, day, year);
			cv.updateCalendarView();
			av.setDate(month, day, year);
			av.updateAgendaView();
		}
		else{ // IF WEEKLY VIEW
			//////TRANSFORM "DAY" INTO THE MONDAY OF THAT WEEK. THAT IS ALL WHAT IT NEEDS.
			///////////////////////// I kinda don't know how to do that for now. 
			/////////////////////////////////////
			cv.setDate(month, day, year);
		//	cv.updateCalendarView();
			av.setDate(month, day, year);
		//	av.updateAgendaView();
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
	

}
