package service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.DataManager;

import com.google.gson.Gson;

import dto.DataObjects;

@Path("/WebService")
public class DataService {
	
	@GET
	@Path("/GetData")
	@Produces("application/json")
	public String feed(@QueryParam("locationPar") String locationPar)
	{
		String jsonData  = null;
		try 
		{
			ArrayList<DataObjects> dbData = null;
			DataManager projectManager= new DataManager();
			dbData = projectManager.getData(locationPar);

			Gson gson = new Gson();
			System.out.println(gson.toJson(dbData));
			jsonData = gson.toJson(dbData);

		} catch (Exception e)
		{
			System.out.println("error:" + e.getMessage());
		}
		return jsonData;
	}

}
