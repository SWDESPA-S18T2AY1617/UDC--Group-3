
public class ClientController {
	
	private ClientView view;
	private ModelController model;
	
	public ClientController(ModelController model)
	{
		this.model = model;
		view = new ClientView();
	}
	
	public void updateView() {
		
	}
	
	//check all available appointment slots
	public Iterator<Appointment> checkAvailable() {
		
	}
	
	//with filter
	public Iterator<Appointment> checkAvailable() {
		
	}
	
	//check client's reservations
	public Iterator<Appointment> getAppointments() {
		
	}
	
	public void reserveAppointment() {
		
		
		this.updateView();
	}
	
	public void cancelAppointment() {
		
		
		this.updateView();
	}

}
