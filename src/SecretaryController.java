
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
	
	//check all appointment slots (all doctors)
	public Iterator<Appointment> checkAppointments() 
	{
		
	}
	
	//check all appointment slots of a doctor
	public Iterator<Appointment> checkAppointments() 
	{
		
	}
	
	//check all appointments for day
	public Iterator<Appointment> checkAppointments() 
	{
		
	}
	
	//check all appointments for week
	public Iterator<Appointment> checkAppointments() 
	{
		
	}
	
	//check all available appointment slots
	public Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//with filter (some doctors only)
	public Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//verify filled appointment slots of all doctors
	public Iterator<Appointment> verifyAppointments()
	{
		
	}
	
	//with filter (some doctors)
	public Iterator<Appointment> verifyAppointments()
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
