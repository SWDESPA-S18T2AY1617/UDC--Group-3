package db;

public class Client {
	
	public static final String TABLE_NAME = "client";
	public static final String CLIENT_ID = "clientid";
	public static final String CLIENT_NAME = "name";
	
	private int clientid;
	private String name;
	
	public Client() { }
	
	public Client(int clientid, String name) {
		super();
		this.clientid = clientid;
		this.name = name;
	}
	
	public int getClientID() {
		return clientid;
	}
	
	public void setClientID(int clientid) {
		this.clientid = clientid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
