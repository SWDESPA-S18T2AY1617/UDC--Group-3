package Controller;
import Appointment;

public abstract class ViewController {
	
	protected ModelController model;
	

	public ViewController(ModelController model)
	{
		this.model = model;
	}
	
	public abstract void updateView();
	
	//check appointments (parameter == filter, can be changed to ArrayList<Integer> if preferred, depends on model implementation)
	public Iterator<Appointment> checkAppointments(ArrayList<String> filter);
	
	public abstract void setAppointment();
	
	public abstract void cancelAppointment();
	
}
