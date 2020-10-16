package music.business;

import java.util.*;
import java.util.stream.*;
import java.text.*;
import java.io.Serializable;

public class Invoice implements Serializable
{
	private User user;
	private List<LineItem> lineItems;
	private Date invoiceDate;
	private Long invoiceNumber;
	private boolean isProcessed;

	public Invoice()
	{}

	public void setUser(User value)
	{
		user = value;
	}

	public User getUser()
	{
		return user;
	}

	public void setLineItems(List<LineItem> value)
	{
		lineItems = value;
	}

	public List<LineItem> getLineItems()
	{
		return lineItems;
	}

	public void setInvoiceDate(Date value)
	{
		invoiceDate = value;
	}

	public Date getInvoiceDate()
	{
		return invoiceDate;
	}

	public String getInvoiceDateDefaultFormat()
	{
		DateFormat dateFormat = DateFormat.getDateInstance();
		String invoiceDateFormatted = dateFormat.format(invoiceDate);
		return invoiceDateFormatted;
	}

	public void setInvoiceNumber(Long value)
	{
		invoiceNumber = value;
	}

	public Long getInvoiceNumber()
	{
		return invoiceNumber;
	}

	public boolean isProcessed()
	{
		return isProcessed;
	}

	public void setIsProcessed(boolean value)
	{
		isProcessed = value;
	}

	public double getInvoiceTotal()
	{
		double total = lineItems.stream()
			.reduce(0, (total, item) -> total + item.getTotal());
		return total;			
	}

	public String getInvoiceTotalCurrencyFormat()
	{
		double total = this.getInvoiceTotal();
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String formattedTotal = currency.format(total);
		return formattedTotal;		
	}
}