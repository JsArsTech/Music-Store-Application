package music.business;

import java.util.*;
import java.io.Serializable;

public class Cart implements Serializable
{
	private List<LineItem> items;

	public Cart() 
	{
		items = new ArrayList<>();
	}

	public void setItems(List<LineItem> lineItems)
	{
		items = lineItems;
	}

	public List<LineItems> getItems()
	{
		return items;
	}

	public int getCount()
	{
		return items.size();
	}

	public void addItem(LineItem item)
	{
		// If the item already exists in the cart,
		// only the quantity is updated. 
		String code = item.getProduct().getCode();
		int quantity = item.getQuantity();
		/*
		for (LineItem lineItem : items)
		{
			if (lineItem.getProduct().getCode().equals(code))
			{
				lineItem.setQuantity(quantity);
				return;
			}
		}
		items.add(item);
		*/
		LineItem item = items.stream()
			.filter(lineItem -> lineItem.getProduct().getCode().equals(code))
			.findAny()
			.orElse(null);

		if (item != null)
		{
			item.setQuantity(quantity);
			return;
		}			

		items.add(item);
	}

	public void removeItem(LineItem item)
	{
		String code = item.getProduct().getCode();

		items.remove(item);
	}
}


