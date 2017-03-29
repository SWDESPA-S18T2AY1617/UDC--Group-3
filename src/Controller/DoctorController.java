package Controller;
import Appointment;

public class DoctorController extends ViewController {
	
	private DoctorView view;
	
	public DoctorController(ModelController model)
	{
		super(model);
		view = new DoctorView();
	}
	
	public void updateView() {
		
	}
	
	//check all appointment slots of this doctor
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check filled appointment slots for day
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check filled appointment slots for week
	public Iterator<Appointment> checkAppointments()
	{
		
	}
	
	//check all available appointment slots of this doctor
	public void Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//set 1 appointment slot
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	
	//recurring
	public void setAppointment()
	{
		
		
		this.updateView();
	}
	
	public void changeAppointment()
	{
		
		
		this.updateView();
	}
	
	public void cancelAppointment()
	{
		
		
		this.updateView();
	}

}
