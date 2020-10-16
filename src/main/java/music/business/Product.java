package music.business;

import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable
{
	private Long productId;
	private String code;
	private String description;
	private double price;

	public Product() {}

	public Long getId()
	{
		return productId;
	}

	public void setId(Long value)
	{
		productId = value;
	}

	public void setCode(String value)
	{
		code = value;
	}

	public String getCode()
	{
		return code;
	}

	public void setDescription(String value)
	{
		description = value;
	}

	public String getDescription()
	{
		return description;
	}

	public String getArtistName()
	{
		String artistName =
			description.substring(0, description.indexOf(" - "));
		return artistName;
	}

	public void setPrice(double value)
	{
		price = value;
	}

	public double getPrice()
	{
		return price;
	}

	public String getPriceCurrencyFormat()
	{
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(price);
	}

	public String getImageURL()
	{
		return "/musicStorage/images/" + code + "_cover.jpg";
	}

	public String getProductType()
	{
		return "Audio CD";
	}
}