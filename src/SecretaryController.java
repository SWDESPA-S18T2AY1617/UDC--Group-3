
public class SecretaryController {

	private SecretaryView view;
	private ModelController model;
	
	public SecretaryController(ModelController model)
	{
		this.model = model;
		view = new SecretaryView();
	}
	
	public void updateView() {
		
	}
	
	//check all appointment slots
	public Iterator<Appointment> checkAppointments() {
		
	}
	
	//check filled appointment slots for day
	public Iterator<Appointment> checkAppointments() {
		
	}
	
	//check filled appointment slots for week
	public Iterator<Appointment> checkAppointments() {
		
	}
	
	//check all available appointment slots
	public Iterator<Appointment> checkAvailable() {
		
	}
	
	//with filter
	public Iterator<Appointment> checkAvailable() {
		
	}
	
	//verify filled appointment slots of all doctors
	public Iterator<Appointment> verifyAppointments() {
		
	}
	
	//with filter (some doctors)
	public Iterator<Appointment> verifyAppointments() {
		
	}
	
	//book appointment on behalf of client
	public void reserveAppointment() {
		
		
		this.updateView();
	}
	
	public void cancelAppointment() {
		
		
		this.updateView();
	}

}
