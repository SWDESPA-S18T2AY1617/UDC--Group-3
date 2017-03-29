
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
	
	//check all available appointment slots
	public Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//with filter
	public Iterator<Appointment> checkAvailable()
	{
		
	}
	
	//check client's reservations
	public Iterator<Appointment> checkAppointments()
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
