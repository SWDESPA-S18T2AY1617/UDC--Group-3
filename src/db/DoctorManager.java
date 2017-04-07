import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import db.DBConnection;
//import db.Doctor;

public class DoctorManager{

	public ArrayList<Doctor> getAllDoctors(){
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		Doctor doctor = null;
		
		String query = "SELECT * FROM " + Doctor.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try{
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()){
				doctor = new Doctor();
				doctor.setDoctor_id(result.getInt(Doctor.COL_DOCTORID));
				doctor.setName(result.getString(Doctor.COL_NAME));
				
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
	
	public boolean addDoctor(Doctor doctor){
		boolean result;
		
		String query = "INSERT INTO " + Doctor.TABLE_NAME + " ("
		+ Doctor.COL_NAME + ") VALUES ('" + doctor.getName() + "')";
		
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
	
	public Doctor getDoctor(String name){
		Doctor doctor = null;
		ResultSet result;
		
		String query = "SELECT * FROM " + Doctor.TABLE_NAME +
						" WHERE " + Doctor.COL_NAME " = '" + name + " '";
			
		DBConnection db = new DBConnection();
		
		try{
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			doctor = new Doctor();
			doctor.setDoctor_id(result.getInt(Doctor.COL_DOCTORID));
			doctor.setName(result.getString(Doctor.COL_NAME));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return doctor;
	}
	
	
}
