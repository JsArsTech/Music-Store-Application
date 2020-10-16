package music.business;

import java.text.NumberFormat;
import java.io.Serializable;

public class LineItem implements Serializable
{
	private Long lineItemId;
	private Product product;
	private int quantity = 1;

	public LineItem()
	{}

	public Long getLineItemId()
	{
		return lineItemId;
	}

	public void setLineItemId(Long value)
	{
		lineItemId = value;
	}

	public void setProduct(Product value)
	{
		product = value;
	}

	public Product getProduct()
	{
		return Product;
	}

	public void setQuantity(int value)
	{
		quantity = value;
	} 

	public int getQuantity()
	{
		return quantity;
	}

	public double getTotal()
	{
		return product.getPrice() * quantity;
	}

	public String getTotalCurrencyFormat()
	{
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(this.getTotal());		
	}
}