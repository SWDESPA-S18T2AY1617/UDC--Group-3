package Controller;
import Appointment;

public class ClientController extends ViewController {
	
	private ClientView view;
	
	public ClientController(ModelController model)
	{
		super(model);
		view = new ClientView();
	}
	
	public void updateView()
	{
		
	}
	
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	public Iterator<Appointment> checkAppointments(ArrayList<String> filter)
	{
		
	}
	
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		
		
		this.updateView();
	}

}
