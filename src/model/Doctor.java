package model;

public class Doctor {
	
	public static int ctr = 0;
	
	private final int id;
	private String name;
	
	public Doctor(String n)
	{
		ctr++;
		
		this.id = ctr;
		name = n;
	}
	
	public Doctor(int id, String name) {
		this.id = id;
		this.name = name;
	}
	/*
	public void addAppointment(Appointment a) {
		a.setDoctor(this);
		appointments.add(a);
		sortAppointments();
	}
	
	public void addAppointments(ArrayList<Appointment> a) {
		for(Appointment appointment : a)
			appointment.setDoctor(this);
		appointments.addAll(a);
		sortAppointments();
	}
	
	public void setAppointment() {
		
	}
	*/
	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	public Iterator<Appointment> getAppointments() {
		return appointments.iterator();
	}
	
	public void sortAppointments() {
		Collections.sort(appointments, new Comparator<Appointment>() {
			public int compare(Appointment s1, Appointment s2) {
				int n = s1.getYear() - s2.getYear();
				if (n == 0)
					n = s1.getMonth() - s2.getMonth();
				if (n == 0)
					n = s1.getDay() - s2.getDay();
				if (n == 0)
					n = s1.getStartHour() - s2.getStartHour();
				if (n == 0)
					n = s1.getStartMinute() - s2.getStartMinute();
				return n;
			}
		});
	}
	*/
}
