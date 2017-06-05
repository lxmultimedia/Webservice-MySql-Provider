package service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.DataManager;

import com.google.gson.Gson;

import dto.DataObjects;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;


@Path("/WebService")
public class DataService {

	@GET
	@Path("/GetCityData")
	@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
	@JsonRawValue
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "jsondata")
	public Response getCityData(@QueryParam("locationPar") String locationPar)
	{
		String jsonData  = null;
		List<DataObjects> dbData = null;
		try 
		{
			//ArrayList<DataObjects> dbData = null;
			DataManager dataManager= new DataManager();
			dbData = dataManager.getCityData(locationPar);

			Gson gson = new Gson();
			jsonData = gson.toJson(dbData);
			System.out.println(jsonData);


		} 
		catch (Exception e)
		{
			System.out.println("error:" + e.getMessage());
		}
			
		return Response.ok(jsonData).build();
	}
	
	@GET
	@Path("/GetCityList")
	@Produces("application/json")
	public String getCityList(
			@QueryParam("windchillMin") String windchillMin,
			@QueryParam("windchillMax") String windchillMax,
			@QueryParam("winddirMin") String winddirMin,
			@QueryParam("winddirMax") String winddirMax,
			@QueryParam("windspeedMin") String windspeedMin,
			@QueryParam("windspeedMax") String windspeedMax,
			@QueryParam("humMin") String humMin,
			@QueryParam("humMax") String humMax,
			@QueryParam("presMin") String presMin,
			@QueryParam("presMax") String presMax,			
			@QueryParam("ris") String ris,
			@QueryParam("visMin") String visMin,
			@QueryParam("visMax") String visMax,
			@QueryParam("sunrMin") String sunrMin,
			@QueryParam("sunrMax") String sunrMax,
			@QueryParam("sunsMin") String sunsMin,
			@QueryParam("sunsMax") String sunsMax,
			@QueryParam("mylat") String mylat,
			@QueryParam("mylong") String mylong,
			@QueryParam("distMin") String distMin,
			@QueryParam("distMax") String distMax,
			@QueryParam("codeMin") String codeMin,
			@QueryParam("codeMax") String codeMax,
			@QueryParam("tempMin") String tempMin,
			@QueryParam("tempMax") String tempMax)
	{
		String jsonData  = null;
		try 
		{
			ArrayList<DataObjects> dbData = null;
			DataManager dataManager= new DataManager();
			dbData = dataManager.getCityList(
					windchillMin,windchillMax,
					winddirMin,
					winddirMax,
					windspeedMin,
					windspeedMax,
					humMin,
					humMax,
					presMin,
					presMax,
					ris,
					visMin,
					visMax,
					sunrMin,
					sunrMax,
					sunsMin,
					sunsMax,
					mylat,mylong,
					distMin,distMax,
					codeMin,
					codeMax,
					tempMin,
					tempMax
					);

			Gson gson = new Gson();
			System.out.println(gson.toJson(dbData));
			jsonData = gson.toJson(dbData);

		} catch (Exception e)
		{
			System.out.println("error:" + e.getMessage());
		}
		return jsonData;
	}
	
	@GET
	@Path("/GetCityListData")
	@Produces("application/json")
	public String getCityListData(
			@QueryParam("windchillMin") String windchillMin,
			@QueryParam("windchillMax") String windchillMax,
			@QueryParam("winddirMin") String winddirMin,
			@QueryParam("winddirMax") String winddirMax,
			@QueryParam("windspeedMin") String windspeedMin,
			@QueryParam("windspeedMax") String windspeedMax,
			@QueryParam("humMin") String humMin,
			@QueryParam("humMax") String humMax,
			@QueryParam("presMin") String presMin,
			@QueryParam("presMax") String presMax,			
			@QueryParam("ris") String ris,
			@QueryParam("visMin") String visMin,
			@QueryParam("visMax") String visMax,
			@QueryParam("sunrMin") String sunrMin,
			@QueryParam("sunrMax") String sunrMax,
			@QueryParam("sunsMin") String sunsMin,
			@QueryParam("sunsMax") String sunsMax,
			@QueryParam("mylat") String myLat,
			@QueryParam("mylong") String myLong,
			@QueryParam("distMin") String distMin,
			@QueryParam("distMax") String distMax,
			@QueryParam("codeMin") String codeMin,
			@QueryParam("codeMax") String codeMax,
			@QueryParam("tempMin") String tempMin,
			@QueryParam("tempMax") String tempMax)
	{
		String jsonData  = null;
		try 
		{
			ArrayList<DataObjects> dbData = null;
			DataManager dataManager= new DataManager();
			dbData = dataManager.getCityList(
					windchillMin,windchillMax,
					winddirMin,
					winddirMax,
					windspeedMin,
					windspeedMax,
					humMin,
					humMax,
					presMin,
					presMax,
					ris,
					visMin,
					visMax,
					sunrMin,
					sunrMax,
					sunsMin,
					sunsMax,
					myLat,myLong,
					distMin,distMax,
					codeMin,
					codeMax,
					tempMin,
					tempMax
					);
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
