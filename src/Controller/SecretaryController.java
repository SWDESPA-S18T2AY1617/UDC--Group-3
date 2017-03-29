package Controller;
import Appointment;

public class SecretaryController extends ViewController {

	private SecretaryView view;
	
	public SecretaryController(ModelController model)
	{
		super(model);
		view = new SecretaryView();
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
