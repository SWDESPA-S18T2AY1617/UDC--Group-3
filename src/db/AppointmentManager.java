package db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class AppointmentManager {
	
	public ArrayList<AppointmentDB> getAllAppointments() throws IOException{
		ArrayList<AppointmentDB> appointmentList = new ArrayList<AppointmentDB>();
		AppointmentDB appointment = null;
		
		String query = "SELECT * FROM " + AppointmentDB.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try {
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()) { // .next returns a whole row from the table
				appointment = new AppointmentDB();
				appointment.setAppointment_id(result.getInt(AppointmentDB.COL_APPOINTMENTID));
				appointment.setStart_time(result.getString(AppointmentDB.COL_STARTTIME));
				appointment.setEnd_time(result.getString(AppointmentDB.COL_ENDTIME));
				appointment.setDate(result.getString(AppointmentDB.COL_DATE));
				appointment.setClient_id(result.getInt(AppointmentDB.COL_CLIENTID));
				appointment.setDoctor_id(result.getInt(AppointmentDB.COL_DOCTORID));
				
				appointmentList.add(appointment);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return appointmentList;
	}
	
	public boolean addAppointment(AppointmentDB appointment) throws IOException{
		boolean result;
		
		String query = "INSERT INTO " + AppointmentDB.TABLE_NAME + " (" +
				AppointmentDB.COL_STARTTIME + ", " + AppointmentDB.COL_ENDTIME +
				", " + AppointmentDB.COL_DATE + ", " +  
				AppointmentDB.COL_CLIENTID + ", " + AppointmentDB.COL_DOCTORID + ") VALUES ('" + 
				appointment.getStart_time() + "', '" + appointment.getEnd_time() + "', '" +
				appointment.getDate() + "', '" +  
				appointment.getClient_id() + "', '" + appointment.getDoctor_id() + "')";
		
		DBConnection db = new DBConnection();
		
		try {
			// result : successful or not successful
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
	
	public boolean deleteAppointment(AppointmentDB appointment) throws IOException{
		boolean result;
		
		String query = "DELETE FROM " + AppointmentDB.TABLE_NAME + 
				       " WHERE " + AppointmentDB.COL_APPOINTMENTID + " = '" + 
				       appointment.getAppointment_id() + "'";
		
		DBConnection db = new DBConnection();
		
		try {
			// result : successful or not successful
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
	
	//how would the appointment be retrieved from the db? would it be by the time or what?
	public AppointmentDB getAppointment(String what, String wew) throws IOException{
		
		AppointmentDB appointment = null;
		ResultSet result;

		//I intentionally left the error below to remind mahself what to edit here
		String query = "SELECT * FROM " + AppointmentDB.TABLE_NAME +
					   " WHERE " + AppointmentDB.COL_ + " = '" + what + 
					   "' AND " + AppointmentDB.COL_ + " = '" + wew + "'";
		
		DBConnection db = new DBConnection();
		
		try {
			// result : successful or not successful
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			appointment = new AppointmentDB();
			appointment.setAppointment_id(result.getInt(AppointmentDB.COL_APPOINTMENTID));
			appointment.setStart_time(result.getString(AppointmentDB.COL_STARTTIME));
			appointment.setEnd_time(result.getString(AppointmentDB.COL_ENDTIME));
			appointment.setDate(result.getString(AppointmentDB.COL_DATE));
			appointment.setClient_id(result.getInt(AppointmentDB.COL_CLIENTID));
			appointment.setDoctor_id(result.getInt(AppointmentDB.COL_DOCTORID));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return appointment;		
	}
	
	public int getCurrAutoInc() throws IOException{
		ResultSet result;
		int ctr = 0;
		
		String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES" +
				   	   " WHERE TABLE_SCHEMA = 'udc_db' AND TABLE_NAME = '" + AppointmentDB.TABLE_NAME + "'";
	
		DBConnection db = new DBConnection();
		
		try {
			// result : successful or not successful
			db.select(query);
			
			result = db.getResult();
			result.next();
			
			ctr = result.getInt("AUTO_INCREMENT");			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return ctr;		
	}

}
