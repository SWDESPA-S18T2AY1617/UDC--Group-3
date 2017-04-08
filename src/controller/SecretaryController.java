package controller;

public class SecretaryController extends ViewController {

	//private SecretaryView view;
	
	public SecretaryController(MainController controller)
	{
		super(controller);
		
		//remember to have an instance of their specific ViewControllers to their views
		//view = new SecretaryView(this);
	}
	
	public void updateView(/*Maybe an Iterator of Appointments*/)
	{
		//will be called by MainController
		//calendarview.updateCalendarView( send a list or iterator of appoinments that would send all appoints)
		//agendaView.updateCalendarView( send a list or iterator of appoinments that would send all appoints)

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


		/* THESE CAN BE USED TO TAKE THE INPUT FIELDS FROM SECRETARY VIEW FROM "PanelBookingView"
		Integer.parseInt(bookingview.getMonthInput()  = month of appointment
		Integer.parseInt(bookingview.getDayInput() = day of appointment
		Integer.parseInt(bookingview.getYearInput() = year of appointment
		Integer.parseInt(bookingview.getStartH() = starting hour of appointment
		Integer.parseInt(bookingview.getStartM() = stat minute of appointment
		Integer.parseInt(bookingview.getEndH() = end hour of appointment
		Integer.parseInt(bookingview.getEndM() = end minute of appointment
		

		// which of the three doctors the booked appointment's for
		if(bookingview.getInputChoice().equals("Doctor1")){
			//SEND ALL APPROPRIATE APPONTMENT DATA TO WHEREEVER FOR DOCTOR NUMBUH 1
		}
		else if(bookingview.getInputChoice().equals("Doctor2")){
			//SEND ALL APPROPRIATE APPONTMENT DATA TO WHEREEVER FOR DOCTOR NUMBUH 2
			
		}
		else if(bookingview.getInputChoice().equals("Doctor3")){
			//SEND ALL APPROPRIATE APPONTMENT DATA TO WHEREEVER FOR DOCTOR NUMBUH 3
			
		}*/
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		//will call MainController and will be called by the view to cancel appointment
		
		this.updateView();
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