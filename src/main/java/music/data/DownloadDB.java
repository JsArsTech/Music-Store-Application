package music.data;

import java.sql.*;

import music.business.*;

public class DownloadDB
{
	public static long insert(Download download)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "INSERT INTO Download " +
			"(UserID, DownloadDate, ProductCode) " +
			"VALUES " +
			"(?, NOW(), ?)";

		try
		{
			ps = connection.prepareStatement(query);
			ps.setLong(1, download.getUser().getId());
			ps.setString(2, download.getProductCode());
			return ps.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
			return 0;
		} 
		finally
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
}