package music.business;

import java.util.Date;
import java.io.Serializable;

public class Download implements Serializable
{
	private Long downloadId;
	private User user;
	private Date downloadDate;
	private String productCode;

	public Download()
	{
		user = null;
		downloadDate = new Date();
		productCode = "";
	}

	public Long getDownloadId()
	{
		return downloadId;
	}

	public void setDownloadId(Long value)
	{
		downloadId = value;
	}

	public void setUser(User value)
	{
		user = value;
	}

	public User getUser()
	{
		return user;
	}

	public void setDownloadDate(Date value)
	{
		downloadDate = value;
	}

	public Date getDownloadDate()
	{
		return downloadDate;
	} 

	public void setProductCode(String value)
	{
		productCode = value;
	}

	public String getProductCode()
	{
		return productCode;
	}
}