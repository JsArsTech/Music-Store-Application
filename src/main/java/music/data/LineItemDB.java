package music.data;

import java.sql.*;
import java.util.*;

import music.business.*;

public class LineItemDB
{
	// This method add one lineItem to the LineItem table
	public static long insert(long invoiceID, LineItem lineItem)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "INSERT INTO LineItem(InvoiceID, ProductID, Quantity) "
			+ "VALUES (?, ?, ?)";

		try 
		{
			ps = connection.prepareStatement(query);
			ps.setLong(1, invoiceID);
			ps.setLong(2, lineItem.getProduct().getId());
			ps.setInt(3, lineItem.getQuantity());
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

	// This method returns null if a record isn't found
	public static List<LineItem> selectLineItems(long invoiceID)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		var query = "SELECT * FROM LineItem "
			+ "WHERE InvoiceID = ?";

		try 
		{
			ps = connection.prepareStatement(query);
			ps.setLong(1, invoiceID);
			rs = ps.executeQuery();
			var lineItems = new ArrayList<LineItem>();

			while (rs.next())
			{
				var lineItem = new LineItem();
				int productID = rs.getInt("ProductID");
				var product = ProductDB.selectProduct(productID);
				lineItem.setProduct(product);
				lineItem.setQuantity(rs.getInt("Quantity"));
				lineItems.add(lineItem);
			}
			return lineItems;
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
}