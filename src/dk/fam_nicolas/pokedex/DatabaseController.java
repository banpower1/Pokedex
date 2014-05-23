/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * DatabaseController Class to communicate with the database
 * @author Banpower1
 * @version 1.0
 */
public class DatabaseController
{
	private String databaseName;

	/**
	 * Sets the location of the database, the database must be in the project root
	 * @param arg0 The full name of the database
	 */
	public DatabaseController(String arg0)
	{
		databaseName = arg0;
	}

	
	/**
	 * Opens a connection and sends a query to the database and returns the result set
	 * @param arg0 The query to pass to the database
	 * @return The result of the query
	 */
	public ArrayList<ArrayList<Object>> request(String arg0)
	{
		java.sql.Connection connection = null;
		ResultSet rs;
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery(arg0);

			ArrayList<ArrayList<Object>> schema = new ArrayList<ArrayList<Object>>();
			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				for(int i = 1; i - 1 < rs.getMetaData().getColumnCount(); i++)
				{
					row.add(rs.getObject(i));
				}
				
				
				schema.add(row);
			}
			
			return schema;
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close(); // Keeping connection to the database at minimum to prevent unclosed connections
			}
			catch(SQLException e)
			{
				//Connection close failed
				System.err.println(e);
			}
		}
		
	}

}
