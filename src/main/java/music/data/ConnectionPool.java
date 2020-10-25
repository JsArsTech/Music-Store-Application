package music.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class ConnectionPool
{
	private static ConnectionPool pool = null;
	private static DataSource dataSource = null;

	public synchronized static ConnectionPool getInstance() 
	{
		if (pool == null)
		{
			pool = new ConnectionPool();
		}
		return pool;
	}

	private ConnectionPool()
	{
		try 
		{
			var ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/musicDB");				
		}
		catch (NamingException ex)
		{
			System.err.println(ex)
		}
	}

	public Connection getConnection()
	{
		try 
		{
			return dataSource.getConnection();
		}
		catch (SQLException sqlex)
		{
			System.err.println(sqle);
			return null;
		}
	}

	public void freeConnection(Connection c)
	{
		try 
		{
			c.close();
		}
		catch (SQLException sqle) 
		{
			System.err.println(sqle);
		}
	}
}

