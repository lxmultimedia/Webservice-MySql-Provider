package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.DataObjects;


public class Project {
	
	
	public ArrayList<DataObjects> GetFeeds(Connection connection, String qparameter) throws Exception
	{
		ArrayList<DataObjects> feedData = new ArrayList<DataObjects>();
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT jo_location,jo_location_code,jo_jsonobject FROM yahoodata WHERE jo_location='" + qparameter + "' ORDER BY jo_datetime DESC LIMIT 1;");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				DataObjects feedObject = new DataObjects();
				feedObject.setLocation(rs.getString("jo_location"));
				feedObject.setLocation(rs.getString("jo_location_code"));
				feedObject.setJsondata(rs.getString("jo_jsonobject"));
				feedData.add(feedObject);
			}
			return feedData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
