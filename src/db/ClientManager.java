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
		
		String query = "INSERT INTO" + Client.TABLE_NAME + " (" + Client.CLIENT_ID + ", " 
		+ Client.CLIENT_NAME + ") VALUES ('" + client.getClientID() + "', '" + client.getName() + "')";
		
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
	
	public boolean deleteClient(Client client) {
		boolean result;
		
		String query = "DELETE FROM " + Client.TABLE_NAME + " WHERE " 
						+ Client.CLIENT_ID + " = '" + client.getClientID() +"'";
		
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
	
	public boolean updateClient(Client client) {
		boolean result;
		
		String query = "UPDATE " + Client.TABLE_NAME +
					   "SET " + Client.CLIENT_ID + " = '" + client.getClientID()
					   + "' WHERE " + Client.CLIENT_NAME + " = '" + client.getName() + "'";
		
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
	
	public Client getClient(int client_id, String name) {
		
		Client client = null;
		ResultSet result;
		
		String query = "SELECET * FROM " + Client.TABLE_NAME + " WHERE " +
						Client.CLIENT_ID + " = '" + client_id + "' AND " +
						Client.CLIENT_NAME + " = '" + name + "'";
		
		DBConnect db = new DBConnection();
		
		try {
			
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			client = new Client();
			client.setClientID(result.getInt(Client.CLIENT_ID));
			client.setName(result.getString(Client.CLIENT_NAME));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return client;
	}
}
