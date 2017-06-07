package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.DataObjects;


public class DBQuery {

	/*
	 * getCityData
	 */
	public ArrayList<DataObjects> getDBData1(Connection connection, String qparameter) throws Exception
	{
		ArrayList<DataObjects> objectList = new ArrayList<DataObjects>();
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT jo_location,jo_location_code,jo_jsonobject FROM yahoodata WHERE jo_location='" + qparameter + "' AND jo_jsonobject != '[]' ORDER BY jo_datetime DESC LIMIT 1;");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				DataObjects dataObjects = new DataObjects();
				dataObjects.setLocation(rs.getString("jo_location"));
				dataObjects.setLocation(rs.getString("jo_location_code"));
				dataObjects.setJsondata(rs.getString("jo_jsonobject"));
				objectList.add(dataObjects);
			}
			return objectList;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	/*
	 * getCityList
	 */
	public ArrayList<DataObjects> getDBData2(Connection connection, 
			String windchillMin, String windchillMax,
			String winddirMin, String winddirMax,
			String windspeedMin,String windspeedMax,
			String humMin,String humMax,
			String presMin,String presMax,
			String ris,
			String visMin,String visMax,
			String sunrMin,String sunrMax,
			String sunsMin,String sunsMax,
			String mylat,String mylong,
			String distMin, String distMax,
			String codeMin, String codeMax,
			String tempMin,String tempMax) throws Exception
	{
		double distance = 0;

		ArrayList<DataObjects> objectList = new ArrayList<DataObjects>();
		try
		{
			String sql = "SELECT jo_location,jo_location_code,jo_jsonobject,SUBSTRING(jo_jsonobject,POSITION('geoLat' IN jo_jsonobject)+8,POSITION('geoLong' IN jo_jsonobject)-2-POSITION('geoLat' IN jo_jsonobject)-8),SUBSTRING(jo_jsonobject,POSITION('geoLong' IN jo_jsonobject)+9,POSITION('\"condition\"' IN jo_jsonobject)-1-POSITION('geoLong' IN jo_jsonobject)-9), " +
					" SUBSTRING(jo_jsonobject, POSITION('low' IN jo_jsonobject)+5,POSITION('high' IN jo_jsonobject)-2-POSITION('low' IN jo_jsonobject)-5), SUBSTRING(jo_jsonobject, POSITION('high' IN jo_jsonobject)+6,2)  FROM yahoodata" + 
					" WHERE jo_jsonobject != '[]' AND jo_location=jo_location ";
			//wind chill
			sql += windchillMin!=null && windchillMax!=null?" AND SUBSTRING(jo_jsonobject,POSITION('chill' IN jo_jsonobject)+7,POSITION('direction' IN jo_jsonobject)-2-POSITION('chill' IN jo_jsonobject)+7) BETWEEN " + windchillMin + " AND " + windchillMax:"";
			//wind direction
			sql += winddirMin!=null && winddirMax!=null?" AND SUBSTRING_INDEX(SUBSTRING_INDEX(jo_jsonobject,'direction\":',-1),\",\",1) BETWEEN " + winddirMin + " AND " + winddirMax:"";
			//wind speed
			sql += windspeedMin!=null && windspeedMax!=null?" AND SUBSTRING_INDEX(SUBSTRING_INDEX(jo_jsonobject,'speed\":',-1),\"}\",1) BETWEEN " + windspeedMin + " AND " + windspeedMax:"";
			//humidity
			sql += humMin!=null && humMax!=null?" AND SUBSTRING(jo_jsonobject,POSITION('humidity' IN jo_jsonobject)+10,POSITION('visibility' IN jo_jsonobject)-2-POSITION('humidity' IN jo_jsonobject)-10) BETWEEN " + humMin + " AND " + humMax:"";
			//pressure
			sql += presMin!=null && presMax!=null?" AND SUBSTRING_INDEX(SUBSTRING_INDEX(jo_jsonobject,'pressure\":',-1),\",\",1) BETWEEN " + presMin + " AND " + presMax:"";
			//rising
			//sql += ris!=null?" AND SUBSTRING(jo_jsonobject,POSITION('rising' IN jo_jsonobject)+9,POSITION('astronomy' IN jo_jsonobject)-4-POSITION('rising' IN jo_jsonobject)-9) = '" + ris +"'":"";
			//pressure
			sql += visMin!=null && visMax!=null?" AND SUBSTRING_INDEX(SUBSTRING_INDEX(jo_jsonobject,'visibility\":',-1),\",\",1) BETWEEN " + visMin + " AND " + visMax:"";
			//sunrise
			sql += sunrMin!=null && sunrMax!=null?" AND SUBSTRING_INDEX(SUBSTRING_INDEX(jo_jsonobject,'sunrise\":{\"hours\":',-1),\",\",1) BETWEEN " + sunrMin + " AND " + sunrMax:"";
			//sunset
			sql += sunsMin!=null && sunsMax!=null?" AND SUBSTRING(jo_jsonobject,POSITION('sunset' IN jo_jsonobject)+10,POSITION('sunset' IN jo_jsonobject)+18-POSITION('sunset' IN jo_jsonobject)-10) BETWEEN " + sunsMin + " AND " + sunsMax:"";
			//code
			sql += codeMin!=null && codeMax!=null?" AND SUBSTRING(jo_jsonobject,POSITION('code' IN jo_jsonobject)+6,POSITION('temp\"' IN jo_jsonobject)-2-POSITION('code' IN jo_jsonobject)+6) BETWEEN " + codeMin + " AND " + codeMax:"";
			//temperature
			sql += tempMin!=null && tempMax!=null?" AND SUBSTRING(jo_jsonobject,POSITION('temp\"' IN jo_jsonobject)+6,POSITION('\"date\"' IN jo_jsonobject)-1-POSITION('temp\"' IN jo_jsonobject)-6) BETWEEN " + tempMin + " AND " + tempMax:"";
			sql = sql + " GROUP BY jo_location ORDER BY jo_datetime DESC;";

			System.out.println(sql);

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String cLat = rs.getString(4);
				String cLon = rs.getString(5);
				if(mylat!= null && mylong != null) {
					distance = distance(Double.parseDouble(mylat), Double.parseDouble(mylong), Double.parseDouble(cLat), Double.parseDouble(cLon), 'K');
					if(distance < Double.parseDouble(distMin) || distance > Double.parseDouble(distMax)) { continue; }
				}
				DataObjects dataObject = new DataObjects();
				dataObject.setLocation(rs.getString("jo_location"));
				dataObject.setDistance(Double.toString(distance));
				dataObject.setcLat(cLat);
				dataObject.setcLong(cLon);
				dataObject.setTempLow(rs.getString(6));
				dataObject.setTempHigh(rs.getString(7));
				dataObject.setLocationcode(rs.getString("jo_location_code"));
				objectList.add(dataObject);
			}
			return objectList;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	/*
	 * getCityListData
	 */
	public ArrayList<DataObjects> getDBData3(Connection connection, 
			String windchillMin, String windchillMax,
			String winddirMin, String winddirMax,
			String windspeedMin,String windspeedMax,
			String humMin,String humMax,
			String presMin,String presMax,
			String ris,
			String visMin,String visMax,
			String sunrMin,String sunrMax,
			String sunsMin,String sunsMax,
			String myLat,String myLong,
			String dist,
			String codeMin, String codeMax,
			String tempMin,String tempMax) throws Exception {

		ArrayList<DataObjects> objectList = new ArrayList<DataObjects>();
		try
		{
			String sql = "SELECT jo_location,jo_location_code,jo_jsonobject FROM yahoodata" + 
					" WHERE jo_jsonobject != '[]' AND jo_location=jo_location AND " +
					//wind chill
					windchillMin!="" && windchillMax!=""?"SUBSTRING(jo_jsonobject,POSITION('chill' IN jo_jsonobject)+7,POSITION('direction' IN jo_jsonobject)-2-POSITION('chill' IN jo_jsonobject)+7) BETWEEN " + windchillMin + " AND " + windchillMax:"" +
							//wind direction
							winddirMin!="" && winddirMax!=""?"SUBSTRING(jo_jsonobject,POSITION('direction' IN jo_jsonobject)+11,POSITION('speed' IN jo_jsonobject)-2-POSITION('direction' IN jo_jsonobject)+11) BETWEEN " + winddirMin + " AND " + winddirMax:"" +
									//wind speed
									windspeedMin!="" && windspeedMax!=""?"SUBSTRING(jo_jsonobject,POSITION('speed' IN jo_jsonobject)+7,POSITION('atmosphere' IN jo_jsonobject)-2-POSITION('speed' IN jo_jsonobject)+7) BETWEEN " + windspeedMin + " AND " + windspeedMax:"" +
											//humidity
											humMin!="" && humMax!=""?"SUBSTRING(jo_jsonobject,POSITION('humidity' IN jo_jsonobject)+10,POSITION('visibility' IN jo_jsonobject)-2-POSITION('humidity' IN jo_jsonobject)+10) BETWEEN " + humMin + " AND " + humMax:"" +
													//pressure
													presMin!="" && presMax!=""?"SUBSTRING(jo_jsonobject,POSITION('pressure' IN jo_jsonobject)+10,POSITION('rising' IN jo_jsonobject)-2-POSITION('pressure' IN jo_jsonobject)+10) BETWEEN " + presMin + " AND " + presMax:"" +
															//rising
															//ris!=""?"SUBSTRING(jo_jsonobject,POSITION('rising' IN jo_jsonobject)+9,POSITION('astronomy' IN jo_jsonobject)-4-POSITION('rising' IN jo_jsonobject)+9) = '" + ris +"'":"" +
															//pressure
															visMin!="" && visMax!=""?"SUBSTRING(jo_jsonobject,POSITION('visibility' IN jo_jsonobject)+12,POSITION('pressure' IN jo_jsonobject)-2-POSITION('visibility' IN jo_jsonobject)+12) BETWEEN " + visMin + " AND " + visMax:"" +
																	//sunrise
																	sunrMin!="" && sunrMax!=""?"SUBSTRING(jo_jsonobject,POSITION('sunrise' IN jo_jsonobject)+11,POSITION('sunrise' IN jo_jsonobject)-+19-POSITION('sunrise' IN jo_jsonobject)+11) BETWEEN " + sunrMin + " AND " + sunrMax:"" +
																			//sunset
																			sunsMin!="" && sunsMax!=""?"SUBSTRING(jo_jsonobject,POSITION('sunset' IN jo_jsonobject)+10,POSITION('sunset' IN jo_jsonobject)+18-POSITION('sunset' IN jo_jsonobject)+10) BETWEEN " + sunsMin + " AND " + sunsMax:"" +
																					//code
																					codeMin!="" && codeMax!=""?"SUBSTRING(jo_jsonobject,POSITION('code' IN jo_jsonobject)+6,POSITION('temp' IN jo_jsonobject)-2-POSITION('code' IN jo_jsonobject)+6) BETWEEN " + codeMin + " AND " + codeMax:"" +
																							//temperature
																							tempMin!="" && tempMax!=""?"SUBSTRING(jo_jsonobject,POSITION('temp' IN jo_jsonobject)+6,POSITION('\"date\"' IN jo_jsonobject)-1-POSITION('temp' IN jo_jsonobject)+6) BETWEEN " + tempMin + " AND " + tempMax:"" +
																									" GROUP BY jo_location ORDER BY jo_datetime DESC;";

			PreparedStatement ps = connection.prepareStatement(sql);ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				DataObjects dataObject = new DataObjects();
				dataObject.setLocation(rs.getString("jo_location"));
				dataObject.setLocation(rs.getString("jo_location_code"));
				dataObject.setJsondata(rs.getString("jo_jsonobject"));
				objectList.add(dataObject);
			}
			return objectList;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts decimal degrees to radians             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts radians to decimal degrees             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

}
