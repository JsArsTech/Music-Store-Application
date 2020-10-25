package music.data;

import java.sql.*;

import music.business.*;

public class UserDB 
{
	public static void insert(User user)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = 
			"INSERT INTO User " 
			+ "(FirstName, LastName, Email, CompanyName, " 
			+ "Address1, Address2, City, State, Zip, Country, "
			+ "CreditCardType, CreditCardNumber, CreditCardExpirationDate) "
			+ "VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try 
			{
				ps = connection.prepareStatement(query);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getCompanyName());
				ps.setString(5, user.getAddress1());
				ps.setString(6, user.getAddress2());
				ps.setString(7, user.getCity());
				ps.setString(8, user.getState());
				ps.setString(9, user.getZip());
				ps.setString(10, user.getCountry());
				ps.setString(11, user.getCreditCardType());
				ps.setString(12, user.getCreditCardNumber());
				ps.setString(13, user.getCreditCardExpirationDate());

				ps.executeUpdate();

				// Get the user ID from the last INSERT statement
				var identityQuery = "SELECT @@IDENTITY AS IDENTITY";
				var identityStatement = connection.createStatement();
				ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
				identityResultSet.next();
				var userID = identityResultSet.getLong("IDENTITY");
				identityResultSet.close();
				identityStatement.close();

				// Set the user ID in the user object
				user.setId(userID);
			}
			catch (SQLException ex)
			{
				System.err.println(ex);
			}
			finally 
			{
				DBUtil.closeResultSet(rs);
				DBUtil.closePreparedStatement(ps);
				pool.freeConnection(connection);
			}
	}

	public static void update(User user)
	{
		var pool = ConnectionPool.getInstance();
		var connection = poll.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "UPDATE User SET "
			+ "FirstName = ?, "
			+ "LastName = ?, "
			+ "CompanyName = ?, "
			+ "Address1 = ?, "
			+ "Address2 = ?, "
			+ "City = ?, "
			+ "State = ?, "
			+ "Zip = ?, "
			+ "Contry = ?, "
			+ "CreditCardType = ?, "
			+ "CreditCardNumber = ?, "
			+ "CreditCardExpirationDate = ?, "
			+ "WHERE Email = ?";

			try 
			{
				ps = connection.prepareStatement(query);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getCompanyName());
				ps.setString(4, user.getAddress1());
				ps.setString(5, user.getAddress2());
				ps.setString(6, user.getCity());
				ps.setString(7, user.getState());
				ps.setString(8, user.getZip());
				ps.setString(9, user.getCountry());
				ps.setString(10, user.getCreditCardType());
				ps.setString(11, user.getCrditCardNumber());
				ps.setString(12, user.getCreditCardExpirationDate());
				ps.setString(13, user.getEmail());	

				ps.executeUpdate();			
			}
			catch (SQLException ex)
			{
				System.err.println(ex);
			}
			finally 
			{
				DBUtil.closeResultSet(rs);
				DBUtil.closePreparedStatement(ps);
				pool.freeConnection(connection);
			}
	}

	public static User selectUser(String email)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "SELECT * FROM User "
			+ "WHERE Email = ?";

		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			User user = null;

			if (rs.next())
			{
				user = new User();
				user.setId(rs.getLong("UserID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
				user.setCompanyName(rs.getString("CompanyName"));
				user.setAddress1(rs.getString("Address1"));
				user.setAddress2(rs.getString("Address2"));
				user.setCity(rs.getString("City"));
				user.setState(rs.getString("State"));
				user.setZip(rs.getString("Zip"));
				user.setCountry(rs.getString("Country"));
				user.setCreditCardType(rs.getString("CreditCardType"));
				user.setCreditCardNumber(rs.getString("CreditCardNumber"));
				user.set(rs.getString("CreditCardExpirationDate"));				
			}

			return user;
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
			return null;
		}
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static boolean emailExist(String email)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "SELECT Email FROM User "
			+ "WHERE Email = ?";

		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			return rs.next();
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
			return false;
		}		
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps); 
			pool.freeConnection(connection);
		}
	}	
}