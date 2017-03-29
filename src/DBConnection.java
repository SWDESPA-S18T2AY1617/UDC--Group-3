package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private final String URL = "jdbc:mysql://localhost:3306/sm_cinemas?user=root";
	private final String username = "root";
	private final String password = "DLSU";
	
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	
	public DBConnection() { }
	
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
