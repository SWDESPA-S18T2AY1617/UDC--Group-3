package db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import db.DBConnection;
//import db.DoctorDB;

public class DoctorManager{

	public ArrayList<DoctorDB> getAllDoctors() throws IOException{
		ArrayList<DoctorDB> doctorList = new ArrayList<DoctorDB>();
		DoctorDB doctor = null;
		
		String query = "SELECT * FROM " + DoctorDB.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try{
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()){
				doctor = new DoctorDB();
				doctor.setDoctor_id(result.getInt(DoctorDB.COL_DOCTORID));
				doctor.setName(result.getString(DoctorDB.COL_NAME));
				
				doctorList.add(doctor);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return doctorList;
	}
	
	public boolean addDoctor(DoctorDB doctor) throws IOException{
		boolean result;
		
		String query = "INSERT INTO " + DoctorDB.TABLE_NAME + " ("
		+ DoctorDB.COL_NAME + ") VALUES ('" + doctor.getName() + "')";
		
		DBConnection db = new DBConnection();
		
		try{
			result = db.editRow(query);
		} finally{
			if(db != null){
				db.close();
			}
		}
		
		return result;
	}
	
	public DoctorDB getDoctor(String name) throws IOException{
		DoctorDB doctor = null;
		ResultSet result;
		
		String query = "SELECT * FROM " + DoctorDB.TABLE_NAME +
						" WHERE " + DoctorDB.COL_NAME + " = '" + name + " '";
			
		DBConnection db = new DBConnection();
		
		try{
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			doctor = new DoctorDB();
			doctor.setDoctor_id(result.getInt(DoctorDB.COL_DOCTORID));
			doctor.setName(result.getString(DoctorDB.COL_NAME));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return doctor;
	}
	
	public DoctorDB getDoctor(int id) throws IOException{
		DoctorDB doctor = null;
		ResultSet result;
		
		String query = "SELECT * FROM " + DoctorDB.TABLE_NAME +
						" WHERE " + DoctorDB.COL_DOCTORID + " = '" + id + " '";
			
		DBConnection db = new DBConnection();
		
		try{
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			doctor = new DoctorDB();
			doctor.setDoctor_id(result.getInt(DoctorDB.COL_DOCTORID));
			doctor.setName(result.getString(DoctorDB.COL_NAME));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return doctor;
	}
	
	public boolean deleteDoctor(DoctorDB doctor) throws IOException{
		boolean result;
		
		String query = "DELETE FROM " + DoctorDB.TABLE_NAME + 
						" WHERE " + DoctorDB.COL_DOCTORID + " = '" +
						doctor.getDoctor_id() + "'";
		
		DBConnection db = new DBConnection();
		
		try{
			result = db.editRow(query);
		} finally{
			if(db != null){
				db.close();
			}
		}
		
		return result;
	}
	
}
