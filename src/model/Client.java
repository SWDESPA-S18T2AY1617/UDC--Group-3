package model;

public class Client {
	
	public static int ctr = 0;
	
	private final int id;
	private String name;

	public Client(String n)
	{
		ctr++;
		
		this.id = ctr;
		name = n;
	}
	
	public Client(int id, String name)
	{
		ctr++;
		
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
