package music.data;

import java.sql.*;
import java.util.*;

import music.business.*;

public class InvoiceDB 
{
	public static void insert(Invoice invoice)
	{
		var pool = ConnectionPool.getInstance();
		var connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		// This method add a record to the invoice table.
		// SQL NOW() function insert the exact invoice date
		var query = "INSERT INTO Invoice (UserID, InvoiceDate, TotalAmount, IsProcessed) " 
			+ "VALUES (?, NOW(), ? 'n')";

		try 
		{
			ps = connection.prepareStatement(query);
			ps.setLong(1, invoice.getUser().getId());
			ps.setDouble(2, invoice.getInvoiceTotal());
			ps.executeUpdate();

			//Get the InvoiceID from the last insert statement
			var identityQuery = "SELECT @@IDENTITY AS IDENTITY";
			var identityStatement = connection.createStatement();			
			var identityResultSet = identityStatement.executeQuery(identityQuery);
			identityResultSet.next();
			long invoiceID = identityResultSet.getLong("IDENTITY");
			identityResultSet.close();
			identityStatement.close();

			// Write line items to the LineItem table.
			List<LineItem> lineItems = invoice.getLineItems();
			for (LineItem item : lineItems)
			{
				LineItemDB.insert(invoiceID, item);
			}
		}
		catch (SQLException ex)
		{
			System.err.println(ex);
		}
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePrepareStatement(ps);
			pool.freeCoonection(connection);
		}
	}	

	// This mode sets the Invoice.IsProcesed column to 'y'
   	public static voide update(Invoice invoice)
   	{
   		var pool = ConnectionPool.getInstance();
   		var connection = pool.getConnection();
   		PreparedStatement ps = null;
   		ResultSet rs = null;

   		var query = "UPDATE Invoice SET "
   			+ "IsProcessed = 'y' "
   			+ "WHERE InvoiceID = ?";

   		try 
   		{
   			ps = connection.prepareStatement(query);
   			ps.setLong(1, invoice.getInvoiceNumber());
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

   	public static ArrayList<Invoice> selectUnprocessedInvoices()
   	{
   		var pool = ConnectionPool.getInstance();
   		var connection = poll.getConnection();
   		PreparedStatement ps = null;
   		ResultSet rs = null;

   		// This method reads in all invoices that have not been 
   		// processed yet.
   		var query = "SELECT * "
   			+ "FROM User "
   			+ "INNER JOIN Invoice "
   			+ "ON User.UserID = Invoice.UserID "
   			+ "WHERE Invoice.IsProcessed = 'n' "
   			+ "ORDER BY InvoiceDate";

   		try
   		{
   			ps = connection.prepareStatement(query);
   			rs = ps.executeQuery();

   			var unprocessedInvoices = new ArrayList<Invoices>();

   			while (rs.next())
   			{
   				// Create a User object
   				var user = UserDB.selectUser(rs.getString("Email"));

   				// Get line items
   				long invoiceID = rs.getLong("Invoice.InvoiceID");
   				List<LineItem> lineItems = LineItemDB.selectLineItems(invoiceID);
				
				// Create the Invoice object
				var invoice = new Invoice();
				invoice.setUser(user);
				invoice.setInvoceDate(rs.getDate("InvoiceDate"));
			   	invoice.setInvoiceNumber(invoiceID);
			   	invoice.setLineItems(linetItems);

			   	unprocessedInvoices.add(invoice);			
   			} 
   			return unprocessedInvoices;
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
}