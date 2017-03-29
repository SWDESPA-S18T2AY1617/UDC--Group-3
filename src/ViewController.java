
public abstract class ViewController {
	
	protected ModelController model;
	

	public ViewController(ModelController model)
	{
		this.model = model;
	}
	
	public abstract void updateView();
	
	//check all available appointment slots
	public abstract Iterator<Appointment> checkAvailable();
	
	//with filter
	public abstract Iterator<Appointment> checkAvailable();
	
	public abstract Iterator<Appointment> checkAppointments();
	
	public abstract void setAppointment();
	
	public abstract void cancelAppointment();
	
}
