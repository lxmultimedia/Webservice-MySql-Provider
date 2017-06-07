package model;

import java.sql.Connection;
import java.util.ArrayList;

import dao.Database;

import dao.DBQuery;
import dto.DataObjects;

public class DataManager {


	public ArrayList<DataObjects> getCityData(String qparameter) throws Exception {
		ArrayList<DataObjects> data = null;
		try {
			Database database= new Database();
			Connection connection = database.Get_Connection();
			DBQuery dbQuery = new DBQuery();
			data = dbQuery.getDBData1(connection, qparameter);

		} catch (Exception e) {
			throw e;
		}
		return data;
	}

	public ArrayList<DataObjects> getCityList(String windchillMin, String windchillMax,String winddirMin, String winddirMax,String windspeedMin, String windspeedMax,String humMin,String humMax,String presMin,String presMax,String ris,String visMin,String visMax,String sunrMin, String sunrMax,String sunsMin, String sunsMax,String myLat,String myLong,String distMin,String distMax, String codeMin, String codeMax,String tempMin, String tempMax) throws Exception {
		ArrayList<DataObjects> data = null;
		try {
			Database database= new Database();
			Connection connection = database.Get_Connection();
			DBQuery dbQuery = new DBQuery();
			data = dbQuery.getDBData2(connection, 
					windchillMin,windchillMax,
					winddirMin, winddirMax,
					windspeedMin, windspeedMax,
					humMin, humMax,
					presMin, presMax,
					ris,
					visMin,visMax,
					sunrMin,sunrMax,
					sunsMin,sunsMax,
					myLat,myLong,
					distMin, distMax,
					codeMin, codeMax,
					tempMin, tempMax);

		} catch (Exception e) {
			throw e;
		}
		return data;
	}

	public ArrayList<DataObjects> getCityListData(String windchillMin, String windchillMax,String winddirMin, String winddirMax,String windspeedMin, String windspeedMax,String humMin,String humMax,String presMin,String presMax,String ris,String visMin,String visMax,String sunrMin, String sunrMax,String sunsMin, String sunsMax,String myLat,String myLong,String distMin, String distMax, String codeMin, String codeMax,String tempMin, String tempMax) throws Exception {
		ArrayList<DataObjects> data = null;
		try {
			Database database= new Database();
			Connection connection = database.Get_Connection();
			DBQuery dbQuery = new DBQuery();
			data = dbQuery.getDBData2(connection, 
					windchillMin,windchillMax,
					winddirMin, winddirMax,
					windspeedMin, windspeedMax,
					humMin, humMax,
					presMin, presMax,
					ris,
					visMin,visMax,
					sunrMin,sunrMax,
					sunsMin,sunsMax,
					myLat,myLong,
					distMin, distMax,
					codeMin, codeMax,
					tempMin, tempMax);
		} catch (Exception e) {
			throw e;
		}
		return data;
	}
}

