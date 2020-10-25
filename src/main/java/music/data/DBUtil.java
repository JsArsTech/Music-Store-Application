package music.data;

import java.sql.*;


public class DBUtil 
{
	public static void closeStatement(Statement s)
	{
		try 
		{
			if (s != null)
			{
				s.close();
			}
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
		}
	}

	public static void closeStatement(Statement s)
	{
		try 
		{
			if (ps != null)
			{
				ps.close();
			}
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
		}
	}	
	
	public void closeResultSet(ResultSet rs)
	{
		try 
		{
			if (rs != null)
			{
				rs.close();
			}
		} 
		catch (SQLException ex)
		{
			System.err.println(ex);
		}
	}	
}