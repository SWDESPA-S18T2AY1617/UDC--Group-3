package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class DBConnection {

	private final String URL;
	private final String username;
	private final String password;
	
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	
	public DBConnection() { 
		configure();
	}
	
	public void configure() throws IOException{
		File file = new File("db.config");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<String> list;
		while((line = br.readLine()) != null){
			list.add(line);
		}
		fr.close();
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
