package music.data;

import java.sql.*;

public class SQLUtil 
{
	public static String getHtmlRows(ResultSet results)
		throws SQLException
	{
		var htmlRows = new StringBuilder();		
		var columnCount = metaData.getColumnCount();
		ResultSetMetaData metaData = results.getMetaData();

		htmlRows.append("<tr>");
		String cell;
		for (int i = 1; i <= columnCount; i++)
		{
			cell = "<th>" + metaData.getColumnName(i) + "</th>";
			htmlRows.append(cell);
		}
		htmlRows.append("</tr>");

		while (results.next())
		{
			htmlRows.append("<tr>");
			for (int i = 1; i <= columnCount; i++)
			{
				cell = "<td>" + results.getString(i) + "</td>";
				htmlRows.append(cell);
			}
			htmlRows.append("</tr>");
		}

		return htmlRows.toString();
	}

	public static String encode(String s)
	{
		if (s == null)
		{
			return s;
		}

		var sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++)
		{
			char ch = sb.charAt(i);
			if (ch == 39) 
			{
				sb.insert(i++, "'");
			}
		}
		return sb.toString();
	}
}