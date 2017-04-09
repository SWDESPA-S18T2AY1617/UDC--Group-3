package db;

public class DoctorDB{
	
	public static final String TABLE_NAME = "Doctor";
	public static final String COL_DOCTORID = "doctor_id";
	public static final String COL_NAME = "name";
	
	private int doctor_id;
	private String name;
	
	public DoctorDB(){}
	
	public DoctorDB(int doctor_id, String name){
		super();
		this.doctor_id = doctor_id;
		this.name = name;
	
	}

	public int getDoctor_id(){
		return this.doctor_id;
	}
	
	public void setDoctor_id(int doctor_id){
		this.doctor_id = doctor_id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}