
public class DoctorController {

	private DoctorView view;
	private ModelController model;
	
	public DoctorController(ModelController model)
	{
		this.model = model;
		view = new DoctorView();
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
	
	//set 1 appointment slot
	public void setAppointmentSlot() {
		
		
		this.updateView();
	}
	
	//recurring
	public void setAppointmentSlot() {
		
		
		this.updateView();
	}
	
	public void changeAppointmentDate() {
		
		
		this.updateView();
	}
	
	public void cancelAppointment() {
		
		
		this.updateView();
	}

}
