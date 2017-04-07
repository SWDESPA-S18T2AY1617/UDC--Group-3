package controller;

public class ClientController extends ViewController {
	
	//private ClientView view;
	
	public ClientController(MainController controller)
	{
		super(controller);
		
		//remember to have an instance of their specific ViewControllers to their views
		//view = new ClientView(this);
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
