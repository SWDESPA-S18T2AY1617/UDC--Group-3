package db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class AppointmentManager {
	
	public ArrayList<Appointment> getAllAppointments() throws IOException{
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		Appointment appointment = null;
		
		String query = "SELECT * FROM " + Appointment.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try {
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()) { // .next returns a whole row from the table
				appointment = new Appointment();
				appointment.setAppointment_id(result.getInt(Appointment.COL_APPOINTMENTID));
				appointment.setStart_time(result.getString(Appointment.COL_STARTTIME));
				appointment.setEnd_time(result.getString(Appointment.COL_ENDTIME));
				appointment.setDate(result.getString(Appointment.COL_DATE));
				appointment.setType(result.getString(Appointment.COL_TYPE));
				appointment.setClient_id(result.getInt(Appointment.COL_CLIENTID));
				appointment.setDoctor_id(result.getInt(Appointment.COL_DOCTORID));
				
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
	
	public boolean addAppointment(Appointment appointment) throws IOException{
		boolean result;
		
		String query = "INSERT INTO " + Appointment.TABLE_NAME + " (" +
				Appointment.COL_STARTTIME + ", " + Appointment.COL_ENDTIME +
				", " + Appointment.COL_DATE + ", " + Appointment.COL_TYPE + ", " + 
				Appointment.COL_CLIENTID + ", " + Appointment.COL_DOCTORID + ") VALUES ('" + 
				appointment.getStart_time() + "', '" + appointment.getEnd_time() + "', '" +
				appointment.getDate() + "', '" + appointment.getType() + "', '" + 
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
	
	public boolean deleteAppointment(Appointment appointment) throws IOException{
		boolean result;
		
		String query = "DELETE FROM " + Appointment.TABLE_NAME + 
				       " WHERE " + Appointment.COL_APPOINTMENTID + " = '" + 
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
	public Appointment getAppointment(String what, String wew) throws IOException{
		
		Appointment appointment = null;
		ResultSet result;

		String query = "SELECT * FROM " + Appointment.TABLE_NAME +
					   " WHERE " + Appointment.COL_ + " = '" + what + 
					   "' AND " + Appointment.COL_ + " = '" + wew + "'";
		
		DBConnection db = new DBConnection();
		
		try {
			// result : successful or not successful
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			appointment = new Appointment();
			appointment.setAppointment_id(result.getInt(Appointment.COL_APPOINTMENTID));
			appointment.setStart_time(result.getString(Appointment.COL_STARTTIME));
			appointment.setEnd_time(result.getString(Appointment.COL_ENDTIME));
			appointment.setDate(result.getString(Appointment.COL_DATE));
			appointment.setType(result.getString(Appointment.COL_TYPE));
			appointment.setClient_id(result.getInt(Appointment.COL_CLIENTID));
			appointment.setDoctor_id(result.getInt(Appointment.COL_DOCTORID));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return appointment;		
	}

}
