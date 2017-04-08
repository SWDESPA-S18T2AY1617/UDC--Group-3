package view.secretary;
import Appointment;

public class ViewController{ 

	private SecretaryView view;
	private PanelHeadline hd;
	private PanelMiniCalendar mnc;
	private PanelCalendarView cv;
	private PanelAgendaView av;
	private PanelBookingView bv;
	int month,
		day,
		year;
	public ViewController(){ 

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
		cv.setToWeekly();
		av.setToWeekly();
	}
	public void setDailyFormat(){
		cv.setToDaily();
		av.setToDaily();
	}
	public void setViewDate(int month, int day, int year){
		cv.setDate(month, day, year);
	//	cv.updateCalendarView();
		av.setDate(month, day, year);
	//	av.updateView();
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

	public void updateView() 
	{
		
	}
	
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	public Iterator<Appointment> checkAppointments(ArrayList<String> filter)
	{
		
	}
	
	//book appointment on behalf of client
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		
		
		this.updateView();
	}

}
