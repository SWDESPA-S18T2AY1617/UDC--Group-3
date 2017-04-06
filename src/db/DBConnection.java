package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class DBConnection {

	private String URL;
	private String username;
	private String password;
	
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	
	public DBConnection() throws IOException { 
		configure();
	}
	
	/* okay bois here's what u gonna do for this shiz db
	 * first off create a new file sa UDC-Group-3 folder
	 * then u put the url on the first line     sample:jdbc:mysql://localhost:3306/sm_cinemas?user=root
	 * tapos sa next line username naman        sample:root
	 * and finally the password					sample:DLSU
	 * iba iba to kada machine so sabi ni sir gawin ko daw na ganito
	 * so save niyo ung file not as text file but as a .config file para cool bois tayo
	 */
	public void configure() throws IOException{
		File file = new File("db.config");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		ArrayList<String> list = new ArrayList<String>();
		while((line = br.readLine()) != null){
			list.add(line);
		}
		fr.close();
		this.URL = list.get(0);
		this.username = list.get(1);
		this.password = list.get(2);
	}
	
	public boolean editRow(String query)
	{
		try
		{
			// 1. Get a connection to database
			this.conn = DriverManager.getConnection(URL, username, password);
			
			// 2. Create a statement
			this.statement = conn.createStatement();

			System.out.println("(INSERT/UPDATE/DELETE) Database connected");
			System.out.println("Query: " + query);

			// 3. Execute SQL query
			return statement.executeUpdate(query) != 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public void select(String query)
	{
		try
		{
			// 1. Get a connection to database
			this.conn = DriverManager.getConnection(URL, username, password);
			
			// 2. Create a statement
			this.statement = conn.createStatement();

			System.out.println("(SELECT) Database connected");
			System.out.println("Query: " + query);

			// 3. Execute SQL query
			this.result = statement.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			if (result != null)
				result.close();

			if (statement != null)
				statement.close();

			if (conn != null)
				conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet getResult()
	{
		return result;
	}
	
}
