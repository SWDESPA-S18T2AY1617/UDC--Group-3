package clinicDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class ClientManager {
	
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> clientList = new ArrayList<Client> ();
		Client client = null;
		
		String query = "SELECT * FROM " + Client.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try {
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()) {
				client = new Client();
				client.setClientID(result.getInt(Client.CLIENT_ID));
				client.setName(result.getString(Client.CLIENT_NAME));
				
				clientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
	
	public boolean addClient(Client client) {
		boolean result;
		
		String query = "INSERT INTO" + client.TABLE_NAME + " (" + client.CLIENT_ID + ", " 
		+ client.CLIENT_NAME + ") VALUES ('" + client.getClientID() + "', '" + client.getName() + "')";
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
}
